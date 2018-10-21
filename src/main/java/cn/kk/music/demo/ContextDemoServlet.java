package cn.kk.music.demo;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextDemoServlet
 */
public class ContextDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ServletContext 代表当前的运行环境
		//1. 可以通过很多渠道获取ServletContext
		//		其返回对象都是同一个对象,这样设计就是为了使用更便捷
		ServletContext sc1 = getServletContext();
		ServletContext sc2 = request.getServletContext();
		ServletContext sc3 = getServletConfig().getServletContext();
		System.out.println(sc1 == sc2 && sc2 == sc3);//true
		//获取当前环境信息:Servlet的版本
		int v1 = sc1.getEffectiveMajorVersion();
		int v2 = sc1.getEffectiveMinorVersion();
		System.out.println("Sevlet" + v1 + "." + v2);
		//获取当前环境信息: Tomcat支持的Servlet的版本
		v1 = sc1.getMajorVersion();
		v2 = sc1.getMinorVersion();
		System.out.println("Sevlet" + v1 + "." + v2);
		response.setContentType("text/html");
		response.getWriter().println("OK");
	}

}
