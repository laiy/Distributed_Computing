/**
 * 
 */
package agenda.exception;

/**
 * @author Administrator
 *
 */
public class ParameterNumWrongException extends AgendaException{
	private static final long serialVersionUID = 1L; 

	public ParameterNumWrongException(){
		super();
	}

	public void printInfo(){
		System.out.println("������������ȷ����������!");
	}
	
	public String getInfo(){
		return "������������ȷ����������!";
	}
}
