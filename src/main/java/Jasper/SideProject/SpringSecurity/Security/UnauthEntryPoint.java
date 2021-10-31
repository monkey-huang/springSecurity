package Jasper.SideProject.SpringSecurity.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import Jasper.SideProject.SpringSecurity.Util.R;
import Jasper.SideProject.SpringSecurity.Util.ReponseUtil;

public class UnauthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ReponseUtil.out(httpServletResponse, R.error());
    }
}
