// ¥¶¿Ìπÿ±’√¸¡Ó

import java.io.IOException;
import java.net.Socket;

public class close extends ClientCmd {
	Socket s;
	@Override
	public String func(Socket s, String[] cmd) {
		this.s = s;
		ClientThread.socketClosing();
		System.out.println("socket closed!goodbye!");
		try {
			s.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
