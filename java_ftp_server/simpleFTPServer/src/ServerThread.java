// 连接客户端

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
	private static Socket connectToClient;
	private static DataOutputStream sendData;
	private static DataInputStream receiveData;

	public ServerThread(Socket socket) {
		connectToClient = socket;
		try {
			receiveData = new DataInputStream(connectToClient.getInputStream());
			sendData = new DataOutputStream(connectToClient.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	public void run() {
		String receive = "";
		String[] cmd;
		while (true) {
			try {
				receive = receiveData.readUTF();
				cmd = receive.split(" ");
				System.out.println(receive);
				try {
					ServerCmd c = (ServerCmd) Class.forName("server." + cmd[0])
							.newInstance();
					String str = c.func(connectToClient, cmd);
				} catch (InstantiationException e) {
					System.out.println("Instantiation！");
					sendData.writeUTF("Instantiation");
				} catch (IllegalAccessException e) {
					System.out.println("Illegal access！");
					sendData.writeUTF("Illegal access");
				} catch (ClassNotFoundException e) {
					System.out.println("Class not found！");
					sendData.writeUTF("Class not found");
				}
			} catch (Exception e) {
				serverSocketClosing();
				System.out.println("connection terminate!");
				break;
			}
		}
	}

	public static void serverSocketClosing() {
		try {
			receiveData.close();
			sendData.close();
			connectToClient.close();
		} catch (Exception e) {
			System.out.println("close socket fail!");
		}
	}
}
