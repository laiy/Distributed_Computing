//��������Զ�̷����������ӣ��Լ����պͷ���Socket����
import java.io.*;
import java.net.*;
public class Connector {
    private String host;
    private int port;
    private Socket skt;
    private InputStream is;
    private ObjectInputStream ois;
    private OutputStream os;
    private ObjectOutputStream oos;

    public Connector(String host, int port)throws Exception {
        this.host = host;
        this.port = port;
        connect(host, port);
    }
    public void send(Object obj)throws Exception {
        //���Ͷ��󷽷�
        oos.writeObject(obj);
    }
    public Object receive()throws Exception {
        //���ն��󷽷�
        return ois.readObject();
    }
    public void connect()throws Exception {
        //������Զ�̷�����������
        connect(host, port);
    }
    public void connect(String host, int port)throws Exception {
        //������Զ�̷�����������
        skt = new Socket(host, port);
        os = skt.getOutputStream();
        oos = new ObjectOutputStream(os);
        is = skt.getInputStream();
        ois = new ObjectInputStream(is);
    }
    public void close() {
        //�ر�����
        try {
            ois.close();
            oos.close();
            skt.close();
        } catch (Exception e) {
            System.out.println("Connector.close: " + e);
        }
    }
}
