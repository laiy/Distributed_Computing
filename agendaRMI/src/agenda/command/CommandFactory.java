// �����
package agenda.command;

public class CommandFactory {
	// ˽�о�̬ʵ����Ա*/
	private static CommandFactory factory;
	
	// ˽�й��캯����ʵ�ֵ�ʵ��ģʽ
	private CommandFactory(){
		
	}
	
	// ��̬�����������������Ψһʵ��������
	public static CommandFactory getInstance(){
		if(factory==null) factory=new CommandFactory();
		return factory;
	}

	// ������������������ʵ��
	public Command createCommand(String cmdName){
		cmdName = cmdName.trim();
		Command cmd = null;
		if(cmdName.equalsIgnoreCase("register")) cmd = RegisterCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("add")) cmd = AddCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("delete")) cmd = DeleteCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("query")) cmd = QueryCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("clear")) cmd = ClearCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("help")) cmd = HelpCommand.factory.createCommand();
		return cmd;
	}
}