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
//public class LogProcessFilter extends OncePerRequestFilter{
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		long startTime = System.currentTimeMillis();
//		// filter chian 會將所有的filter做一遍，也要等這doFilter做完HttpServletResponse才有資料(但做這filter 自己的filter也會跑一遍==)
////		filterChain.doFilter(request, response);
//		long processTime = System.currentTimeMillis() - startTime;
//		
//		System.out.println("LogProcessFilter First One Filter");
//		
//		System.out.println(processTime + "ms");
//		
//		System.out.println("TODO LOG SQL");
//	}
//
//}
