// 客户端数据报类
import java.net.*;
import java.io.*;

public class MyDatagramSocket extends DatagramSocket {
    static final int MAX_LEN = 100;
    MyDatagramSocket()throws SocketException {
        super();
    }
    MyDatagramSocket(int portNo)throws SocketException {
        super(portNo);
    }
    // 发送消息方法
    public void sendMessage(InetAddress receiverHost, int receiverPort, String
        message)throws IOException {
        byte[] sendBuffer = message.getBytes();
        DatagramPacket datagram = new DatagramPacket(sendBuffer,
            sendBuffer.length, receiverHost, receiverPort);
        this.send(datagram);
    }
    // 接收消息方法
    public String receiveMessage()throws IOException {
        byte[] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        String message = new String(receiveBuffer);
        return message;
    }
}
