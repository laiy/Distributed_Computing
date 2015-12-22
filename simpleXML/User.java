// 用户类
public class User {
	// 用户名
	private String userName;
	// 密码
	private String password;
	
	// 构造函数
	public User(String name,String password){
		this.userName=name;
		this.password=password;
	}

	// 判断两个用户是否相同
	public boolean equals(User another){
		return userName.equals(another.getUserName())&&password.equals(another.getPassword());
	}
	
	public String toString(){
		return "用户名："+userName+" 密码："+password;
	}
	
	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
