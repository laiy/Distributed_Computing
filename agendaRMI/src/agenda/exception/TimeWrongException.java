/**
 * 
 */
package agenda.exception;

/**
 * @author Administrator
 *
 */
public class TimeWrongException extends AgendaException{
	private static final long serialVersionUID = 1L; 

	public TimeWrongException(){
		super();
	}

	public void printInfo(){
		System.out.println("ʱ�������߼������յ�Ӧ�������!");
	}
	
	public String getInfo(){
		return "ʱ�������߼������յ�Ӧ�������!";
	}
}
