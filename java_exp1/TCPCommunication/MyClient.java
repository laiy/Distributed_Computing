// 简单的利用流套接字实现通信的客户端程序
import java.net.*;
import java.io.*;

public class MyClient {
    static Socket server;

    public static void main(String[] args)throws Exception {
        if (args.length != 2) {
            System.out.println("用法：MyClient <主机名> <端口号>");
            return ;
        }
        // 获取本地ip地址，访问在本地的服务程序，缺省端口是：1234
        Socket server = new Socket(args[0], Integer.parseInt(args[1]));
        // 建立连接并打开相关联的输入流和输出流
        BufferedReader in = new BufferedReader(new InputStreamReader (server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
        // 将控制台输入的字符串发送给服务端，如果接收到"end"，则退出程序。
        while (true) {
            String str = wt.readLine();
            out.println(str);
            out.flush();
            if (str.equals("end")) {
                System.out.println("通信已经终止");
                break;
            }
            System.out.println(in.readLine());
        }
        // 关闭连接
        wt.close();
        out.close();
        in.close();
        server.close();
    }
}
