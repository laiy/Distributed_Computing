/**
 * 
 */
package agenda.exception;

/**
 * @author Administrator
 *
 */
public class UserNotRegisteredException extends AgendaException{
	private static final long serialVersionUID = 1L; 

	String name;
	public UserNotRegisteredException(String name){
		super();
		this.name=name;
	}

	public void printInfo(){
		System.out.println("�û� "+name+" ��û��ע�ᣬ����ע��!");
	}
	
	public String getInfo(){
		return "�û� "+name+" ��û��ע�ᣬ����ע��!";
	}
}