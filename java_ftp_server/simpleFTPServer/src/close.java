// 关闭客户端的连接

import java.io.DataOutputStream;
import java.net.Socket;

public class close extends ServerCmd {
	private Socket s;
	public String func(Socket s, String[] cmd) {
		this.s = s;

		try {
			DataOutputStream sendData = new DataOutputStream(s
					.getOutputStream());
			sendData.writeUTF("goodbye!00");
			ServerThread.serverSocketClosing();
			s.close();
		} catch (Exception e) {
			System.out.println("close socker fail!");
		}
		return "goodbye!";
	}
}
