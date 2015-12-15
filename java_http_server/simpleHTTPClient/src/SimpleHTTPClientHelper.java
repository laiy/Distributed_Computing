// HTTP客户端程序帮助类
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SimpleHTTPClientHelper {
	private static final String endMessage = ".";
	private StreamSocket streamSocket;
	private String serverHost;
	private int serverPort;

	// 构造函数
	public SimpleHTTPClientHelper(String hostName, String portNum)
			throws UnknownHostException, IOException {
		this.serverHost = hostName;
		this.serverPort = Integer.parseInt(portNum);
		// 反馈给用户连接情况的提示
		try {
			streamSocket = new StreamSocket(serverHost, serverPort);
			System.out.println("连接成功！\n");
		} catch (UnknownHostException u) {
			System.out.println("找不到HTTP服务器,连接失败！");
			throw u;
		} catch (IOException i) {
			System.out.println("Socket连接失败！");
			throw i;
		}
	}

	// 从用户输入的命令行中取出命令
	public String getCommand(String message) {
		String cmd = "";
		// 找到第一个空格出现的位置
		int index = message.indexOf(" ");
		// 如果有空格则index之前就是命令,否则是一条没有参数的命令
		if (index >= 0)
			cmd = message.substring(0, index);
		else
			cmd = message;
		// 返回全部为大写字母的命令
		return cmd.toUpperCase();
	}

	// 从用户输入的命令行中取出URI
	public String getUri(String message) {
		int index = message.indexOf(" ");
		// 得到一个去掉命令的参数行
		if (index >= 0)
			message = message.substring(index + 1, message.length()).trim();
		else
			message = "";
		return message;
	}

	// 客户端和服务端进行交互
	public void process(String req, String uri) {
		String request = req + " " + uri + " HTTP/1.0\n\n";
		try {
			// 发送请求
			streamSocket.sendMessage(request);
			// 接收服务器端的响应
			String response = streamSocket.receiveMessage();
			// 显示头部信息
			while (response.length() != 0) {
				System.out.println(response);
				response = streamSocket.receiveMessage();
			}
			// 将消息体保存到文件中去
			FileOutputStream os = new FileOutputStream("MessageBody.txt");
			response = streamSocket.receiveMessage();
			while (response.length() != 0) {
				response += "\r\n";
				byte[] data = new byte[response.length()];
				data = response.getBytes();
				os.write(data, 0, data.length);
				response = streamSocket.receiveMessage();
			}
			os.close();
			System.out.println("消息体保存到MessageBody.txt!\n");
		} catch (Exception ex) {
			System.out.println("ERROR : " + ex);
			ex.printStackTrace(System.out);
		}
	}
	// 关闭Socket
	public void close() throws SocketException, IOException {
		streamSocket.sendMessage(endMessage);
		streamSocket.close();
	}
}
