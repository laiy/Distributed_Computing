// ����ӿ�
package agenda.command;
import java.util.*;
import java.io.IOException;
import org.jdom.JDOMException;

import agenda.exception.*;

public interface Command {
	/**�Բ����б���м��*/
	public void parse(List param)throws AgendaException,IOException,JDOMException;
	/**ִ������*/
	public String[] excute()throws AgendaException,IOException,JDOMException;
}