// �û���
public class User {
	// �û���
	private String userName;
	// ����
	private String password;
	
	// ���캯��
	public User(String name,String password){
		this.userName=name;
		this.password=password;
	}

	// �ж������û��Ƿ���ͬ
	public boolean equals(User another){
		return userName.equals(another.getUserName())&&password.equals(another.getPassword());
	}
	
	public String toString(){
		return "�û�����"+userName+" ���룺"+password;
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
