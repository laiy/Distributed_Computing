package agenda.exception;

public class PasswordWrongException extends AgendaException{
	private static final long serialVersionUID = 1L; 

	public PasswordWrongException(){
		super();
	}

	public void printInfo(){
		System.out.println("�û��������!");
	}
	
	public String getInfo(){
		return "�û��������!";
	}
}
