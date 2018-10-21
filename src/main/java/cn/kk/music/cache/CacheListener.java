package cn.kk.music.cache;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.kk.music.dao.MusicDao;
import cn.kk.music.dao.MusicDaoImpl;
import cn.kk.music.entity.Music;

/**
 * Application Lifecycle Listener implementation class CacheListener
 *
 */
public class CacheListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0)  { 
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent servletContextEvent)  {
		MusicDao dao = new MusicDaoImpl();
		try {
			List<Music> musics = dao.findAllMusic();
			servletContextEvent.getServletContext().setAttribute("musics", musics);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
