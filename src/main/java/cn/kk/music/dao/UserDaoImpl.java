package cn.kk.music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.kk.music.entity.User;
import cn.kk.music.utils.DBUtils;

public class UserDaoImpl implements UserDao{
	//只有只读属性,没有可写属性,就是没有状态!

	public List<User> findAll() throws SQLException {
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			statement = connection.createStatement();
			//执行SQL
			resultSet = statement.executeQuery("SELECT * FROM t_user");
			mapRow(users, resultSet);
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
	}
	private void mapRow(List<User> users, ResultSet resultSet) throws SQLException {
		while(resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getInt(1));
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setEmail(resultSet.getString("email"));
			user.setMobile(resultSet.getString("mobile"));
			user.setCreatedate(resultSet.getTimestamp("createdate"));
			users.add(user);
		}
	}
	//UserDaoImpl
	public void saveUser(User user) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			String sql = "INSERT INTO t_user (id, username, password, email, mobile, createdate) VALUES ("
					+ "null,?,?,?,?,?)";
			statement = connection.prepareStatement(sql, new String[] {"id"});//用jdbc获取id的方法,第二个参数传个字符串数组
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getMobile());
			long l = user.getCreatedate().getTime();
			statement.setTimestamp(5, new Timestamp(l));
			//执行SQL
			int n = statement.executeUpdate();
			if(n != 1) {
				throw new RuntimeException("保存失败");
			}
			//读取自动生成的ID
			//Generated生成的key:关键字id
			resultSet = statement.getGeneratedKeys();
			while(resultSet.next()) {
				user.setId(resultSet.getInt(1));
			}
			resultSet.close();
			statement.close();
			//保存用户的角色
			sql = "INSERT INTO user_role (id, user_id, role_id) VALUES (null,?,?)";
			statement = connection.prepareStatement(sql);
			//批量执行
			for(int roleid : user.getRoles()) {
				statement.setInt(1, user.getId());
				statement.setInt(2, roleid);
				statement.addBatch();
			}
			statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
		
	}
	@Override
	public List<User> findAllByPage(int start, int size) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			String sql = "SELECT id, username, password, email, mobile, createdate FROM t_user LIMIT ?,?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, start);
			statement.setInt(2, size);
			//执行SQL
			resultSet = statement.executeQuery();
			List<User> users = new ArrayList<User>();
			mapRow(users, resultSet);
			return users;
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
			resultSet = statement.executeQuery("SELECT COUNT(*) c FROM t_user");
			while(resultSet.next()) {
				return resultSet.getInt("c");
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
	public boolean deleteUserById(Integer id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			String sql = "DELETE FROM t_user WHERE id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			int n = statement.executeUpdate();
			return n == 1;
			//执行SQL
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
		
	}
	@Override
	public boolean updateUserById(User user) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			String sql = "UPDATE t_user SET username=?,password=?,email=?,mobile=? WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getMobile());
			statement.setInt(5, user.getId());
			int n = statement.executeUpdate();
			return n == 1;
			//执行SQL
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
	}
	@Override
	public User findUserById(Integer id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			String sql = "SELECT username,password,email,mobile FROM t_user WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			//执行SQL
			resultSet = statement.executeQuery();
			User user = new User();
			while(resultSet.next()) {
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setMobile(resultSet.getString("mobile"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
	}
	@Override
	public User findByName(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//获取连接对象
			connection = DBUtils.getConnection();
			//创建SQL执行对象
			String sql = "SELECT id,username,password,email,mobile,createdate FROM t_user WHERE username=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			//执行SQL
			resultSet = statement.executeQuery();
			User user = new User();
			while(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setMobile(resultSet.getString("mobile"));
				user.setCreatedate(resultSet.getDate("createdate"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//关闭资源
			DBUtils.close(resultSet, statement, connection);
		}
	}

}
