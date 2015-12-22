/**
 * 
 */
package agenda.command;

import java.io.*;
import java.util.List;

import org.jdom.JDOMException;

import agenda.domain.*;
import agenda.exception.*;
import agenda.xmlAccess.*;




/**����������ܣ�ɾ��һ���û��趨�����л���
 * @version 1.0
 */
public class ClearCommand implements Command{
	/**˽�����ݳ�Ա�����ڱ����û���Ϣ*/
	private User user;
	
	/**��̬���������Ա������ʵ���˹����ӿڵ������ڲ���ʵ��*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new ClearCommand();
		}
	};
	
	/**˽�й��캯����ֻ��ͨ��������ȡʵ��
	 * 
	 */
	private ClearCommand(){
		user=null;
	}
	
	/**�������ܣ��Բ����б���кϷ��Լ��
	 * @param param �����б�
	 * @throws AgendaException
	 */
    public void parse(List param)throws AgendaException,IOException,JDOMException
    {
    	if(param!=null){
			int num=param.size();
			if(num==2){
				User u=new User((String)param.get(0),(String)param.get(1));
				if(UserDataAccessor.getInstance().userValidate(u)){
					user=u;
				}
				else throw new PasswordWrongException();
			}
			else throw new ParameterNumWrongException();
		}
		else throw new NoParameterException();
	}

    /**�������ܣ�ɾ��һ���û��趨�����л���
     * 
     */
	public String[] excute()throws AgendaException,IOException,JDOMException{
		String[] re = new String[1];
		MeetingDataAccessor.getInstance().clear(user);
		re[0] = "�ɹ�������û����������л���.";
		return re;
	}

}
