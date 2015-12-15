// 面向连接数据包接收类
import java.net.*;

public class UDPConnectionReceiver {
    public static void main(String[] args) {
        if (args.length != 4)
            System.out.println(
                "命令行参数：发送方IP地址，发送方端口，接收方端口，发送字符串");
        else {
            try {
                InetAddress senderHost = InetAddress.getByName(args[0]);
                int senderPort = Integer.parseInt(args[1]);
                int myPort = Integer.parseInt(args[2]);
                String message = args[3];
                // 创建面向连接的数据报
                DatagramSocketHelp mySocket = new DatagramSocketHelp(myPort);
                // 建立连接，接收数据
                mySocket.connect(senderHost, senderPort);
                for (int i = 0; i < 100; i++)
                    System.out.println(mySocket.receiveMessage());
                // 向对方发送数据
                mySocket.sendMessage(senderHost, senderPort, message);
                mySocket.close();
            } catch (Exception ex) {
                System.out.println("An exception has occured: " + ex);
            }
        }
    }
}
