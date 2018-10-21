package cn.kk.music.dao;

import java.sql.SQLException;
import java.util.List;

import cn.kk.music.entity.User;


public interface UserDao {
	/**
	 * 查询全部用户信息
	 * @return
	 * @throws SQLException
	 */
	List<User> findAll() throws SQLException;
	/**
	 * 将用户信息保存到数据库中
	 * user的ID是自动生成的
	 * @param user
	 * @throws SQLException 
	 */
	void saveUser(User user) throws SQLException;
	/**
	 * 查询一个页面的全部User信息
	 * @param start
	 * @param size
	 * @return 一个页面的数据
	 * @throws SQLException 
	 */
	List<User> findAllByPage(int start, int size) throws SQLException;
	/**
	 * 查询所有行数
	 * @return 所有行数
	 * @throws SQLException
	 */
	int countAll() throws SQLException;
	/**
	 * 通过id删除用户
	 * @param id
	 * @return 删除成功返回true
	 * @throws SQLException
	 */
	boolean deleteUserById(Integer id) throws SQLException;
	/**
	 * 通过id修改用户信息
	 * @param user 用户信息
	 * @return 修改成功返回true
	 * @throws SQLException
	 */
	boolean updateUserById(User user) throws SQLException;
	/**
	 * 通过id查找用户信息
	 * @param id 用户的id
	 * @return 用户信息
	 * @throws SQLException
	 */
	User findUserById(Integer id) throws SQLException;
	
	User findByName(String username) throws SQLException;
	
}
