// ����RMI�ӿ�
import java.rmi.Remote;

public interface SimpleRMIInterface extends java.rmi.Remote {
    public String SimpleRMIHello(String name)throws java.rmi.RemoteException;
}
