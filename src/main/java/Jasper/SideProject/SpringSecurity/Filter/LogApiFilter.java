//package Jasper.SideProject.SpringSecurity.Filter;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.filter.OncePerRequestFilter;
//
//public class LogApiFilter extends OncePerRequestFilter{
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		System.out.println("LogApiFilter Second One Filter");
////		filterChain.doFilter(request, response);
//		int httpStatus = response.getStatus();
//		String httpMethod = request.getMethod();
//		String uri = request.getRequestURI();
//		String params = request.getQueryString();
//		
//		if(params != null) {
//			uri += "?" + params;
//		}
//		
//		System.out.println(String.join("", String.valueOf(httpStatus), httpMethod, uri));
//		
//	}
//
//}
