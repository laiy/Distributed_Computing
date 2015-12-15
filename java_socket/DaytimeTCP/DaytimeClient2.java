// 基于TCP的Daytime客户端程序
import java.io.*;

public class DaytimeClient2 {
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            System.out.println("请输入服务器主机名：");
            String hostName = br.readLine();
            if (hostName.length() == 0) hostName = "localhost";
            System.out.println("请输入服务器端口：");
            String portNum = br.readLine();
            if (portNum.length() == 0) portNum = "13";
            System.out.println(
                // 获取服务端时间
                DaytimeClientHelper2.getTimestamp(hostName, portNum));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
