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
		System.out.println("��ȷ����ʱ���ʽӦΪ��yyyy-MM-dd,HH:mm:ss");
	}
	
	public String getInfo(){
		return "��ȷ����ʱ���ʽӦΪ��yyyy-MM-dd,HH:mm:ss";
	}
}
