//package Jasper.SideProject.SpringSecurity.Config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//public class SecurityConfigTest extends WebSecurityConfigurerAdapter{
//	
//	// 這個要自己去implements。Spring 會自動找到有實作這個介面的類別
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(password());
//	}
//	
//	@Bean
//	PasswordEncoder password() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	// 負責處理哪些網頁該被訪問的設置
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
////		http.formLogin()  //自訂義自己的登陸葉面
////		.loginPage("/login.html") // 登陸的預設頁面
////		.loginProcessingUrl("/user/login") // 訪問的路徑
////		.defaultSuccessUrl("/test/index").permitAll() //預設登陸成功導向的頁面
////		.and().authorizeRequests()
////			.antMatchers("/", "/test/hello", "/user/login").permitAll() // 不需要驗證即可進入的頁面
////		.anyRequest().authenticated()
////		.and().csrf().disable(); // 關閉csrf防護
//
//		
//		/**
//		 * 「*」：代表0到多個字元。如「/products/*」適用於「/products」、「/products/123」，但不適用「/products/123/draft」
//		 * 「**」：代表0到多個路徑。如「/products/**」適用於「/products」底下任何路徑。
//		 * 「?」：代表一個字元。如「/products/?*」適用於「/products/1」、「/products/123」，但不適用「/products」。
//		 */
//		/**
//		 * 「/users」這個 API 及其底下的所有 GET 請求，需通過身份驗證才可存取。
//		 * 其餘 API 的所有GET請求，允許所有呼叫方存取。
//		 * 「/users」這個 API 的 POST 請求，允許所有呼叫方存取。
//		 * 其餘的所有 API，需通過身份驗證才可存取。
//		 */
//		http
//        .authorizeRequests()
//        .antMatchers(HttpMethod.GET, "/users/**").authenticated()
//        .antMatchers(HttpMethod.GET).permitAll()
//        .antMatchers(HttpMethod.POST, "/users").permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .csrf().disable()
//        // 使用內建的登入畫面
//        .formLogin();
//	}
//}
