// ͨ��ʵ��Runnable�ӿڶ������߳�
class Counter implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++)
            System.out.println("�������� " + i);
    }
}
public class RunnableThread {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread thread = new Thread(counter);
        thread.start();
        System.out.println("���������");
    }
}
