// �����봦���쳣����
class Account {
    private double balance = 1000;
    public void transfer(double amount)throws OutOfMoney {
        // �ڷ����������׳��쳣
        if (balance < amount)
        // ֱ���׳��쳣
            throw new OutOfMoney("[Balance:" + balance + " < Amount:" + amount + "]");
        balance = balance - amount;
    }
    public double getBalance() {
        return balance;
    }
}
class OutOfMoney extends Exception {
    public OutOfMoney() {
        // �������쳣��һ��ȱʡ���ַ�������
        super("Your account have not enough money!");
    }
    public OutOfMoney(String msg) {
        // ����ʹ�������и������쳣��һ���ַ�������
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
                System.out.println("finally�����е�������ǻ�ִ�У�");
            }
            amount = amount - 300;
        }
    }
}
