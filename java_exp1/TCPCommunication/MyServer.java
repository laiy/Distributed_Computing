// 简单的利用流套接字实现通信的服务端程序
import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args)throws IOException {
        if (args.length != 1) {
            System.out.println("用法：EchoServer <端口号>");
            return ;
        }
        // 创建 ServerSocket 实例，建立连接
        ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("服务程序正在监听端口" + args[0]);
        // 监听客户程序的连接请求
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream());
        // 从客户端读取数据，并打印在屏幕上，如果接收到”End”，则退出程序。
        String str;
        System.out.println("客户端已经建立连接");
        while ((str = in.readLine()) != null) {
            System.out.println(str);
            System.out.println("收到请求：" + str);
            out.println("服务端已经收到请求：" + str);
            out.flush();
            if (str.equals("end")) {
                System.out.println("通信已经终止");
                break;
            }
        }
        // 关闭连接
        out.close();
        in.close();
        client.close();
    }
}
