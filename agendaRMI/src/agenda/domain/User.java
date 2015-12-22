package agenda.domain;
// User类

public class User {
	/** 用户名 */
	private String userName;
	/** 密码 */
	private String password;

	/**
	 * 构造函数
	 * 
	 * @param name
	 *            用户名
	 * @param password
	 *            用户密码
	 */
	public User(String name, String password) {
		this.userName = name;
		this.password = password;
	}

	/**
	 * 判断两个用户是否相同
	 * 
	 * @param another
	 *            另一用户
	 * @return 相同返回true，否则返回false
	 */
	public boolean equals(User another) {
		return userName.equals(another.getUserName())
				&& password.equals(another.getPassword());
	}

	/**
	 * 序列化函数
	 */
	public String toString() {
		return "用户名：" + userName + " 密码：" + password;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param password
	 *            要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param userName
	 *            要设置的 userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
