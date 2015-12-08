// �򵥵��������׽���ʵ��ͨ�ŵķ���˳���
import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args)throws IOException {
        if (args.length != 1) {
            System.out.println("�÷���EchoServer <�˿ں�>");
            return ;
        }
        // ���� ServerSocket ʵ������������
        ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("����������ڼ����˿�" + args[0]);
        // �����ͻ��������������
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream());
        // �ӿͻ��˶�ȡ���ݣ�����ӡ����Ļ�ϣ�������յ���End�������˳�����
        String str;
        System.out.println("�ͻ����Ѿ���������");
        while ((str = in.readLine()) != null) {
            System.out.println(str);
            System.out.println("�յ�����" + str);
            out.println("������Ѿ��յ�����" + str);
            out.flush();
            if (str.equals("end")) {
                System.out.println("ͨ���Ѿ���ֹ");
                break;
            }
        }
        // �ر�����
        out.close();
        in.close();
        client.close();
    }
}
