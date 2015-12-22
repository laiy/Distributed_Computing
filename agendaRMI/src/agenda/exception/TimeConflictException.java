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
		System.out.println("与系统中其他会议冲突，没有添加成功!");
	}
	
	public String getInfo(){
		return "与系统中其他会议冲突，没有添加成功!";
	}
}
