// 接口的实现类 
import java.rmi.server.UnicastRemoteObject;

public class SimpleRMIImpl extends UnicastRemoteObject implements SimpleRMIInterface {
    // 接口实现必须有一个显式的构造函数，并且要抛出一个RemoteException异常
    public SimpleRMIImpl()throws java.rmi.RemoteException {
        super();
    }
    public String SimpleRMIHello(String name)throws java.rmi.RemoteException {
        return "Hello " + name;
    }
}
