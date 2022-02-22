package com.laptrinhjavaweb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.utils.SessionUtil;


@WebFilter("/*")
public class AuthorizationFilter implements Filter {
	
	private ServletContext servletContext;
   
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//get url of web
		String url = request.getRequestURI();
		if(url.startsWith("/admin")) {
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			if(user != null) {
				if(user.getRole().getCode().equalsIgnoreCase(SystemConstant.ADMIN_ROLE)) {
					chain.doFilter(request, response);
				}else
					response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&alert=danger&message=not_permission");
			}else {
				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&alert=danger&message=not_login");
			}
		}else {
			chain.doFilter(request, response);
		}
		
	} 

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.servletContext = filterConfig.getServletContext();
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}
