// 演示Reflection的基本使用方法
import java.lang.reflect.*;
public class AccountReflect {
    public Object copy(Object object)throws Exception {
        // 获得对象的类型
        Class classType = object.getClass();
        System.out.println("Class:" + classType.getName());
        // 通过默认构造方法创建一个新的对象
        Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
        // 获得对象的所有属性
        Field fields[] = classType.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            String firstLetter = fieldName.substring(0,1).toUpperCase();
            // 获得和属性对应的getXXX()方法的名字
            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            // 获得和属性对应的setXXX()方法的名字
            String setMethodName = "set" + firstLetter + fieldName.substring(1);
            // 获得和属性对应的getXXX()方法
            Method getMethod = classType.getMethod(getMethodName, new Class[]{});
            // 获得和属性对应的setXXX()方法
            Method setMethod = classType.getMethod(setMethodName, new Class[] {field.getType()});
            // 调用原对象的getXXX()方法
            Object value = getMethod.invoke(object, new Object[]{});
            System.out.println(fieldName + ":" + value);
            // 调用复制对象的setXXX()方法
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

// Account类
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
