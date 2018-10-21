package cn.kk.music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.kk.music.entity.Music;
import cn.kk.music.utils.DBUtils;

public class MusicDaoImpl implements MusicDao{
	public List<Music> findAllMusic() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			statement = connection.createStatement();
			//执行SQL
			resultSet = statement.executeQuery("SELECT * FROM t_music");
			List<Music> musics = new ArrayList<Music>();
			mapRow(resultSet, musics);
			return musics;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
	}
	private void mapRow(ResultSet resultSet, List<Music> musics) throws SQLException {
		while(resultSet.next()) {
			Music music = new Music();
			music.setId(resultSet.getInt(1));
			music.setMusicName(resultSet.getString(2));
			music.setMusicPath(resultSet.getString(3));
			music.setMusicInfo(resultSet.getString(4));
			music.setMusicLRC(resultSet.getString(5));
			musics.add(music);
		}
	}
	public void addMusic(Music music) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			String sql = "INSERT INTO t_music VALUES(null,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			//执行SQL
			statement.setString(1, music.getMusicName());
			statement.setString(2, music.getMusicPath());
			statement.setString(3, music.getMusicInfo());
			statement.setString(4, music.getMusicLRC());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
		
	}
	public void clearMusicList() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			statement = connection.createStatement();
			//执行SQL
			statement.execute("truncate t_music");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
		
	}
	@Override
	public List<Music> findAllByPage(int start, int size) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			String sql = "SELECT id,musicname,musicpath,musicinfo,musiclrc FROM t_music LIMIT ?,?";
			statement = connection.prepareStatement(sql);
			//执行SQL
			statement.setInt(1, start);
			statement.setInt(2, size);
			resultSet = statement.executeQuery();
			List<Music> musics = new ArrayList<Music>();
			mapRow(resultSet, musics);
			return musics;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
	}
	@Override
	public int countAll() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			statement = connection.createStatement();
			//执行SQL
			resultSet = statement.executeQuery("SELECT COUNT(*) n FROM t_music");
			while(resultSet.next()) {
				return resultSet.getInt("n");
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
	}
	@Override
	public List<Music> findAllByAll(int pageStart, int pageSize, int start, int size) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			String sql = "SELECT * FROM (SELECT * FROM t_music LIMIT ?,?) t LIMIT ?,?";
			statement = connection.prepareStatement(sql);
			//执行SQL
			statement.setInt(1, pageStart);
			statement.setInt(2, pageSize);
			statement.setInt(3, start);
			statement.setInt(4, size);
			resultSet = statement.executeQuery();
			List<Music> musics = new ArrayList<Music>();
			mapRow(resultSet, musics);
			return musics;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
	}
}
