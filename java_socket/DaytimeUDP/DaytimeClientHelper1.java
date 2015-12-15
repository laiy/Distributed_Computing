// 客户端帮助类
import java.net.*;

public class DaytimeClientHelper1 {
    public static String getTimestamp(String hostName, String portNum) {
        String timestamp = "";
        try {
            InetAddress serverHost = InetAddress.getByName(hostName);
            int serverPort = Integer.parseInt(portNum);
            MyDatagramSocket mySocket = new MyDatagramSocket();
            mySocket.sendMessage(serverHost, serverPort, "");
            timestamp = mySocket.receiveMessage();
            mySocket.close();
        } catch (Exception ex) {
            System.out.println("问题: " + ex);
        }
        return timestamp;
    }
}
