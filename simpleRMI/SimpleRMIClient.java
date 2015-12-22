// 客户端的主程序
import java.rmi.Naming;

public class SimpleRMIClient {
    public static void main(String[] args) {
        try {
            String hostName = "localhost";
            String portNum = "1099";
            String registryURL = "rmi://" + hostName + ":" + portNum + "/SimpleRMIService";
            SimpleRMIInterface simpleRMIObject = (SimpleRMIInterface)Naming .lookup(registryURL);
            System.out.println(simpleRMIObject.SimpleRMIHello("SYSU"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
