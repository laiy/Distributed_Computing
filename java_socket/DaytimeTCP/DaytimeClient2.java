// ����TCP��Daytime�ͻ��˳���
import java.io.*;

public class DaytimeClient2 {
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            System.out.println("�������������������");
            String hostName = br.readLine();
            if (hostName.length() == 0) hostName = "localhost";
            System.out.println("������������˿ڣ�");
            String portNum = br.readLine();
            if (portNum.length() == 0) portNum = "13";
            System.out.println(
                // ��ȡ�����ʱ��
                DaytimeClientHelper2.getTimestamp(hostName, portNum));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
