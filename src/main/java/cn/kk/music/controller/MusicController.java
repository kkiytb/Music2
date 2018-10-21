package cn.kk.music.controller;

import java.sql.SQLException;
import java.util.List;

import cn.kk.music.dao.MusicDao;
import cn.kk.music.dao.MusicDaoImpl;
import cn.kk.music.entity.Music;
import cn.kk.music.entity.User;
import cn.kk.music.web.ModelMap;
import cn.kk.music.web.Param;
import cn.kk.music.web.RequestMapping;
import cn.kk.music.web.SessionMap;

@RequestMapping("/music")
public class MusicController {
	private int pages = 5;
	private int begin = 1;
	private int beginlast = 1;
	private int pageslast = 5;
	@RequestMapping("/list.do")
	public String musilist(ModelMap<String, Object> map) throws SQLException {
		MusicDao dao = new MusicDaoImpl();
		List<Music> musics = dao.findAllMusic();
		//转发到JSP显示音乐列表
		//传递数据:
		map.put("musics", musics);
		//转发到目标页面
		return "music/list";
	}
	@RequestMapping("/player.do")
	public String musicplayer(@Param("page")String page,SessionMap<String, Object> sessionMap, ModelMap<String, Object> map) throws SQLException {
		//登录验证
		User user = (User) sessionMap.get("user");
		if(user == null) {
			return "redirect:/login.jsp";
		}
		//获取页号
		int pageIndex = 1;
		int number = 5;
		try {
			pageIndex = Integer.parseInt(page);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			Integer p = (Integer) sessionMap.get("musicPage");
			if(p != null) {
				p = pageIndex;
			}	
		}
		//页面显示数据个数
		int size = 10;
		//调用Dao获取数据
		MusicDao dao = new MusicDaoImpl();
		int rows = dao.countAll();
		int p = rows / size;
		if(rows % size != 0) {//不整除时候
			p++;
		}
		beginlast = begin - number;
		pageslast = pages + 1;
		if(begin <= number) {
			beginlast = 1;
		}
		if((pages + p % number) > p) {
			pageslast = p;
		}
		int start = (pageIndex - 1) * size;
		List<Music> musics = dao.findAllByPage(start, size);
		//计算总页数
		sessionMap.put("musicPage", pageIndex);
		map.put("begin", begin);
		map.put("beginlast", beginlast);
		map.put("pageslast", pageslast);
		map.put("pages", pages);
		map.put("musics", musics);
		return "music/player";
	}
}
