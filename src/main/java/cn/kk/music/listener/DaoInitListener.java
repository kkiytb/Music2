package cn.kk.music.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.kk.music.dao.MusicDao;
import cn.kk.music.dao.MusicDaoImpl;
import cn.kk.music.dao.UserDao;
import cn.kk.music.dao.UserDaoImpl;

/**
 * Application Lifecycle Listener implementation class DaoInitListener
 *
 */
public class DaoInitListener implements ServletContextListener {
	//销毁以后(Destroyed)执行一次
	public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
		
	}
	//ServletContext对象初始化以后(Initialized)
	//执行的方法只执行一次
	public void contextInitialized(ServletContextEvent servletContextEvent)  { 
		UserDao userdao = new UserDaoImpl();
		MusicDao musicDao = new MusicDaoImpl();
		servletContextEvent.getServletContext().setAttribute("userdao", userdao);
		servletContextEvent.getServletContext().setAttribute("musicdao", musicDao);
	}

}
