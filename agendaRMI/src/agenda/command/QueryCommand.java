package agenda.command;

import java.util.*;
import java.io.IOException;
import org.jdom.JDOMException;

import agenda.domain.*;
import agenda.exception.*;
import agenda.xmlAccess.*;




/**��ѯ�����࣬��ѯĳ���û���ָ��ʱ�������ڵĻ��鰲��
 * 
 *
 */
public class QueryCommand implements Command{
	/**�û�*/
	private User user;
	/**ָ��ʱ������*/
	private TimeInterval interval;
	/**��ѯ���*/
	private ArrayList meetings;
	
	/**˽�й��캯����ֻ��ͨ��������������
	 * */
	private QueryCommand(){
		user=null;
		interval=null;
		meetings=null;
	}
	
	/**��̬����ʵ��������ʵ�ֹ����ӿڵ������ڲ���ʵ��*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new QueryCommand();
		}
	};
	
	/**�������ܣ��Բ������кϷ������
	 * @param param �����б�
	 * @throws AgendaException
	 */
    public void parse(List param)throws AgendaException
    {
    	if(param!=null){
			int num=param.size();
			if(num==4){
				User u=new User((String)param.get(0),(String)param.get(1));
				try{
					if(UserDataAccessor.getInstance().userValidate(u)){
						user=u;
					    interval=new TimeInterval((String)param.get(2),(String)param.get(3));
					}
					else throw new PasswordWrongException();
				}catch(UserNotRegisteredException e){
					throw e;
				}catch(TimeWrongException e){
					throw e;
				}catch(DateParseException e){
					throw e;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else throw new ParameterNumWrongException();
		}
		else throw new NoParameterException();
		
	}

    /**ִ�в�ѯ����
     * @throws AgendaException
     * @return ���û���ָ��ʱ������Ļ����б�
     */
	public String[] excute() throws AgendaException,IOException,JDOMException{
		String[] re = null;
		meetings = MeetingDataAccessor.getInstance().query(user, interval);
		Meeting m = null;
		if(meetings != null && meetings.size() != 0){
			re = new String[meetings.size()+2];
			re[0] = "------------------------------------------------------------";
			int i;
			for(i=0;i<meetings.size();i++){
				m = (Meeting)meetings.get(i);
				re[i+1] = m.toString();
			}
			re[i+1] = "------------------------------------------------------------";
		}
		else {
			re = new String[1];
			re[0] = "û�з��������Ļ��鰲�š�";
		}
		return re;
	}

}
