package agenda.command;

import java.util.*;

/**帮助命令类
 *
 */
public class HelpCommand implements Command{
	/**静态工厂对象，返回实现工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new HelpCommand();
		}
	};

	/**
	 *  私有构造函数，只能通过工厂获取实例
	 */
	private HelpCommand(){
		super();
	}
	
	/**不需要任何参数，因为该函数重写为空函数
	 * */
	public void parse(List param){
		
	}
	
	/**
	 * 显示系统命令格式
	 */
	public String[] excute(){
		String[] re = new String[10];
		re[0] = "*****************************************************************************";
		re[1] = "系统命令格式如下：";
		re[2] = "$register[userName][password]---------------------------- 用户注册";
		re[3] = "$add[userName][password][anotherUserName][startTime][endTime][label]- 添加会议";
		re[4] = "$query[userName][password][startTime][endTime]----------- 查询某段时间内的会议";
		re[5] = "$delete[userName][password][meetingLabel]---------------- 删除某个会议";
		re[6] = "$clear[userName][password]------------------------------- 清除某用户所有会议";
		re[7] = "$help---------------------------------------------------- 系统命令格式帮助";
		re[8] = "所有日期时间的规定格式为：yyyy-MM-dd,HH:mm";
		re[9] = "*****************************************************************************";
		return re;
	}
}
