// 基于Daytime服务器程序
import java.io.*;
import java.net.*;
import java.util.Date;

public class DaytimeServer2 {
    public static void main(String[] args) {
    		// 服务端缺省端口为13
        int serverPort = 13; 
        if (args.length == 1) serverPort = Integer.parseInt(args[0]);
        try {
            // 创建流Socket
            ServerSocket myConnectionSocket = new ServerSocket(serverPort);
            System.out.println("服务端准备就绪.");
            while (true) {
            		// 等待连接请求
                System.out.println("等待客户端请求连接");
                MyStreamSocket myDataSocket = new MyStreamSocket(myConnectionSocket.accept());
                System.out.println("客户端已经建立连接");
                Date timestamp = new Date();
                System.out.println("timestamp sent: " + timestamp.toString());
                // 响应客户端
                myDataSocket.sendMessage(timestamp.toString());
                myDataSocket.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
