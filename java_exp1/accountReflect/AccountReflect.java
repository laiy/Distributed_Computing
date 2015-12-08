// ��ʾReflection�Ļ���ʹ�÷���
import java.lang.reflect.*;
public class AccountReflect {
    public Object copy(Object object)throws Exception {
        // ��ö��������
        Class classType = object.getClass();
        System.out.println("Class:" + classType.getName());
        // ͨ��Ĭ�Ϲ��췽������һ���µĶ���
        Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
        // ��ö������������
        Field fields[] = classType.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            String firstLetter = fieldName.substring(0,1).toUpperCase();
            // ��ú����Զ�Ӧ��getXXX()����������
            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            // ��ú����Զ�Ӧ��setXXX()����������
            String setMethodName = "set" + firstLetter + fieldName.substring(1);
            // ��ú����Զ�Ӧ��getXXX()����
            Method getMethod = classType.getMethod(getMethodName, new Class[]{});
            // ��ú����Զ�Ӧ��setXXX()����
            Method setMethod = classType.getMethod(setMethodName, new Class[] {field.getType()});
            // ����ԭ�����getXXX()����
            Object value = getMethod.invoke(object, new Object[]{});
            System.out.println(fieldName + ":" + value);
            // ���ø��ƶ����setXXX()����
            setMethod.invoke(objectCopy, new Object[]{value});
        }
        return objectCopy;
    }
    public static void main(String[] args)throws Exception {
        Account Account = new Account("Zhang3", 1000);
        Account AccountCopy = (Account)new AccountReflect().copy(Account);
        System.out.println("Copy information:" + AccountCopy.getName() + " " + AccountCopy.getBalance());
    }
}

// Account��
class Account {
    private String name;
    private int balance;
    public Account(){}
    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
