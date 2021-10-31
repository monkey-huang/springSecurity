package Jasper.SideProject.SpringSecurity.Filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import Jasper.SideProject.SpringSecurity.Config.TokenManager;
import Jasper.SideProject.SpringSecurity.Entity.SecurityUser;
import Jasper.SideProject.SpringSecurity.Entity.User;
import Jasper.SideProject.SpringSecurity.Util.R;
import Jasper.SideProject.SpringSecurity.Util.ReponseUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private TokenManager tokenManager;

	private RedisTemplate redisTemplate;
	// 權限管理的工具
	private AuthenticationManager authenticationManager;

	public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager,
			RedisTemplate redisTemplate) {
		this.authenticationManager = authenticationManager;
		this.tokenManager = tokenManager;
		this.redisTemplate = redisTemplate;
		this.setPostOnly(true);
		// 這邊是設定LOGIN的URL路徑
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/springSecurity/login", "POST"));
	}

	// 1 获取表单提交用户名和密码
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			// 很重要!!! 這裡會幫你把request得到的password(第二個參數)進行加密，並將加密的內容與資料庫加密過後的資料做比對
			System.out.println(new BCryptPasswordEncoder().encode(user.getPassword()));
			// 這邊會傳給UserDetailsService
			// 創建Authentication給AuthenticationManager
			return authenticationManager.authenticate(
					// 第三個參數放入權限的參數
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
							new ArrayList<>()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	// 2. 認證成功會呼叫的方法
	// Authentication authResult 會傳認證成功的訊息過來
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// 認證成功，得到使用者的資料
		SecurityUser user = (SecurityUser) authResult.getPrincipal();
		// 產生token
		String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
		/**
		 * 目前放資料的部分有問題
		 */
		// 把token的資訊放入redis
		redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(), user.getPermissionValueList());
		// 設定HttpServletResponse response的值回去
		ReponseUtil.out(response, R.ok().data("token", token));

	}

	// 3. 認證失敗會呼叫到方法
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
	}

}
