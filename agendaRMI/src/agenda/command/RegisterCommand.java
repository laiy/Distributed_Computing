// ע�������࣬��չCommand�ӿ�
package agenda.command;

import java.util.*;
import java.io.IOException;
import org.jdom.JDOMException;
import agenda.domain.*;
import agenda.exception.*;
import agenda.xmlAccess.*;

public class RegisterCommand implements Command{
	// ��Ҫע����û�
	private User user;
	// ��̬����ʵ��������ʵ���˹����ӿڵ������ڲ���ʵ��*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new RegisterCommand();
		}
	};

	// ˽�й��캯����ֻ��ͨ��������ȡ����ʵ��
	private RegisterCommand(){
		user=null;
	}
	
	// �Բ������кϷ��Լ��
	public void parse(List param)throws AgendaException{
		if(param!=null){
			int num=param.size();
			if(num==2){
			    user=new User((String)param.get(0),(String)param.get(1));
			}
			else throw new ParameterNumWrongException();
		}
		else throw new NoParameterException();
	}
	
	// ע���û�
	public String[] excute()throws AgendaException,IOException,JDOMException{
		String[] re = new String[1];
		UserDataAccessor.getInstance().userRegister(user);
		re[0] = "ע��ɹ���";
		return re;
	}
}