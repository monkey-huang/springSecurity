package Jasper.SideProject.SpringSecurity.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 設置你implement的filter之執行順序、哪個url會進這filter
 * 應該只能有一個filter
 */
@Configuration
public class FilterConfig {

//	@Bean
//	public FilterRegistrationBean<LogProcessFilter> logProcessFilter(){
//        FilterRegistrationBean<LogProcessFilter> bean = new FilterRegistrationBean<>();
//        bean.setFilter(new LogProcessFilter());
//        // 代表所有URL都會mapping到這filter
//        bean.addUrlPatterns("/*");
//        // 設置filter name
//        bean.setName("LogProcessFilter");
//        // 代表最先執行
//        bean.setOrder(0);
//        return bean;
//	}
	
	/**
	 * 這邊要設定一下，不然security會跟這個衝突，可能沒有回傳到response
	 */
//	@Bean
//	public FilterRegistrationBean<LogApiFilter> logApiFilter(){
//		FilterRegistrationBean<LogApiFilter> bean = new FilterRegistrationBean<>();
//		bean.setFilter(new LogApiFilter());
//		
//		bean.addUrlPatterns("/*");
//		
//		bean.setName("LogApiFilter");
//		bean.setOrder(1);
//		return bean;
//	}
}
