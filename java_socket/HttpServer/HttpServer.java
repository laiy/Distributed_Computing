// 简单的HTTP服务器
import java.net.*;
import java.io.*;
import java.util.*;

public class HttpServer {
    public static void main(String[] args)throws IOException {
        ServerSocket ss = new ServerSocket((args.length == 0) ? 80 : Integer.parseInt(args[0]));
        System.out.println("HTTP服务程序已就绪 ...");
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
            // 打开与连接绑定的输入流和输出流
            BufferedReader in = new BufferedReader(new InputStreamReader (socket.getInputStream(), "GBK"));
            OutputStream out = socket.getOutputStream();
            // 读取客户请求
            String request = in.readLine();
            System.out.println("收到请求：" + request);
            // 根据HTTP协议分析客户请求内容（只处理GET命令）
            StringTokenizer st = new StringTokenizer(request);
            if ((st.countTokens() >= 2) && st.nextToken().equals("GET")) {
                // 从请求中取出文档的文件名，支持默认索引文件
                String filename = st.nextToken();
                if (filename.startsWith("/")) filename = filename.substring(1);
                if (filename.endsWith("/")) filename += "index.html";
                if (filename.equals("")) filename += "index.html";
                try {
                    // 读取文件中的内容并写到socket的输出流
                    InputStream file = new FileInputStream(filename);
                    byte[] data = new byte[file.available()];
                    file.read(data);
                    out.write(data);
                    out.flush();
                } catch (FileNotFoundException exc) {
                    PrintWriter pout = new PrintWriter(new OutputStreamWriter (out, "GBK"), true);
                    pout.println("错误代码404：未发现目标！");
                }
            } else {
                PrintWriter pout = new PrintWriter(new OutputStreamWriter(out, "GBK"), true);
                pout.println("错误代码400：错误的请求！");
            }
            // 关闭连接
            socket.close();
        } catch (IOException exc) {
            System.out.println("I/O错误：" + exc);
        }
    }
}
