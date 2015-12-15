// �����������ݰ�������
import java.net.*;

public class UDPConnectionReceiver {
    public static void main(String[] args) {
        if (args.length != 4)
            System.out.println(
                "�����в��������ͷ�IP��ַ�����ͷ��˿ڣ����շ��˿ڣ������ַ���");
        else {
            try {
                InetAddress senderHost = InetAddress.getByName(args[0]);
                int senderPort = Integer.parseInt(args[1]);
                int myPort = Integer.parseInt(args[2]);
                String message = args[3];
                // �����������ӵ����ݱ�
                DatagramSocketHelp mySocket = new DatagramSocketHelp(myPort);
                // �������ӣ���������
                mySocket.connect(senderHost, senderPort);
                for (int i = 0; i < 100; i++)
                    System.out.println(mySocket.receiveMessage());
                // ��Է���������
                mySocket.sendMessage(senderHost, senderPort, message);
                mySocket.close();
            } catch (Exception ex) {
                System.out.println("An exception has occured: " + ex);
            }
        }
    }
}
