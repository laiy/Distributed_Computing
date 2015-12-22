package agenda.command;

import java.util.*;

/**����������
 *
 */
public class HelpCommand implements Command{
	/**��̬�������󣬷���ʵ�ֹ����ӿڵ������ڲ���ʵ��*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new HelpCommand();
		}
	};

	/**
	 *  ˽�й��캯����ֻ��ͨ��������ȡʵ��
	 */
	private HelpCommand(){
		super();
	}
	
	/**����Ҫ�κβ�������Ϊ�ú�����дΪ�պ���
	 * */
	public void parse(List param){
		
	}
	
	/**
	 * ��ʾϵͳ�����ʽ
	 */
	public String[] excute(){
		String[] re = new String[10];
		re[0] = "*****************************************************************************";
		re[1] = "ϵͳ�����ʽ���£�";
		re[2] = "$register[userName][password]---------------------------- �û�ע��";
		re[3] = "$add[userName][password][anotherUserName][startTime][endTime][label]- ��ӻ���";
		re[4] = "$query[userName][password][startTime][endTime]----------- ��ѯĳ��ʱ���ڵĻ���";
		re[5] = "$delete[userName][password][meetingLabel]---------------- ɾ��ĳ������";
		re[6] = "$clear[userName][password]------------------------------- ���ĳ�û����л���";
		re[7] = "$help---------------------------------------------------- ϵͳ�����ʽ����";
		re[8] = "��������ʱ��Ĺ涨��ʽΪ��yyyy-MM-dd,HH:mm";
		re[9] = "*****************************************************************************";
		return re;
	}
}
