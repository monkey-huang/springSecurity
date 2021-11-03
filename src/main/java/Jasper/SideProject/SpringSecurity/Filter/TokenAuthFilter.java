package Jasper.SideProject.SpringSecurity.Filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import Jasper.SideProject.SpringSecurity.Config.TokenManager;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TokenAuthFilter extends BasicAuthenticationFilter {

	private TokenManager tokenManager;

	private RedisTemplate redisTemplate;

	public TokenAuthFilter(AuthenticationManager authenticationManager, TokenManager tokenManager,
			RedisTemplate redisTemplate) {
		super(authenticationManager);
		this.tokenManager = tokenManager;
		this.redisTemplate = redisTemplate;
	}

	// 所有的request 都會經過這個，去檢查你是否有token再決定你是否已經通過驗證
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 获取当前认证成功用户权限信息
		UsernamePasswordAuthenticationToken authRequest = getAuthentication(request);
		// 判断如果有权限信息，放到权限上下文中d
		if (authRequest != null) {
			// 讓Security的框架，可以讓該使用者通過此驗證(透過token)
			SecurityContextHolder.getContext().setAuthentication(authRequest);
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request)
			throws SignatureException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException,
			IllegalArgumentException, UnsupportedEncodingException {
		String token = request.getHeader("token");
		if (token != null) {
			// 从token获取用户名
			String username = tokenManager.getUserInfoFromToken(token);
			// 从redis获取对应权限列表
			List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(username);
			Collection<GrantedAuthority> authority = new ArrayList<>();
			for (String permissionValue : permissionValueList) {
				SimpleGrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + permissionValue);
				authority.add(auth);
			}
			return new UsernamePasswordAuthenticationToken(username, token, authority);
		}

		return null;
	}

}
