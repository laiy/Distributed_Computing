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




/**清理命令，功能：删除一个用户设定的所有会议
 * @version 1.0
 */
public class ClearCommand implements Command{
	/**私有数据成员，用于保存用户信息*/
	private User user;
	
	/**静态工厂对象成员，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new ClearCommand();
		}
	};
	
	/**私有构造函数，只能通过工厂获取实例
	 * 
	 */
	private ClearCommand(){
		user=null;
	}
	
	/**函数功能：对参数列表进行合法性检查
	 * @param param 参数列表
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

    /**函数功能：删除一个用户设定的所有会议
     * 
     */
	public String[] excute()throws AgendaException,IOException,JDOMException{
		String[] re = new String[1];
		MeetingDataAccessor.getInstance().clear(user);
		re[0] = "成功清除该用户创建的所有会议.";
		return re;
	}

}
