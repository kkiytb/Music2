package cn.kk.music.controller;

import java.sql.SQLException;

import cn.kk.music.dao.UserDao;
import cn.kk.music.dao.UserDaoImpl;
import cn.kk.music.entity.User;
import cn.kk.music.web.ModelMap;
import cn.kk.music.web.Param;
import cn.kk.music.web.RequestMapping;
import cn.kk.music.web.SessionMap;

public class LoginController {
	@RequestMapping("/login.do")
	public String login(@Param("username") String username,@Param("password")String password,ModelMap<String, Object> map, SessionMap<String, Object> sessionMap) throws SQLException {
		if(username==null || username.trim().isEmpty()) {
			map.put("error", "用户名不能为空");
			return "login";
		}
		if(password==null || password.trim().isEmpty()) {
			map.put("error", "密码不能为空");
			return "login";
		}
		//登录验证
		map.put("username", username);
		UserDao dao = new UserDaoImpl();
		User user = dao.findByName(username);
		if (user.getUsername() == null) {
			map.put("error", "用户名不存在");
			return "login";
		}
		//显示登录成功结果
		if(password.equals(user.getPassword())) {
			map.put("message", "欢迎" + user.getUsername() + "回来!");
			//在session中保存用户信息
			sessionMap.put("user", user);
			return "ok";
		}
		map.put("error", "密码不正确");
		return "login";
	}

}
