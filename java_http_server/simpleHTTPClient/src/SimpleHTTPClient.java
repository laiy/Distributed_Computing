// 基于命令行界面的HTTP客户程序
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleHTTPClient {
	static final String endMessage = ".";
	public static void main(String[] args) {
		// 控制台的输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("\n请输入服务器主机名(默认主机名为localhost)：");
			String hostName = br.readLine();
			if (hostName.length() == 0)
				hostName = "localhost";
			System.out.println("\n请输入服务器主机端口号(默认端口号为8000)：");
			String portNum = br.readLine();
			System.out.println(portNum);
			if (portNum.length() == 0)
				portNum = "8000";
			SimpleHTTPClientHelper helper = new SimpleHTTPClientHelper(
					hostName, portNum);
			boolean done = false;
			String message;
			System.out.println("\n输入格式：<HTTP method><space><Request-URI>\n"
					+ "示例：GET /index.html\n" + "如要退出请输入 . \n");
			while (!done) {
				System.out.println("\n请输入您的命令>");
				message = br.readLine();
				if ((message.trim()).equals(endMessage)) {
					done = true;
					helper.close();
				} else if ((helper.getCommand(message)).equals("GET")) {
					String req = helper.getCommand(message);
					String uri = helper.getUri(message);
					helper.process(req, uri);
				} else {
					System.out.println("您输入的命令有误，请重新输入！");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}