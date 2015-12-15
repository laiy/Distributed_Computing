// 发送接收类
import java.net.*;

public class UDPConnectionSenderReceiver {
    public static void main(String[] args) {
        if (args.length != 4)
            System.out.println(
                "命令行参数：接收方IP地址，接收方端口，发送方端口，发送字符串");
        else {
            try {
                InetAddress receiverHost = InetAddress.getByName(args[0]);
                int receiverPort = Integer.parseInt(args[1]);
                int myPort = Integer.parseInt(args[2]);
                String message = args[3];
                // 产生数据报用于发送和接收数据
                DatagramSocketHelp mySocket = new DatagramSocketHelp(myPort);
                // 发送数据报
                mySocket.sendMessage(receiverHost, receiverPort, message);
                // 等待返回数据
                System.out.println(mySocket.receiveMessage());
                mySocket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
