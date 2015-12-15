// ����Daytime����������
import java.io.*;
import java.net.*;
import java.util.Date;

public class DaytimeServer2 {
    public static void main(String[] args) {
    		// �����ȱʡ�˿�Ϊ13
        int serverPort = 13; 
        if (args.length == 1) serverPort = Integer.parseInt(args[0]);
        try {
            // ������Socket
            ServerSocket myConnectionSocket = new ServerSocket(serverPort);
            System.out.println("�����׼������.");
            while (true) {
            		// �ȴ���������
                System.out.println("�ȴ��ͻ�����������");
                MyStreamSocket myDataSocket = new MyStreamSocket(myConnectionSocket.accept());
                System.out.println("�ͻ����Ѿ���������");
                Date timestamp = new Date();
                System.out.println("timestamp sent: " + timestamp.toString());
                // ��Ӧ�ͻ���
                myDataSocket.sendMessage(timestamp.toString());
                myDataSocket.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
