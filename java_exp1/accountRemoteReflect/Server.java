//����˳���
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.reflect.*;

public class Server {
    // ���Զ�̶���Ļ���
    private Map remoteObjects = new HashMap();
    // ��һ��Զ�̶���ŵ�������
    public void register(String className, Object remoteObject) {
        remoteObjects.put(className, remoteObject);
    }
    public void service()throws Exception {
        // ������������Socket,����8000�˿ڼ���
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("����������......");
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream in = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            RemoteCall remotecallobj = (RemoteCall)ois.readObject(); 
                //���տͻ����͵�Call����
            System.out.println(remotecallobj);
            remotecallobj = invoke(remotecallobj); //������ض���ķ���
            oos.writeObject(remotecallobj); 
                //��ͻ����Ͱ�����ִ�н����remotecallobj����
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
                //�ӻ�����ȡ����ص�Զ�̶���
            if (remoteObject == null) {
                throw new Exception(className + "��Զ�̶��󲻴���");
            } else {
                result = method.invoke(remoteObject, params);
            }
        } catch (Exception e) {
            result = e;
        }
        call.setResult(result); //���÷���ִ�н��
        return call;
    }

    public static void main(String args[])throws Exception {
        Server server = new Server();
        //�����ȴ�����RemoteServiceImpl ������뵽�������Ļ�����
        server.register("AccountService", new AccountServiceImpl());
        server.service();
    }
}
