
package agenda.exception;

/**
 * @author Administrator
 *
 */
public class NoParameterException extends AgendaException{
	private static final long serialVersionUID = 1L; 

	public NoParameterException(){
		super();
	}

	public void printInfo(){
		System.out.println("û���ṩӦ�еĲ�����");
	}
	
	public String getInfo(){
		return "û���ṩӦ�еĲ�����";
	}
}
