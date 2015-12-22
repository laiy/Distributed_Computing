// 注册命令类，扩展Command接口
package agenda.command;

import java.util.*;
import java.io.IOException;
import org.jdom.JDOMException;
import agenda.domain.*;
import agenda.exception.*;
import agenda.xmlAccess.*;

public class RegisterCommand implements Command{
	// 需要注册的用户
	private User user;
	// 静态工厂实例，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new RegisterCommand();
		}
	};

	// 私有构造函数，只能通过工厂获取对象实例
	private RegisterCommand(){
		user=null;
	}
	
	// 对参数进行合法性检查
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
	
	// 注册用户
	public String[] excute()throws AgendaException,IOException,JDOMException{
		String[] re = new String[1];
		UserDataAccessor.getInstance().userRegister(user);
		re[0] = "注册成功。";
		return re;
	}
}