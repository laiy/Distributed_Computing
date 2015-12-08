// ���Զ�������л��ͷ����л�
import java.io.*;

public class AccountSerializable {
    public static void main(String[] args)throws Exception {
        // ���������ļ�������
        File f = new File("objectFile.obj");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        // ���л�����
        Account Account1 = new Account("Zhang3", 1000);
        Account Account2 = new Account("Li4", 2000);
        out.writeObject(Account1);
        out.writeObject(Account2);
        out.close();
        // �����л�����
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        Account obj1 = (Account)in.readObject();
        System.out.println("Account1=" + obj1);
        Account obj2 = (Account)in.readObject();
        System.out.println("Account2=" + obj2);
        in.close();
    }
}

// Account��ʵ��java.io.Serializable�ӿ�
class Account implements Serializable {
    private String name;
    private double balance;
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    public String toString() {
        return "name=" + name + ", balance=" + balance;
    }
}
