//服务端程序
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.reflect.*;

public class Server {
    // 存放远程对象的缓存
    private Map remoteObjects = new HashMap();
    // 把一个远程对象放到缓存中
    public void register(String className, Object remoteObject) {
        remoteObjects.put(className, remoteObject);
    }
    public void service()throws Exception {
        // 创建基于流的Socket,并在8000端口监听
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("服务器启动......");
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream in = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            RemoteCall remotecallobj = (RemoteCall)ois.readObject(); 
                //接收客户发送的Call对象
            System.out.println(remotecallobj);
            remotecallobj = invoke(remotecallobj); //调用相关对象的方法
            oos.writeObject(remotecallobj); 
                //向客户发送包含了执行结果的remotecallobj对象
            ois.close();
            oos.close();
            socket.close();
        }
    }

    public RemoteCall invoke(RemoteCall call) {
        Object result = null;
        try {
            String className = call.getClassName();
            String methodName = call.getMethodName();
            Object[] params = call.getParams();
            Class classType = Class.forName(className);
            Class[] paramTypes = call.getParamTypes();
            Method method = classType.getMethod(methodName, paramTypes);
            Object remoteObject = remoteObjects.get(className); 
                //从缓存中取出相关的远程对象
            if (remoteObject == null) {
                throw new Exception(className + "的远程对象不存在");
            } else {
                result = method.invoke(remoteObject, params);
            }
        } catch (Exception e) {
            result = e;
        }
        call.setResult(result); //设置方法执行结果
        return call;
    }

    public static void main(String args[])throws Exception {
        Server server = new Server();
        //把事先创建的RemoteServiceImpl 对象加入到服务器的缓存中
        server.register("AccountService", new AccountServiceImpl());
        server.service();
    }
}
