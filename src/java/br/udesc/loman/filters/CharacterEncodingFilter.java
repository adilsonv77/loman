package br.udesc.loman.filters;

import java.io.IOException;
import javax.servlet.*;


public class CharacterEncodingFilter implements Filter {
	 
	    @Override
	    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
	        req.setCharacterEncoding("UTF-8");
	        resp.setCharacterEncoding("UTF-8");
	        chain.doFilter(req, resp);
	    }
	 
	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    }
	 
	    @Override
	    public void destroy() {
	    }
}