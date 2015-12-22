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
		System.out.println("用户 "+name+" 还没有注册，请先注册!");
	}
	
	public String getInfo(){
		return "用户 "+name+" 还没有注册，请先注册!";
	}
}