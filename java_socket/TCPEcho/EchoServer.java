// 单线程服务端程序 
import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args)throws IOException {
        if (args.length != 1) {
            System.out.println("用法：EchoServer <端口号>");
            return ;
        }

        // 监听客户程序的连接请求
        ServerSocket listenSocket = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("服务程序正在监听端口" + args[0]);
        Socket socket = listenSocket.accept();
        // 从与客户程序的新建连接获取输入流和输出流
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader
            (socket.getInputStream()));
        // 从客户端读取数据，并写回数据的加工结果
        String message;
        while ((message = in.readLine()) != null) {
            System.out.println("收到请求：" + message);
            out.println(message.toUpperCase());
        }

        // 关闭连接
        out.close();
        in.close();
        socket.close();
        listenSocket.close();
    }
}
