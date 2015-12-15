// øÕªß∂À∞Ô÷˙¿‡
import java.net.*;

public class DaytimeClientHelper2 {
    public static String getTimestamp(String hostName, String portNum)throws
        Exception {
        String timestamp = "";
        InetAddress serverHost = InetAddress.getByName(hostName);
        int serverPort = Integer.parseInt(portNum);
        MyStreamSocket mySocket = new MyStreamSocket(serverHost, serverPort);
        timestamp = mySocket.receiveMessage();
        mySocket.close();
        return timestamp;
    }
}
