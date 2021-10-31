package Jasper.SideProject.SpringSecurity.Security;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import Jasper.SideProject.SpringSecurity.Config.TokenManager;
import Jasper.SideProject.SpringSecurity.Util.R;
import Jasper.SideProject.SpringSecurity.Util.ReponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

//退出处理器
public class TokenLogoutHandler implements LogoutHandler {
	private TokenManager tokenManager;
	private RedisTemplate redisTemplate;

	public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
		this.tokenManager = tokenManager;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// 1 从header里面获取token
		// 2 token不为空，移除token，从redis删除token
		String token = request.getHeader("token");
		if (token != null) {
			// 移除
			tokenManager.removeToken(token);
			// 从token获取用户名
			String username;
			try {
				username = tokenManager.getUserInfoFromToken(token);
				redisTemplate.delete(username);
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExpiredJwtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedJwtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedJwtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ReponseUtil.out(response, R.ok());
	}
}