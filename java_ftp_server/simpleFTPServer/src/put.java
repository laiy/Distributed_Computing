// ¥¶¿Ìput√¸¡Ó
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

public class put extends ServerCmd {

	Socket connectToServer;
	String[] cmd;
	private DataOutputStream sendData;
	private InputStream receiveData;
	RandomAccessFile inFile;

	@Override
	public String func(Socket s, String[] cmd) {
		connectToServer = s;
		this.cmd = cmd;

		try {
			inFile = new RandomAccessFile("c:" + "\\" + cmd[1], "rw");
			sendData = new DataOutputStream(connectToServer.getOutputStream());
			String command = cmd[0] + " " + cmd[1];
			System.out.println(command);
			sendData.writeUTF(command);
			// str=receiveData.readUTF();
			receiveData = connectToServer.getInputStream();
			byte buf[] = new byte[1024];
			int num;
			while ((num = receiveData.read(buf)) != 1) {
				inFile.write(buf, 0, num);
			}
			connectToServer.close();
			inFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "file down success!";
	}
}
