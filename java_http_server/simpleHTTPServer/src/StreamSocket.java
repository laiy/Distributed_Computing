// 自定义流连接套接字
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class StreamSocket {
	// 连接套接字
	private Socket socket;
	// 输入缓存
	private BufferedReader input;
	// 输出缓存
	private PrintWriter output;
	private OutputStream outStream;
	// 根据已有的套接字创建自定义流套接字
	public StreamSocket(Socket s) throws IOException {
		socket = s;
		setStreams();
	}
	// 根据主机名、监听端口建立流套接字
	public StreamSocket(String acceptorHost, int acceptorPort)
			throws UnknownHostException, IOException {
		socket = new Socket(acceptorHost, acceptorPort);
		setStreams();
	}
	// 向服务端 发送消息
	public void sendMessage(String message) {
		output.print(message);
		output.flush();
	}
	// 从服务端接收消息
	public String receiveMessage() throws IOException {
		String message = input.readLine();
		return message;
	}
	// 设置数据流
	private void setStreams() throws IOException {
		InputStream inStream = socket.getInputStream();
		input = new BufferedReader(new InputStreamReader(inStream));
		outStream = socket.getOutputStream();
		output = new PrintWriter(new OutputStreamWriter(outStream));
	}
	// 将文件输出到输出流中
	public void sendBytes(String filename) throws Exception {
		InputStream file = new FileInputStream(filename);
		byte[] data = new byte[file.available()];
		file.read(data);
		outStream.write(data);
		file.close();
	}
	// 关闭输入、输出缓存及套接字
	public void close() throws IOException {
		input.close();
		output.close();
		socket.close();
	}
	// 取套接字
	public Socket getSocket() {
		return socket;
	}
	// 设置套接字
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
