// AgendaService�ӿ�ʵ��
package agenda.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.jdom.JDOMException;
import agenda.command.Command;
import agenda.command.CommandFactory;
import agenda.exception.AgendaException;

public class AgendaServiceImplement extends java.rmi.server.UnicastRemoteObject
		implements
			AgendaServiceInterface {
	private static final long serialVersionUID = 1L;

	public AgendaServiceImplement() throws java.rmi.RemoteException {

	}

	// ִ������
	public String[] excuteCommand(String command) throws AgendaException,
			RemoteException, IOException, JDOMException {
		System.out.println("����ͻ�����" + command);
		List param = new ArrayList();
		StringTokenizer st = new StringTokenizer(command, "[]");
		String cmdName = st.nextToken().trim();
		while (st.hasMoreTokens())
			param.add(st.nextToken().trim());
		String[] re;
		Command cmd = CommandFactory.getInstance().createCommand(cmdName);
		if (cmd != null) {
			cmd.parse(param);
			re = cmd.excute();
		} else {
			re = new String[1];
			re[0] = "û�и���������������Ƿ���ȷ��";
		}
		return re;
	}
}
