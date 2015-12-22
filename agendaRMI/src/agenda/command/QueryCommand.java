package agenda.command;

import java.util.*;
import java.io.IOException;
import org.jdom.JDOMException;

import agenda.domain.*;
import agenda.exception.*;
import agenda.xmlAccess.*;




/**查询命令类，查询某个用户在指定时间区间内的会议安排
 * 
 *
 */
public class QueryCommand implements Command{
	/**用户*/
	private User user;
	/**指定时间区间*/
	private TimeInterval interval;
	/**查询结果*/
	private ArrayList meetings;
	
	/**私有构造函数，只能通过工厂创建对象
	 * */
	private QueryCommand(){
		user=null;
		interval=null;
		meetings=null;
	}
	
	/**静态工厂实例，返回实现工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new QueryCommand();
		}
	};
	
	/**函数功能：对参数进行合法化检查
	 * @param param 参数列表
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

    /**执行查询命令
     * @throws AgendaException
     * @return 该用户在指定时间区间的会议列表
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
			re[0] = "没有符合条件的会议安排。";
		}
		return re;
	}

}
