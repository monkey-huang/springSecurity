package Jasper.SideProject.SpringSecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter{
	
	// 這個要自己去implements
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(password());
	}
	
	@Bean
	PasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
	
	// 負責處理哪些網頁該被訪問的設置
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.formLogin()  //自訂義自己的登陸葉面
		.loginPage("/login.html") // 登陸的預設頁面
		.loginProcessingUrl("/user/login") // 訪問的路徑
		.defaultSuccessUrl("/test/index").permitAll() //預設登陸成功導向的頁面
		.and().authorizeRequests()
			.antMatchers("/", "/test/hello", "/user/login").permitAll() // 不需要驗證即可進入的頁面
		.anyRequest().authenticated()
		.and().csrf().disable(); // 關閉csrf防護
	}
}
