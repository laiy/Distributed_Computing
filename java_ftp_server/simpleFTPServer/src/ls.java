// ¥¶¿Ìls√¸¡Ó
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ls extends ServerCmd {
	String str = "";
	Socket s;
	private DataOutputStream sendData;
	private DataInputStream receiveData;

	public String func(Socket s, String[] cmd) {
		String back = null;
		this.s = s;
		back = out("C:\\root\\");
		return back;
	}

	public String out(String path) {
		try {
			sendData = new DataOutputStream(s.getOutputStream());
			File file = new File(path);
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					str += files[i].getName() + "  \n";
				}
				System.out.println(str);
			}
			sendData.writeUTF(str + "200 command complete!");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}