// ����XML�ļ�������
public class SimpleXML {

	public static void main(String[] args) {
		// TODO �Զ����ɷ������
		try {
			UserDataAccessor uda = new UserDataAccessor();
			User user = new User("Jane", "789");
			uda.userRegister(user);
			if (uda.hasUser("Jane"))
				System.out
						.println("user register succeed! and user serch works!");
			else
				System.out.println("It doesn't work!");
			if (uda.userValidate(user))
				System.out.println("user validation works!");
			else
				System.out.println("user validation doesn't works!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
