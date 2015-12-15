// ��������ݱ���
import java.net.*;
import java.io.*;

public class MyServerDatagramSocket extends DatagramSocket {
    static final int MAX_LEN = 100;
    MyServerDatagramSocket(int portNo)throws SocketException {
        super(portNo);
    }
    // ������Ϣ����
    public void sendMessage(InetAddress receiverHost, int receiverPort, String message)throws IOException {
        byte[] sendBuffer = message.getBytes();
        DatagramPacket datagram = new DatagramPacket(sendBuffer, sendBuffer.length, receiverHost, receiverPort);
        this.send(datagram);
    }
    // ������Ϣ����
    public String receiveMessage()throws IOException {
        byte[] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        String message = new String(receiveBuffer);
        return message;
    }
    // ������Ϣ���ٷ�����Ϣ����
    public DatagramMessage receiveMessageAndSender()throws IOException {
        byte[] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        DatagramMessage returnVal = new DatagramMessage();
        returnVal.putVal(new String(receiveBuffer), datagram.getAddress(), datagram.getPort());
        return returnVal;
    }
}
