
package agenda.exception;

/**
 * @author Administrator
 *
 */
public class MeetingNotExistException extends AgendaException{
	private static final long serialVersionUID = 1L; 

	public MeetingNotExistException(){
		super();
	}
	
	public void printInfo(){
		System.out.println("�����ڸû����û�з��������Ļ���!");
	}
	
	public String getInfo(){
		return "�����ڸû����û�з��������Ļ���!";
	}
}
