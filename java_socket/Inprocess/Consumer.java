// �����ڴ� ������ ����
import java.io.*;
import java.nio.*;

public class Consumer extends Thread {
    private String mFileName;
    private FileChannel mFileChannel;
    private MappedByteBuffer mMappedByteBuffer;

    public Consumer(String fn) {
        try {
            mFileName = fn;
            //���һ���ɶ�д�������ȡ�ļ�����
            RandomAccessFile RAFile = new RandomAccessFile(mFileName, "r");
            //�����Ӧ���ļ�ͨ��
            mFileChannel = RAFile.getChannel();
            //ȡ���ļ���ʵ�ʴ�С���Ա�ӳ�񵽹����ڴ�
            int size = (int)mFileChannel.size();
            //��ù����ڴ滺�������ù����ڴ�ɶ�
            mMappedByteBuffer = mFileChannel.map(FileChannel.MapMode.READ_ONLY,
                0, size).load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(300);
                FileLock lock = null;
                lock = mFileChannel.tryLock(0, 10, true);
                if (lock == null) {
                    System.err.println("Consumer:   lock   failed");
                    continue;
                }
                Thread.sleep(200);
                System.out.println("Consumer:   " + mMappedByteBuffer.getInt(0)
                    + ":" + mMappedByteBuffer.getInt(4) + ":" +
                    mMappedByteBuffer.getInt(8));
                lock.release();
            } catch (IOException ex) {
                System.out.print(ex);
            } catch (InterruptedException ex) {
                System.out.print(ex);
            }
        }
    }

    public static void main(String args[]) {
        Consumer consumer = new Consumer("sharedMemory.bin");
        consumer.start();
    }
}
