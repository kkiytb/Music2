package cn.kk.music.dao;

import java.sql.SQLException;
import java.util.List;

import cn.kk.music.entity.Music;

public interface MusicDao {
	/**
	 * 查找所有音乐
	 * @return
	 * @throws SQLException
	 */
	List<Music> findAllMusic() throws SQLException;
	/**
	 * 添加音乐信息到数据库
	 * @param music
	 * @throws SQLException 
	 */
	public abstract void addMusic(Music music) throws SQLException;
	/**
	 * 清除音乐库
	 */
	void clearMusicList();
	/**
	 * 查询一个页面全部的music信息
	 * @param page 起始行数
	 * @param size	页面大小
	 * @return	一个页面的数据
	 * @throws SQLException 
	 */
	List<Music> findAllByPage(int start, int size) throws SQLException;
	/**
	 * 获取全部文件数量
	 * @return 文件数量
	 * @throws SQLException 
	 */
	int countAll() throws SQLException;
	
	List<Music> findAllByAll(int pageStart, int pageSize, int start, int size) throws SQLException;
}
