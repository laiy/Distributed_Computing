// 接收返回类
import java.net.*;

public class UDPConnectionReceiverSender {
    public static void main(String[] args) {
        if (args.length != 4)
            System.out.println(
                "命令行参数：发送方IP地址，发送方端口，接收方端口，发送字符串");
        else {
            try {
                InetAddress receiverHost = InetAddress.getByName(args[0]);
                int receiverPort = Integer.parseInt(args[1]);
                int myPort = Integer.parseInt(args[2]);
                String message = args[3];
                // 产生数据报用于接收和返回数据
                DatagramSocketHelp mySocket = new DatagramSocketHelp(myPort);
                // 接收数据报
                System.out.println(mySocket.receiveMessage());
                // 返回数据报
                mySocket.sendMessage(receiverHost, receiverPort, message);
                mySocket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
