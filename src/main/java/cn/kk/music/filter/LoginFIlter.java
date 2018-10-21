package cn.kk.music.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.kk.music.entity.User;

/**
 * Servlet Filter implementation class LoginFIlter
 */
public class LoginFIlter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//检查session中是否有登录用户信息,如果没有
		//则跳转到login.jsp
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {//没有登录的情况下
			//重定向到login.jsp
			res.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}
		//执行后续的servlet
		chain.doFilter(req, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
