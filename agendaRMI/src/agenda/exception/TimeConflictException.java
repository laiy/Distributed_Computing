/**
 * 
 */
package agenda.exception;

/**
 * @author Administrator
 *
 */
public class TimeConflictException extends AgendaException{
	private static final long serialVersionUID = 1L; 

	public TimeConflictException(){
		super();
	}

	public void printInfo(){
		System.out.println("��ϵͳ�����������ͻ��û����ӳɹ�!");
	}
	
	public String getInfo(){
		return "��ϵͳ�����������ͻ��û����ӳɹ�!";
	}
}
