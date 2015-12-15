// 基于UDP的Daytime服务器程序
import java.io.*;
import java.util.Date;

public class DaytimeServer1 {
    public static void main(String[] args) {
    		// 服务端缺省端口为13
        int serverPort = 13; 
        if (args.length == 1) serverPort = Integer.parseInt(args[0]);
        try {
            // 创建数据报用于发送与接收
            MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort);
            System.out.println("服务端准备就绪.");
            while (true) {
                DatagramMessage request = mySocket.receiveMessageAndSender();
                System.out.println("收到客户端请求");
                // 接收到消息后，取得服务器时间与日期
                Date timestamp = new Date();
                System.out.println("timestamp sent: " + timestamp.toString());
                // 将服务器时间与日期返回给请求客户端
                mySocket.sendMessage(request.getAddress(), request.getPort(), timestamp.toString());
            }
        } catch (Exception ex) {
            System.out.println("问题：" + ex);
        }
    }
}
