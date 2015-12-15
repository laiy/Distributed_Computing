// �����������ݰ�������
import java.net.*;

public class UDPConnectionSender {
    public static void main(String[] args) {
        if (args.length != 4)
            System.out.println(
                "�����в��������շ�IP��ַ�����շ��˿ڣ����ͷ��˿ڣ������ַ���");
        else {
            try {
                InetAddress receiverHost = InetAddress.getByName(args[0]);
                int receiverPort = Integer.parseInt(args[1]);
                int myPort = Integer.parseInt(args[2]);
                String message = args[3];
                // �����������ӵ����ݱ�
                DatagramSocketHelp mySocket = new DatagramSocketHelp(myPort);
                // ��������
                mySocket.connect(receiverHost, receiverPort);
                for (int i = 0; i < 10; i++)
                    mySocket.sendMessage(receiverHost, receiverPort, message);
                // ���մӽ��շ��ķ�������
                System.out.println(mySocket.receiveMessage());
                // �Ͽ����ӣ��ر�Socket
                mySocket.disconnect();
                mySocket.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
