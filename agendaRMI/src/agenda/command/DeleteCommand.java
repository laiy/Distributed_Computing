package agenda.command;

import java.io.*;
import java.util.*;
import org.jdom.JDOMException;

import agenda.domain.*;
import agenda.exception.*;
import agenda.xmlAccess.*;




/**删除一个会议命令类，实现命令接口
 * 
 *
 */
public class DeleteCommand implements Command{
	/**待删除会议的标签*/
	private String label;
	/**用户*/
	private User user;

	/**静态工厂对象，返回实现工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new DeleteCommand();
		}
	};
	
	/**私有构造函数，只能通过工厂获取实例
	 *
	 */
	private DeleteCommand()
	{
		label="";
		user=null;
	}
	
	/**函数功能：对参数进行合法性检查
	 * @param param 参数列表
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
     * 删除指定会议
     * @throws AgendaException 
     */
	public String[] excute()throws AgendaException,JDOMException,IOException
	{
		String[] re = new String[1];
		MeetingDataAccessor.getInstance().delete(user, label);
		re[0] = "删除会议成功！";
		return re;
	}
}
