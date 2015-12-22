// 客户端主程序
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
				System.out.println("欢迎使用议程管理系统～ \n可以使用help命令查看命令格式～");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				do {
					System.out.print("请输入注册服务器ip地址：");
					serverIP = br.readLine();
				} while (serverIP == null || "".equals(serverIP.trim()));
				do {
					System.out.print("请输入注册服务器服务端口：(缺省为1099)");
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
						System.out.print("请输入命令>");
						cmd = br.readLine();
						if (cmd.trim().equals("quit")) {
							System.out.println("议程管理系统客户端已终止...");
							System.exit(1);
						}
						if (!"".equals(cmd.trim())) {
							// 调用远程服务对象执行命令
							re = asi.excuteCommand(cmd);
							for (int i = 0; i < re.length; i++) {
								System.out.println(re[i]);
							}
						}
					} catch (AgendaException exc) {
						exc.printInfo();
					} catch (RemoteException exc) {
						System.out.println("调用远程服务对象时发生异常！");
					} catch (IOException exc) {
						System.out.println("调用远程服务对象时发生IO异常！");
					} catch (JDOMException exc) {
						System.out.println("调用远程服务对象时发生JDOM异常！");
					}
				}
			} catch (IOException exc) {
				exc.printStackTrace();
			} catch (NotBoundException exc) {
				System.out.println("发生远程服务对象未注册异常！");
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
				// 获取远程服务对象
				Registry registry = LocateRegistry.getRegistry(serverIP,
						Integer.parseInt(serverPort));
				AgendaServiceInterface asi = (AgendaServiceInterface) registry
						.lookup("AgendaService");
				// 调用远程服务对象执行命令
				String[] re = asi.excuteCommand(cmd);
				for (int i = 0; i < re.length; i++) {
					System.out.println(re[i]);
				}
			} catch (NotBoundException exc) {
				System.out.println("发生远程服务对象未注册异常！");
			} catch (AgendaException exc) {
				exc.printInfo();
			} catch (RemoteException exc) {
				System.out.println("调用远程服务对象时发生异常！");
			} catch (IOException exc) {
				System.out.println("调用远程服务对象时发生文件读写异常！");
			} catch (Exception exc) {
				System.out.println("调用远程服务对象时发生异常！");
			}
		} else {
			System.out.println("参数个数错误！");
		}
	}
}
