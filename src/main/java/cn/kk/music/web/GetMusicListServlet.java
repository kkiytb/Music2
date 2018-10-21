package cn.kk.music.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kk.music.dao.MusicDao;
import cn.kk.music.entity.Music;

/**
 * Servlet implementation class GetMusicListServlet
 */
public class GetMusicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Map<String, String> musics = getMusicName("/home/soft01/音乐");
			MusicDao musicDao = (MusicDao) request.getServletContext().getAttribute("musicdao");
			Music music = new Music();
			musicDao.clearMusicList();
			Set<Entry<String, String>> musicsSet = musics.entrySet();
			for(Entry<String, String> name : musicsSet) {
				music.setMusicName(name.getKey());
				music.setMusicPath(name.getValue());
				music.setMusicLRC("");
				music.setMusicInfo("");
				try {
					musicDao.addMusic(music);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("music/list.do");
	}
	private  Map<String, String> musics = new HashMap<String, String>();
	public Map<String, String> getMusicName(String filePath) {
		//创建一个musics线性表保存musicName
		//获取路径下对应的目录
		File dir = new File(filePath);
		//如果目录存在并且目录不是空目录
		if (dir.exists() && dir.listFiles() != null) {
			//创建所有文件数组
			File[] files = dir.listFiles();
			//遍历所有文件
			for (File file : files) {
				//如果目录下是目录
				if(file.isDirectory()) {
					//递归判断目录下是否还是目录
					getMusicName(file.getAbsolutePath());
				}
				//如果目录下是文件,把文件名字获取后放入musics里面
				if(file.getName().endsWith(".mp3") || file.getName().endsWith(".flac")) {
						musics.put(file.getName(), file.getAbsolutePath());
				}
			}
		}
		//把结果返回
		return musics;
		
	}
}
