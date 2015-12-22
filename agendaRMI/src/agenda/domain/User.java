package agenda.domain;
// User��

public class User {
	/** �û��� */
	private String userName;
	/** ���� */
	private String password;

	/**
	 * ���캯��
	 * 
	 * @param name
	 *            �û���
	 * @param password
	 *            �û�����
	 */
	public User(String name, String password) {
		this.userName = name;
		this.password = password;
	}

	/**
	 * �ж������û��Ƿ���ͬ
	 * 
	 * @param another
	 *            ��һ�û�
	 * @return ��ͬ����true�����򷵻�false
	 */
	public boolean equals(User another) {
		return userName.equals(another.getUserName())
				&& password.equals(another.getPassword());
	}

	/**
	 * ���л�����
	 */
	public String toString() {
		return "�û�����" + userName + " ���룺" + password;
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
	 *            Ҫ���õ� password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param userName
	 *            Ҫ���õ� userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
