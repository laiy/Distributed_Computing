// �ͻ���������
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jdom.JDOMException;

import agenda.exception.AgendaException;
import agenda.service.AgendaServiceInterface;

public class agendaRMIClient {

	public static void main(String[] args) {
		String serverIP = null;
		String serverPort = null;
		String cmd = null;
		List param = new ArrayList();
		if (args.length == 0) {
			try {
				System.out.println("��ӭʹ����̹���ϵͳ�� \n����ʹ��help����鿴�����ʽ��");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				do {
					System.out.print("������ע�������ip��ַ��");
					serverIP = br.readLine();
				} while (serverIP == null || "".equals(serverIP.trim()));
				do {
					System.out.print("������ע�����������˿ڣ�(ȱʡΪ1099)");
					serverPort = br.readLine();
				} while (serverPort == null || "".equals(serverPort.trim()));
				Registry registry = LocateRegistry.getRegistry(serverIP,
						Integer.parseInt(serverPort));
				AgendaServiceInterface asi = (AgendaServiceInterface) registry
						.lookup("AgendaService");

				String[] re = null;
				while (true) {
					try {
						param.clear();
						System.out.print("����������>");
						cmd = br.readLine();
						if (cmd.trim().equals("quit")) {
							System.out.println("��̹���ϵͳ�ͻ�������ֹ...");
							System.exit(1);
						}
						if (!"".equals(cmd.trim())) {
							// ����Զ�̷������ִ������
							re = asi.excuteCommand(cmd);
							for (int i = 0; i < re.length; i++) {
								System.out.println(re[i]);
							}
						}
					} catch (AgendaException exc) {
						exc.printInfo();
					} catch (RemoteException exc) {
						System.out.println("����Զ�̷������ʱ�����쳣��");
					} catch (IOException exc) {
						System.out.println("����Զ�̷������ʱ����IO�쳣��");
					} catch (JDOMException exc) {
						System.out.println("����Զ�̷������ʱ����JDOM�쳣��");
					}
				}
			} catch (IOException exc) {
				exc.printStackTrace();
			} catch (NotBoundException exc) {
				System.out.println("����Զ�̷������δע���쳣��");
				exc.printStackTrace();
			}
		} else if (args.length >= 5) {
			StringTokenizer st = new StringTokenizer(args[0], "[]");
			serverIP = st.nextToken().trim();
			st = new StringTokenizer(args[1], "[]");
			serverPort = st.nextToken().trim();
			cmd = "";
			for (int i = 2; i < args.length; i++)
				cmd += args[i];
			try {
				// ��ȡԶ�̷������
				Registry registry = LocateRegistry.getRegistry(serverIP,
						Integer.parseInt(serverPort));
				AgendaServiceInterface asi = (AgendaServiceInterface) registry
						.lookup("AgendaService");
				// ����Զ�̷������ִ������
				String[] re = asi.excuteCommand(cmd);
				for (int i = 0; i < re.length; i++) {
					System.out.println(re[i]);
				}
			} catch (NotBoundException exc) {
				System.out.println("����Զ�̷������δע���쳣��");
			} catch (AgendaException exc) {
				exc.printInfo();
			} catch (RemoteException exc) {
				System.out.println("����Զ�̷������ʱ�����쳣��");
			} catch (IOException exc) {
				System.out.println("����Զ�̷������ʱ�����ļ���д�쳣��");
			} catch (Exception exc) {
				System.out.println("����Զ�̷������ʱ�����쳣��");
			}
		} else {
			System.out.println("������������");
		}
	}
}
