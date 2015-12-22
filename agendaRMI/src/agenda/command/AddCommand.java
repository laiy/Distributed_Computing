// 添加命令类，扩展Command接口
package agenda.command;

import java.io.*;
import java.util.*;
import org.jdom.*;

import agenda.domain.*;
import agenda.exception.*;
import agenda.xmlAccess.*;

public class AddCommand implements Command{
	// 私有成员，保存要添加的会议的信息
	private Meeting meeting;
	
	// 静态工厂对象，返回实现CmdFactory接口的内部类的实例
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new AddCommand();
		}
	};
	
	// 私有构造函数，只能通过工厂创建对象
	private AddCommand(){
		meeting=null;
	}
	
	// 功能：对Add命令的参数进行合法性检查
	public void parse(List param)throws AgendaException,IOException,JDOMException
	{
		if(param!=null){
			int num=param.size();
			if(num==6){
				User u=new User((String)param.get(0),(String)param.get(1));
				if(UserDataAccessor.getInstance().userValidate(u)){
					if(UserDataAccessor.getInstance().hasUser((String)param.get(2))){
						TimeInterval ti=new TimeInterval((String)param.get(3),(String)param.get(4));
						meeting=new Meeting((String)param.get(0),(String)param.get(2),ti,(String)param.get(5));
					}
					else throw new UserNotRegisteredException((String)param.get(2));
				}
				else throw new PasswordWrongException();	
			}
			else throw new ParameterNumWrongException();
		}
		else throw new NoParameterException();
	}

	// 功能:执行添加会议的命令
	public String[] excute()throws AgendaException,JDOMException,IOException{
		String[] re = new String[1];	
		MeetingDataAccessor.getInstance().add(meeting);
		re[0] = "添加会议成功！";
		return re;
	}
	
	/**嵌入式测试
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		User tim=new User("Tim","123");
		User amber=new User("Amber","456");
		UserDataAccessor.getInstance().userRegister(tim);
		UserDataAccessor.getInstance().userRegister(amber);
		List param=new ArrayList();
		param.add("Tim");
		param.add("123");
		param.add("Amber");
		param.add("2012-10-03,12:00");
		param.add("2012-10-03,24:00");
		param.add("marriage");
		AddCommand ac=new AddCommand();
	    ac.parse(param);
		ac.excute();
		}catch(AgendaException e){
			e.printInfo();
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
