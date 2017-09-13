package com.bookstore.config;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter {
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		response.setHeader("Access-Controle-Allow-Origin", "*");
		response.setHeader("Access-Controle-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Controle-Allow-Headers", "x-requested-with, x-auth-token");
		response.setHeader("Access-Controle-Max-Age", "3600");
		response.setHeader("Access-Controle-Allow-Creadentials", "true");
		
		if(!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
			try {
				chain.doFilter(req, res);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			System.out.println("Pre-fight");
			response.setHeader("Access-Controle-Allow-Methods", "POST, GET, DELETE");
			response.setHeader("Access-Controle-Max-Age", "3600");
			response.setHeader("Access-Controle-Allow-Headers", "authorization, content-type, x-requested-with, x-auth-token, " +
					"acces-control-request-headers, acces-control-request-method, accept, origin");
			response.setStatus(HttpServletResponse.SC_OK);
		}	
	}
	
	public void init(FilterConfig filterConfig) {}
	
	public void destroy() {}
}
