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
		System.out.println("时间区间逻辑错误，终点应迟于起点!");
	}
	
	public String getInfo(){
		return "时间区间逻辑错误，终点应迟于起点!";
	}
}
