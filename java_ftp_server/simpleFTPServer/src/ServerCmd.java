// 服务端命令接口
import java.net.Socket;

public abstract class ServerCmd {

	public abstract String func(Socket s, String[] cmd);
}
