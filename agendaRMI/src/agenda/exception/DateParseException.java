package agenda.exception;

/**
 * @author Administrator
 *
 */
public class DateParseException extends AgendaException {
	private static final long serialVersionUID = 1L; 

	public DateParseException(){
		super();
	}

	public void printInfo(){
		System.out.println("正确日期时间格式应为：yyyy-MM-dd,HH:mm:ss");
	}
	
	public String getInfo(){
		return "正确日期时间格式应为：yyyy-MM-dd,HH:mm:ss";
	}
}
