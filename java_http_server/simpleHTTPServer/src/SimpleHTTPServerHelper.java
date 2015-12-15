// HTTP服务程序帮助类
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.StringTokenizer;

public class SimpleHTTPServerHelper {
	private final static String CRLF = System.getProperty("line.separator");
	private StreamSocket streamSocket;

	public SimpleHTTPServerHelper(Socket socket) throws Exception {
		streamSocket = new StreamSocket(socket);
	}

	// 处理 HTTP客户端的请求
	public void processRequest() throws Exception {
		String statusLine = null;
		String entityBody = null;
		// 读取并显示 HTTP客户端提交的请求信息
		while (true) {
			String headerLine = streamSocket.receiveMessage();
			if (headerLine.trim().equals(".")) {
				System.out.println("会话结束！");
				break;
			} else {
				if (headerLine.length() != 0)
					System.out.println("客户端的请求是：" + headerLine);
				StringTokenizer s = new StringTokenizer(headerLine);
				if ((s.countTokens() >= 2) && s.nextToken().equals("GET")) {
					// 从请求中取出文档的文件名，支持默认索引文件
					String filename = s.nextToken();
					if (filename.startsWith("/"))
						filename = filename.substring(1);
					if (filename.endsWith("/"))
						filename += "index.html";
					if (filename.equals(""))
						filename += "index.html";
					// 打开所请求的文件
					FileInputStream fis = null;
					boolean fileExists = true;
					try {
						fis = new FileInputStream(filename);
						fis.close();
					} catch (FileNotFoundException e) {
						fileExists = false;
					}
					// 读取文件中的内容并写到socket的输出流
					if (fileExists) {
						statusLine = "HTTP/1.0 200 OK" + CRLF;
					} else {
						statusLine = "HTTP/1.0 404 Not Found" + CRLF;
						entityBody = "＜HTML＞" + "＜HEAD＞" + CRLF
								+ "＜TITLE＞404 Not Found＜/TITLE＞" + CRLF
								+ "＜/HEAD＞＜BODY＞" + CRLF + "<h1>Not Found</h1>"
								+ CRLF + "<p>The request URI " + filename
								+ " was not found on this server.</p>" + CRLF
								+ "＜/BODY＞＜/HTML＞" + CRLF;
					}
					streamSocket.sendMessage(statusLine);
					streamSocket.sendMessage(CRLF);
					if (fileExists) {
						streamSocket.sendBytes(filename);
						streamSocket.sendMessage(CRLF);
						streamSocket.sendMessage(CRLF);
					} else {
						streamSocket.sendMessage(entityBody);
						streamSocket.sendMessage(CRLF);
					}
				}
			}
		}
	}
}