// �����ڴ� ������ ����
import java.io.*;
import java.nio.*;

public class Producer extends Thread {
    private String mFileName;
    private FileChannel mFileChannel;
    private MappedByteBuffer mMappedByteBuffer;

    public Producer(String fn) {
        try {
            mFileName = fn;
            // ���һ���ɶ�д�������ȡ�ļ�����
            RandomAccessFile RAFile = new RandomAccessFile(mFileName, "rw");
            // �����Ӧ���ļ�ͨ��
            mFileChannel = RAFile.getChannel();
            // ȡ���ļ���ʵ�ʴ�С���Ա�ӳ�񵽹����ڴ�
            int size = (int)mFileChannel.size();
            // ��ù����ڴ滺�������ù����ڴ�ɶ�
            mMappedByteBuffer = mFileChannel.map(FileChannel.MapMode.READ_WRITE,
                0, size).load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void run() {
        int i = 0;
        while (true) {
            try {
                FileLock lock = null;
                lock = mFileChannel.tryLock();
                if (lock == null) {
                    System.err.println("Producer: lock failed");
                    continue;
                }
                mMappedByteBuffer.putInt(0, ++i);
                mMappedByteBuffer.putInt(4, ++i);
                mMappedByteBuffer.putInt(8, ++i);
                System.out.println("Producer: " + (i - 3) + ":" + (i - 2) + ":"
                    + (i - 1));
                Thread.sleep(200);
                lock.release();
                Thread.sleep(500);
            } catch (IOException ex) {
                System.out.print(ex);
            } catch (InterruptedException ex) {
                System.out.print(ex);
            }
        }
    }

    public static void main(String args[]) {
        Producer producer = new Producer("sharedMemory.bin");
        producer.run();
    }
}
