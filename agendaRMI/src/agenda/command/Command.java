// 命令接口
package agenda.command;
import java.util.*;
import java.io.IOException;
import org.jdom.JDOMException;

import agenda.exception.*;

public interface Command {
	/**对参数列表进行检查*/
	public void parse(List param)throws AgendaException,IOException,JDOMException;
	/**执行命令*/
	public String[] excute()throws AgendaException,IOException,JDOMException;
}