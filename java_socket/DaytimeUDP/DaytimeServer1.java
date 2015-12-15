// ����UDP��Daytime����������
import java.io.*;
import java.util.Date;

public class DaytimeServer1 {
    public static void main(String[] args) {
    		// �����ȱʡ�˿�Ϊ13
        int serverPort = 13; 
        if (args.length == 1) serverPort = Integer.parseInt(args[0]);
        try {
            // �������ݱ����ڷ��������
            MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort);
            System.out.println("�����׼������.");
            while (true) {
                DatagramMessage request = mySocket.receiveMessageAndSender();
                System.out.println("�յ��ͻ�������");
                // ���յ���Ϣ��ȡ�÷�����ʱ��������
                Date timestamp = new Date();
                System.out.println("timestamp sent: " + timestamp.toString());
                // ��������ʱ�������ڷ��ظ�����ͻ���
                mySocket.sendMessage(request.getAddress(), request.getPort(), timestamp.toString());
            }
        } catch (Exception ex) {
            System.out.println("���⣺" + ex);
        }
    }
}
