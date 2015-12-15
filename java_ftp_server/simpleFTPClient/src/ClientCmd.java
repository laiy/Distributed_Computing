// 客户端命令接口
import java.net.Socket;

public abstract class ClientCmd {

	public abstract String func(Socket s, String[] cmd);
}
