// �������ʽSocket��
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
        // ��������������
        InputStream inStream = socket.getInputStream();
        input = new BufferedReader(new InputStreamReader(inStream));
        // �������������
        OutputStream outStream = socket.getOutputStream();
        output = new PrintWriter(new OutputStreamWriter(outStream));
    }
    // ������Ϣ����
    public void sendMessage(String message)throws IOException {
        output.println(message);
        output.flush();
    }
    // ������Ϣ����
    public String receiveMessage()throws IOException {
        String message = input.readLine();
        return message;
    }
}
