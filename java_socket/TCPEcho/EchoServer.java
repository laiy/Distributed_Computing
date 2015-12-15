// ���̷߳���˳��� 
import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args)throws IOException {
        if (args.length != 1) {
            System.out.println("�÷���EchoServer <�˿ں�>");
            return ;
        }

        // �����ͻ��������������
        ServerSocket listenSocket = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("����������ڼ����˿�" + args[0]);
        Socket socket = listenSocket.accept();
        // ����ͻ�������½����ӻ�ȡ�������������
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader
            (socket.getInputStream()));
        // �ӿͻ��˶�ȡ���ݣ���д�����ݵļӹ����
        String message;
        while ((message = in.readLine()) != null) {
            System.out.println("�յ�����" + message);
            out.println(message.toUpperCase());
        }

        // �ر�����
        out.close();
        in.close();
        socket.close();
        listenSocket.close();
    }
}
