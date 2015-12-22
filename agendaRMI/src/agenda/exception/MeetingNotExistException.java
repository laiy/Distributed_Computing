
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
		System.out.println("不存在该会议或没有符合条件的会议!");
	}
	
	public String getInfo(){
		return "不存在该会议或没有符合条件的会议!";
	}
}
