// 捕获与处理异常例程
class Account {
    private double balance = 1000;
    public void transfer(double amount)throws OutOfMoney {
        // 在方法中声明抛出异常
        if (balance < amount)
        // 直接抛出异常
            throw new OutOfMoney("[Balance:" + balance + " < Amount:" + amount + "]");
        balance = balance - amount;
    }
    public double getBalance() {
        return balance;
    }
}
class OutOfMoney extends Exception {
    public OutOfMoney() {
        // 给出该异常的一个缺省的字符串描述
        super("Your account have not enough money!");
    }
    public OutOfMoney(String msg) {
        // 允许使用者自行给出对异常的一个字符串描述
        super(msg);
    }
}
public class AccountException {
    public static void main(String[]args) {
        Account obj = new Account();
        double amount = 800;
        for (int count = 0; count < 3; count++) {
            try {
                obj.transfer(amount);
                System.out.println("Transfer amount: " + amount + ", and then balance: " + obj.getBalance());
            } catch (OutOfMoney exc) {
                exc.printStackTrace();
            } finally  {
                System.out.println("finally语句块中的语句总是会执行！");
            }
            amount = amount - 300;
        }
    }
}
