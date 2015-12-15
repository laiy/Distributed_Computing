// 服务端主程序

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleFTPServer {
	public static void main(String[] args) throws IOException {
		System.out.println("client connecting....please wait!");
		ServerSocket serverSocket = new ServerSocket(21);
		Socket connectToClient = null;

		while (true) {
			connectToClient = serverSocket.accept();
			new ServerThread(connectToClient);
		}
	}
}
