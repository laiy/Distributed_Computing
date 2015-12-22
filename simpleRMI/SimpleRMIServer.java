// ·þÎñ¶Ë³ÌÐò 
import java.rmi.Naming;

public class SimpleRMIServer {
    public SimpleRMIServer() {
        try {
            SimpleRMIInterface SimpleRMIobj = new SimpleRMIImpl();
            Naming.rebind("rmi://localhost:1099/SimpleRMIService", SimpleRMIobj);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        new SimpleRMIServer();
    }
}
