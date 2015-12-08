// 通过继承Thread类定义新线程
public class SubclassThread extends Thread {
    public void run() {
        while (true) {
            // 执行线程自身的任务
            try {
                sleep(5 * 1000);
                break;
            } catch (InterruptedException exc) {
            // 睡眠被中断
            }
        }
    }
    public static void main(String[] args) {
        Thread thread = new SubclassThread();
        thread.start();
        System.out.println("主程序结束");
    }
}
