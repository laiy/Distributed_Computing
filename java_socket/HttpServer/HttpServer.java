// �򵥵�HTTP������
import java.net.*;
import java.io.*;
import java.util.*;

public class HttpServer {
    public static void main(String[] args)throws IOException {
        ServerSocket ss = new ServerSocket((args.length == 0) ? 80 : Integer.parseInt(args[0]));
        System.out.println("HTTP��������Ѿ��� ...");
        while (true) new HttpdThread(ss.accept()).start();
    }
}

class HttpdThread extends Thread {
    Socket socket;
    HttpdThread(Socket s) {
        socket = s;
    }
    public void run() {
        try {
            // �������Ӱ󶨵��������������
            BufferedReader in = new BufferedReader(new InputStreamReader (socket.getInputStream(), "GBK"));
            OutputStream out = socket.getOutputStream();
            // ��ȡ�ͻ�����
            String request = in.readLine();
            System.out.println("�յ�����" + request);
            // ����HTTPЭ������ͻ��������ݣ�ֻ����GET���
            StringTokenizer st = new StringTokenizer(request);
            if ((st.countTokens() >= 2) && st.nextToken().equals("GET")) {
                // ��������ȡ���ĵ����ļ�����֧��Ĭ�������ļ�
                String filename = st.nextToken();
                if (filename.startsWith("/")) filename = filename.substring(1);
                if (filename.endsWith("/")) filename += "index.html";
                if (filename.equals("")) filename += "index.html";
                try {
                    // ��ȡ�ļ��е����ݲ�д��socket�������
                    InputStream file = new FileInputStream(filename);
                    byte[] data = new byte[file.available()];
                    file.read(data);
                    out.write(data);
                    out.flush();
                } catch (FileNotFoundException exc) {
                    PrintWriter pout = new PrintWriter(new OutputStreamWriter (out, "GBK"), true);
                    pout.println("�������404��δ����Ŀ�꣡");
                }
            } else {
                PrintWriter pout = new PrintWriter(new OutputStreamWriter(out, "GBK"), true);
                pout.println("�������400�����������");
            }
            // �ر�����
            socket.close();
        } catch (IOException exc) {
            System.out.println("I/O����" + exc);
        }
    }
}
