package agenda.command;

import java.io.*;
import java.util.*;
import org.jdom.JDOMException;

import agenda.domain.*;
import agenda.exception.*;
import agenda.xmlAccess.*;




/**ɾ��һ�����������࣬ʵ������ӿ�
 * 
 *
 */
public class DeleteCommand implements Command{
	/**��ɾ������ı�ǩ*/
	private String label;
	/**�û�*/
	private User user;

	/**��̬�������󣬷���ʵ�ֹ����ӿڵ������ڲ���ʵ��*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new DeleteCommand();
		}
	};
	
	/**˽�й��캯����ֻ��ͨ��������ȡʵ��
	 *
	 */
	private DeleteCommand()
	{
		label="";
		user=null;
	}
	
	/**�������ܣ��Բ������кϷ��Լ��
	 * @param param �����б�
	 * @throws AgendaException
	 */
    public void parse(List param)throws AgendaException,IOException,JDOMException
    {
    	if(param!=null){
    		int num=param.size();
    		if(num==3){
    			User u=new User((String)param.get(0),(String)param.get(1));
    			if(UserDataAccessor.getInstance().userValidate(u)){
    				user=u;
    				label=(String)param.get(2);
    			}
    			else throw new PasswordWrongException();
    		}
    		else throw new ParameterNumWrongException();
    	}
    	else throw new NoParameterException();
	}

    /**
     * ɾ��ָ������
     * @throws AgendaException 
     */
	public String[] excute()throws AgendaException,JDOMException,IOException
	{
		String[] re = new String[1];
		MeetingDataAccessor.getInstance().delete(user, label);
		re[0] = "ɾ������ɹ���";
		return re;
	}
}
