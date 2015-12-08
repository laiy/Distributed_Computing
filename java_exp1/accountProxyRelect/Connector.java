//负责建立与远程服务器的连接，以及接收和发送Socket对象
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
        //发送对象方法
        oos.writeObject(obj);
    }
    public Object receive()throws Exception {
        //接收对象方法
        return ois.readObject();
    }
    public void connect()throws Exception {
        //建立与远程服务器的连接
        connect(host, port);
    }
    public void connect(String host, int port)throws Exception {
        //建立与远程服务器的连接
        skt = new Socket(host, port);
        os = skt.getOutputStream();
        oos = new ObjectOutputStream(os);
        is = skt.getInputStream();
        ois = new ObjectInputStream(is);
    }
    public void close() {
        //关闭连接
        try {
            ois.close();
            oos.close();
            skt.close();
        } catch (Exception e) {
            System.out.println("Connector.close: " + e);
        }
    }
}
