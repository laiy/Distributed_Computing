// �ӿڵ�ʵ���� 
import java.rmi.server.UnicastRemoteObject;

public class SimpleRMIImpl extends UnicastRemoteObject implements SimpleRMIInterface {
    // �ӿ�ʵ�ֱ�����һ����ʽ�Ĺ��캯��������Ҫ�׳�һ��RemoteException�쳣
    public SimpleRMIImpl()throws java.rmi.RemoteException {
        super();
    }
    public String SimpleRMIHello(String name)throws java.rmi.RemoteException {
        return "Hello " + name;
    }
}
