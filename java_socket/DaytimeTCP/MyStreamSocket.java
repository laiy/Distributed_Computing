// 服务端流式Socket类
import java.net.*;
import java.io.*;

public class MyStreamSocket extends Socket {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    MyStreamSocket(InetAddress acceptorHost, int acceptorPort)throws
        SocketException, IOException {
        socket = new Socket(acceptorHost, acceptorPort);
        setStreams();
    }
    MyStreamSocket(Socket socket)throws IOException {
        this.socket = socket;
        setStreams();
    }
    private void setStreams()throws IOException {
        // 创建数据输入流
        InputStream inStream = socket.getInputStream();
        input = new BufferedReader(new InputStreamReader(inStream));
        // 创建数据输出流
        OutputStream outStream = socket.getOutputStream();
        output = new PrintWriter(new OutputStreamWriter(outStream));
    }
    // 发送消息方法
    public void sendMessage(String message)throws IOException {
        output.println(message);
        output.flush();
    }
    // 接收消息方法
    public String receiveMessage()throws IOException {
        String message = input.readLine();
        return message;
    }
}
