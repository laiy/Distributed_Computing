// ¥¶¿Ì‰Ø¿¿ƒø¬º√¸¡Ó
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ls extends ClientCmd {
    private static Socket connectToServer;
    private static DataOutputStream sendData;
    private static DataInputStream receiveData;

    public String func(Socket s, String[] cmd) {
        connectToServer = s;
        String str = null;
        try {
            receiveData = new DataInputStream(connectToServer.getInputStream());
            sendData = new DataOutputStream(connectToServer.getOutputStream());
            sendData.writeUTF(cmd[0]);
            str = receiveData.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
