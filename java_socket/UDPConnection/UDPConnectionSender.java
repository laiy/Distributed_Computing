// 面向连接数据包发送类
import java.net.*;

public class UDPConnectionSender {
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
                // 创建面向连接的数据报
                DatagramSocketHelp mySocket = new DatagramSocketHelp(myPort);
                // 建立连接
                mySocket.connect(receiverHost, receiverPort);
                for (int i = 0; i < 10; i++)
                    mySocket.sendMessage(receiverHost, receiverPort, message);
                // 接收从接收方的返回数据
                System.out.println(mySocket.receiveMessage());
                // 断开连接，关闭Socket
                mySocket.disconnect();
                mySocket.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
