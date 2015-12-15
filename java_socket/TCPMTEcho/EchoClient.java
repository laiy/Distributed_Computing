// �ͻ��˳��� 
import java.net.*;
import java.io.*;

public class EchoClient {
    public static void main(String[] args)throws Exception {
        if (args.length != 2) {
            System.out.println("�÷���EchoClient <������> <�˿ں�>");
            return ;
        }

        // �������Ӳ�����������������������
        Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
        System.out.println("��ǰsocket��Ϣ��" + socket);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader
            (socket.getInputStream()));

        // ������̨������ַ������͸�����ˣ�����ʾ�ӷ���˻�ȡ�Ĵ�����
        BufferedReader stdIn = new BufferedReader(new InputStreamReader
            (System.in));
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("���أ�" + in.readLine());
        }
        stdIn.close();
        // �ر�����
        out.close();
        in.close();
        socket.close();
    }
}
