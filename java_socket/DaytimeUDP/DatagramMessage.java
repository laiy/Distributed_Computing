// 数据报消息类
import java.net.*;

public class DatagramMessage {
    private String message;
    private InetAddress senderAddress;
    private int senderPort;
    public void putVal(String message, InetAddress addr, int port) {
        this.message = message;
        this.senderAddress = addr;
        this.senderPort = port;
    }
    public String getMessage() {
        return this.message;
    }
    public InetAddress getAddress() {
        return this.senderAddress;
    }
    public int getPort() {
        return this.senderPort;
    }
}
