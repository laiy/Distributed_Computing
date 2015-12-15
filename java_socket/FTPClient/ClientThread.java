// 客户端线程类
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientThread extends Thread {
    private static Socket connectToServer;
    private Thread thread;
    public void testConnection(InetAddress add, int port) {
        try {
            connectToServer = new Socket(add, port);
            System.out.println("200 connect to " + connectToServer.getInetAddress() + " server success!");
        } catch (IOException e) {
            System.out.println("connection incorrect!");
        }
        thread = new Thread(this);
        thread.start();
    }
    public void run() {
        while (true) {
            try {
                System.out.print("ftp>");
                Scanner i = new Scanner(System.in);
                String temp = i.nextLine();
                String[] cmd = temp.split(" ");
                try {
                		// 采用Java反射机制获得处理客户请求的对象
                    ClientCmd cm = (ClientCmd)Class.forName("client." + cmd[0]).newInstance();
                    String str = cm.func(connectToServer, cmd);
                    System.out.println(str);
                } catch (InstantiationException e) {
                    System.out.println("Instantiation！");
                } catch (IllegalAccessException e) {
                    System.out.println("Illegal access！");
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found！");
                }
            } catch (Exception e) {
                socketClosing();
                break;
            }
        }
    }
    public static void socketClosing() {
        try {
            connectToServer.close();
        } catch (Exception e) {
            System.out.println("close socket fail!");
        }
    }
}
