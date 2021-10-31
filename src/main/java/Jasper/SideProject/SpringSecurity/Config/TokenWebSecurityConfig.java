package Jasper.SideProject.SpringSecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import Jasper.SideProject.SpringSecurity.Filter.TokenAuthFilter;
import Jasper.SideProject.SpringSecurity.Filter.TokenLoginFilter;
import Jasper.SideProject.SpringSecurity.Security.TokenLogoutHandler;
import Jasper.SideProject.SpringSecurity.Security.UnauthEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressWarnings("rawtypes")
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * 配置设置
	 * 
	 * @param http
	 * @throws Exception
	 */
	// 设置退出的地址和token，redis操作地址
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new UnauthEntryPoint())// 没有权限访问
//	      .antMatchers("/admin/**").hasRole("ADMIN")
				.and().csrf().disable()
				.authorizeRequests().anyRequest().authenticated()
				.and()
				.logout()
				.logoutUrl("/logout")// 退出路径
				// 登出時會經過的處理器
				.addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate)).and()
				// 認證過濾器
				.addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
				// 授權過濾器
				.addFilter(new TokenAuthFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();
	}

	@Bean
	PasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
	
	// 這是驗證的過程
	// 调用userDetailsService和密码处理
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(password());
	}


	// 不进行认证的路径，可以直接访问
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/api/**");
	}
}
