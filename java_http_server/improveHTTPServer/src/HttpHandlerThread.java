import http.ConstantValues;
import http.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import rfc.HttpHandler;
import rfc.HttpProtocol;

// 处理一个HTTP请求的线程
public class HttpHandlerThread implements Runnable {
	// 客户连接套接字
	StreamSocket client;
	// HTTP服务器根目录
	String root;
	// HTTP协议处理器
	HttpHandler handler;
	// 客户端的IP
	String clientInetAddress;
	// 客户端端口
	int clientPort;

	// 创建一条HTTP服务线程
	public HttpHandlerThread(String webRoot, StreamSocket client) {
		this.client = client;
		this.root = webRoot;
		clientInetAddress = client.getSocket().getInetAddress().toString();
		clientPort = client.getSocket().getPort();
		handler = new HttpHandler(new HttpProtocol(root));
	}

	// 处理HTTP请求
	public void run() {
		String temp = null;
		StringBuffer message = new StringBuffer();
		Response response = null;
		try {
			temp = client.receiveMessage();
			while (temp != null && !temp.isEmpty()) {
				message.append(temp);
				message.append(ConstantValues.CRLF);
				temp = client.receiveMessage();
			}
			message.append(ConstantValues.CRLF);
			response = handler.handle(message.toString());
			client.sendMessage(response.getHeader().toString());
			if (response.getInput() != null) { // 判断响应中是否有WEB对象需要传送给客户端
				InputStream inputStream = response.getInput();
				OutputStream outputStream = client.getSocket()
						.getOutputStream();
				byte[] bytes = new byte[1024];
				int length = 0;
				while ((length = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, length);
					outputStream.flush();
				}
				inputStream.close();
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out
				.println("客户端" + clientInetAddress + ":" + clientPort + "已断开");
	}
}
