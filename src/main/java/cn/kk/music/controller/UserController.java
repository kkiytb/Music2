package cn.kk.music.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

import cn.kk.music.dao.UserDao;
import cn.kk.music.dao.UserDaoImpl;
import cn.kk.music.entity.User;
import cn.kk.music.web.ModelMap;
import cn.kk.music.web.Param;
import cn.kk.music.web.RequestMapping;
import cn.kk.music.web.SessionMap;

@RequestMapping("/user")
public class UserController {
	@RequestMapping("/delete.do")
	public String delete(@Param("id")String idString,ModelMap<String, Object> map) throws SQLException {
		//获取用户ID
		Integer id = Integer.parseInt(idString);
		//调用Dao删除用户
		UserDao dao = new UserDaoImpl();
		boolean deleteFlag = dao.deleteUserById(id);
		//重定向到users.do
		if (deleteFlag) {
			return "redirect:/user/users.do";
		}
		map.put("error", "删除失败");
		return "redirect:/user/users.do";
	}
	/**
	 * 显示添加用户界面
	 */
	@RequestMapping("/add.do")
	public String add() throws SQLException {
		return "user/add";
	}
	@RequestMapping("/reg.do")
	public String userRegister(@Param("username")String username,@Param("password")String password,@Param("confirm")String confirm,@Param("agree")String agree,@Param("email")String email,@Param("mobile")String mobile,ModelMap<String, Object> map) throws SQLException {
		//校验表单信息
		UserDao dao = new UserDaoImpl();
		User userN = dao.findByName(username);
		if(userN.getUsername() != null) {
			map.put("error", "用户名已存在");
			return "register";
		}
		if(!"on".equals(agree)) {
			map.put("error", "你必须同意注册条款");
			return "register";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String reg = "^[a-zA-Z0-9_-]{4,16}$";
		if(!username.matches(reg)) {
			map.put("error", "用户名必须是4-~16位英文字符");
			return "register";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String regP = "[a-zA-Z0-9]{6,20}";
		if(!password.matches(regP)) {
			map.put("error", "密码必须是6-~20位英文或数字");
			return "register";//forward不能出现两次,后面须结束,否则会出现异常
		}
		if(!password.equals(confirm)) {
			map.put("error", "两次密码需要一致");
			return "register";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String regM = "^((17[0-9])|(14[0-9])|(13[0-9])|15[0-9]|18[0-9])\\d{8}$";
		if(!mobile.matches(regM)) {
			map.put("error", "手机号码格式不正确");
			return "register";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String regE = "(\\w+)([\\w+.-])*@(\\w+)([\\w+.-])*\\.\\w+";
		if(!email.matches(regE)) {
			map.put("error", "邮箱格式不正确");
			return "register";//forward不能出现两次,后面须结束,否则会出现异常
		}
		//调用dao保存信息
		User user = new User(null, username, password, email, mobile, new Date());
		dao.saveUser(user);
		return "redirect:/login.jsp";
	}
	@RequestMapping("/save.do")
	public String userSave(@Param("username")String username,
						   @Param("password")String password,
						   @Param("confirm")String confirm,
						   @Param("agree")String agree,@Param("email")String email,@Param("mobile")String mobile,@Param("username")String[] roles,ModelMap<String, Object> map) throws SQLException {
		//校验表单信息
		UserDao dao = new UserDaoImpl();
		User userN = dao.findByName(username);
		if(userN.getUsername() != null) {
			map.put("error", "用户名已存在");
			return "user/add";
		}
		if(roles == null || roles.length == 0) {
			map.put("error", "必须选定用户角色");
			return "user/add";
		}
		//将字符串数组数据转换成int[]数据
		int[] roleIds = new int[roles.length]; 
		for (int i = 0; i < roles.length; i++) {
			roleIds[i] = Integer.parseInt(roles[i]);
		}
		if(!"on".equals(agree)) {
			map.put("error", "你必须同意注册条款");
			return "user/add";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String reg = "^[a-zA-Z0-9_-]{4,16}$";
		if(!username.matches(reg)) {
			map.put("error", "用户名必须是4-~16位英文字符");
			return "user/add";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String regP = "[a-zA-Z0-9]{6,20}";
		if(!password.matches(regP)) {
			map.put("error", "密码必须是6-~20位英文或数字");
			return "user/add";//forward不能出现两次,后面须结束,否则会出现异常
		}
		if(!password.equals(confirm)) {
			map.put("error", "两次密码需要一致");
			return "user/add";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String regM = "^((17[0-9])|(14[0-9])|(13[0-9])|15[0-9]|18[0-9])\\d{8}$";
		if(!mobile.matches(regM)) {
			map.put("error", "手机号码格式不正确");
			return "user/add";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String regE = "(\\w+)([\\w+.-])*@(\\w+)([\\w+.-])*\\.\\w+";
		if(!email.matches(regE)) {
			map.put("error", "邮箱格式不正确");
			return "user/add";//forward不能出现两次,后面须结束,否则会出现异常
		}
		//调用dao保存信息
		User user = new User(null, username, password, email, mobile, new Date());
		dao.saveUser(user);
		return "redirect:/user/users.do";
	}
	@RequestMapping("/update.do")
	public String userUpdate(@Param("username")String username,@Param("password")String password,
							 @Param("confirm")String confirm,@Param("email")String email,
							 @Param("mobile")String mobile,@Param("username")String[] roles,
							 ModelMap<String, Object> map) throws SQLException {
		//校验表单信息
		UserDao dao = new UserDaoImpl();
		User userN = dao.findByName(username);
		if(userN.getUsername() != null) {
			map.put("error", "用户名已存在");
			return "user/update";
		}
		if(roles == null || roles.length == 0) {
			map.put("error", "必须选定用户角色");
			return "user/update";
		}
		//将字符串数组数据转换成int[]数据
		int[] roleIds = new int[roles.length]; 
		for (int i = 0; i < roles.length; i++) {
			roleIds[i] = Integer.parseInt(roles[i]);
		}
		String reg = "^[a-zA-Z0-9_-]{4,16}$";
		if(!username.matches(reg)) {
			map.put("error", "用户名必须是4-~16位英文字符");
			return "user/update";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String regP = "[a-zA-Z0-9]{6,20}";
		if(!password.matches(regP)) {
			map.put("error", "密码必须是6-~20位英文或数字");
			return "user/update";//forward不能出现两次,后面须结束,否则会出现异常
		}
		if(!password.equals(confirm)) {
			map.put("error", "两次密码需要一致");
			return "user/update";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String regM = "^((17[0-9])|(14[0-9])|(13[0-9])|15[0-9]|18[0-9])\\d{8}$";
		if(!mobile.matches(regM)) {
			map.put("error", "手机号码格式不正确");
			return "user/update";//forward不能出现两次,后面须结束,否则会出现异常
		}
		String regE = "(\\w+)([\\w+.-])*@(\\w+)([\\w+.-])*\\.\\w+";
		if(!email.matches(regE)) {
			map.put("error", "邮箱格式不正确");
			return "user/update";//forward不能出现两次,后面须结束,否则会出现异常
		}
		//调用dao保存信息
		User user = new User(null, username, password, email, mobile, new Date());
		dao.saveUser(user);
		return "redirect:/user/users.do";
	}
	@RequestMapping("/updateuser.do")
	public String toUpdate(@Param("id")String id,ModelMap<String, Object> map) throws NumberFormatException, SQLException {
		UserDao dao = new UserDaoImpl();
		User user = new User();
		user = dao.findUserById(Integer.parseInt(id));
		map.put("user", user);
		map.put("id", id);
		return "user/update";
	}
	@RequestMapping("/users.do")
	public String users(SessionMap<String, Object> sessionMap,@Param("page")String number,ModelMap<String, Object> map) throws SQLException {
		//获取页号
		int page = 1;
		int size = 5;
		try {
			page = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			//URL参数上没有页号的时候
			Integer p = (Integer) sessionMap.get("userPageNo");
			if (p != null) {
				page = p;
			}
		}
		//page当前页号
		sessionMap.put("userPageNo", page);
		int start = (page - 1) * size;
		//调用Dao获取数据
		UserDao dao = new UserDaoImpl();
		List<User> users = dao.findAllByPage(start, size);
		int row = dao.countAll();
		int pages = row / size;
		if(row % size != 0) {
			pages++;
		}
		//转发到list.jsp
		map.put("pages", pages);
		map.put("users", users);
		return "user/userlist";
	}

}
