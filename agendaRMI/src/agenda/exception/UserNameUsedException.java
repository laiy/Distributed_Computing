/**
 * 
 */
package agenda.exception;

/**
 * @author Administrator
 *
 */
public class UserNameUsedException extends AgendaException{
	private static final long serialVersionUID = 1L; 

	public  UserNameUsedException(){
		super();
	}

	public void printInfo(){
		System.out.println("���û����Ѿ���ʹ�ã�������ѡ��һ���û�����");
	}
	
	public String getInfo(){
		return "���û����Ѿ���ʹ�ã�������ѡ��һ���û�����";
	}
}
