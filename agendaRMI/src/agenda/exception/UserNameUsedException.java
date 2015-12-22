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
		System.out.println("该用户名已经被使用，请重新选择一个用户名！");
	}
	
	public String getInfo(){
		return "该用户名已经被使用，请重新选择一个用户名！";
	}
}
