// ¥¶¿Ìput√¸¡Ó

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

public class put extends ClientCmd {

	Socket connectToClient;
	String[] cmd;
	private OutputStream sendData;
	private DataInputStream receiveData;
	RandomAccessFile outFile;

	@Override
	public String func(Socket s, String[] cmd) {
		connectToClient = s;
		this.cmd = cmd;
		try {
			outFile = new RandomAccessFile("c:" + "\\" + cmd[1], "rw");
			sendData = connectToClient.getOutputStream();
			System.out.println(sendData);
			byte[] buf = new byte[1024];
			int num;
			while ((num = outFile.read(buf)) != -1) {
				sendData.write(buf, 0, num);
			}
			connectToClient.close();
			sendData.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "file download success!";
	}
}
