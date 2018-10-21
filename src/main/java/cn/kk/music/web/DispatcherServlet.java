package cn.kk.music.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 核心前端控制器
 */
public class DispatcherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ApplicationContext applicationContext;
	private HandlerMapping handlerMapping;
	/**
	 * Servlet初始化方法,用来加载控制器类
	 */
	public void init() throws ServletException {
		String cfg = getServletConfig().getInitParameter("config");
		applicationContext = new ApplicationContext(cfg);
		handlerMapping = new HandlerMapping();
		//getBeans方法获取application中创建的全部控制器对象
		handlerMapping.init(applicationContext.getBeans());
	}
	
	//doGet中调用doPost可以实现一个方法处理doGet或者doPost请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获得用户请求的路径
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			//删除uri中开头的ContextPath
			if(uri.startsWith(contextPath)) {
				uri = uri.substring(contextPath.length());
			}
			//查找需要执行的对象和方法
			UrlHandler handler = handlerMapping.getUrlHandler(uri);
			//获取用户表单提交的全部参数
			Map<String, String[]> paramMap = request.getParameterMap();
			//利用urlHandler执行控制器方法
			ModelMap<String, Object> map = new ModelMap<String, Object>();
			//传递Session参数
			SessionMap<String, Object> sessionMap = new SessionMap<String, Object>();
			HttpSession session = request.getSession();
			Enumeration<String> enumeration = session.getAttributeNames();
			while(enumeration.hasMoreElements()) {
				String name = enumeration.nextElement();
				sessionMap.put(name, session.getAttribute(name));
			}
			//将全部表单参数paraMap传递给控制器方法
			String target = handler.execute(paramMap, map, sessionMap);
			//将map中需要传递到JSP中的数据,复制到request的attribute中
			for (String key : map.keySet()) {
				Object value = map.get(key);
				request.setAttribute(key, value);
			}
			//赋值Session参数
			for (String key : sessionMap.keySet()) {
				Object val = sessionMap.get(key);
				session.setAttribute(key, val);
			}
			/*//利用反射执行控制器对象的方法
			Object object = handler.getObject();
			Method method = handler.getMethod();
			//执行目标方法
			Object obj = method.invoke(object);
			String target = obj.toString();*/
			//拼接为一个页面
			processView(target, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 处理process视图(显示)结果,根据target来决定如何处理
	 * 1.如果以redirect 为开头就进行重定向处理
	 * 		如果是http开始就绝对路径重定向
	 * 		如果不是http开始的就补上contextPath在重定向
	 * 2.如果不是以redirect 就直接转发
	 * @param target
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void processView(String target, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(target == null) {
			throw new ServletException("没有结果");
		}
		if (target.startsWith("redirect:")) {
			//重定向
			String path = target.substring("redirect:".length());
			if(!path.startsWith("http")) {
				path = request.getContextPath() + path;
			}
			response.sendRedirect(path);
		}else {
			//转发
			String path = "/WEB-INF/jsp/" + target + ".jsp";
			forward(request, response, path);
		}
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
}
