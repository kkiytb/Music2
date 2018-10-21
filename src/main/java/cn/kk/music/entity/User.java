package cn.kk.music.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class User implements Serializable{
	private static final long serialVersionUID = 2370394569671195267L;
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String mobile;
	private Date createdate;
	private int[] roles;
	
	public User() {
	}
	
	public User(Integer id, String username, String password, String email, String mobile, Date createdate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.createdate = createdate;
	}
	public User(Integer id, String username, String password, String email, String mobile, Date createdate,
			int[] roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.createdate = createdate;
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", mobile="
				+ mobile + ", createdate=" + createdate + ", roles=" + Arrays.toString(roles) + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public void setRoles(int[] roles) {
		this.roles = roles;
	}
	public int[] getRoles() {
		return roles;
	}
	
	
}
