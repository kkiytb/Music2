package cn.kk.test;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;

import cn.kk.music.dao.MusicDao;
import cn.kk.music.dao.MusicDaoImpl;
import cn.kk.music.dao.UserDao;
import cn.kk.music.dao.UserDaoImpl;
import cn.kk.music.entity.Music;
import cn.kk.music.entity.User;

public class DaoTest {

	@Test
	public void testFindAll() {
		MusicDao dao = new MusicDaoImpl();
		try {
			List<Music> musics = dao.findAllMusic();
			for (Music music : musics) {
				System.out.println(music);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testAddMusic() {
		Map<String, String> musics = getMusicName("D:\\CloudMusic");
		MusicDao musicDao = new MusicDaoImpl();
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
	}
	private  Map<String, String> musics = new HashMap<String, String>();
	public Map<String, String> getMusicName(String filePath) {
		//创建一个musics线性表保存musicName
		//获取路径下对应的目录
		File dir = new File(filePath);
		//如果目录存在
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
						musics.put(file.getName(), "CloudMusic" + file.getAbsolutePath().substring("D:\\CloudMusic".length()));
				}
			}
		}
		//把结果返回
		return musics;
		
	}
	@Test
	public void testSaveUser() {
		UserDao dao = new UserDaoImpl();
		User user = new User(null, "TOM", "123", "110@tedu.cn", "110", new Date());
		System.out.println(user);
		try {
			dao.saveUser(user);
			System.out.println(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testFindAllByPage() {
		int page = 2;
		int size = 10;
		MusicDao dao = new MusicDaoImpl();
		List<Music> musics;
		try {
			musics = dao.findAllByPage((page - 1) * size, size);
			System.out.println(musics);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindByName() throws SQLException {
		UserDao dao = new UserDaoImpl();
		User user = dao.findByName("小明");
		System.out.println(user);
	}
	
	
}