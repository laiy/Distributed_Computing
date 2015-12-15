// ���ö��̷߳�ʽʵ�ֵķ���˳��� 
import java.io.*;
import java.net.*;
public class MTEchoServer {
    public static void main(String[] args)throws IOException {
        if (args.length != 1) {
            System.out.println("�÷���MTServer <�˿ں�>");
            return ;
        }
        ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("����������ڼ����˿ڣ�" + args[0]);
        for (;;)
            new EchoThread(ss.accept()).start();
    }
}

class EchoThread extends Thread {
    Socket socket;
    EchoThread(Socket s) {
        socket = s;
    }
    public void run() {
        System.out.println("����Ϊ�ͻ������ṩ����" + socket);
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(socket + "����" + message);
                out.println(message.toUpperCase());
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
