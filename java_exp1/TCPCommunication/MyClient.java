// �򵥵��������׽���ʵ��ͨ�ŵĿͻ��˳���
import java.net.*;
import java.io.*;

public class MyClient {
    static Socket server;

    public static void main(String[] args)throws Exception {
        if (args.length != 2) {
            System.out.println("�÷���MyClient <������> <�˿ں�>");
            return ;
        }
        // ��ȡ����ip��ַ�������ڱ��صķ������ȱʡ�˿��ǣ�1234
        Socket server = new Socket(args[0], Integer.parseInt(args[1]));
        // �������Ӳ�����������������������
        BufferedReader in = new BufferedReader(new InputStreamReader (server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
        // ������̨������ַ������͸�����ˣ�������յ�"end"�����˳�����
        while (true) {
            String str = wt.readLine();
            out.println(str);
            out.flush();
            if (str.equals("end")) {
                System.out.println("ͨ���Ѿ���ֹ");
                break;
            }
            System.out.println(in.readLine());
        }
        // �ر�����
        wt.close();
        out.close();
        in.close();
        server.close();
    }
}
