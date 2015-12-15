// HTTP�ͻ��˳��������
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SimpleHTTPClientHelper {
	private static final String endMessage = ".";
	private StreamSocket streamSocket;
	private String serverHost;
	private int serverPort;

	// ���캯��
	public SimpleHTTPClientHelper(String hostName, String portNum)
			throws UnknownHostException, IOException {
		this.serverHost = hostName;
		this.serverPort = Integer.parseInt(portNum);
		// �������û������������ʾ
		try {
			streamSocket = new StreamSocket(serverHost, serverPort);
			System.out.println("���ӳɹ���\n");
		} catch (UnknownHostException u) {
			System.out.println("�Ҳ���HTTP������,����ʧ�ܣ�");
			throw u;
		} catch (IOException i) {
			System.out.println("Socket����ʧ�ܣ�");
			throw i;
		}
	}

	// ���û��������������ȡ������
	public String getCommand(String message) {
		String cmd = "";
		// �ҵ���һ���ո���ֵ�λ��
		int index = message.indexOf(" ");
		// ����пո���index֮ǰ��������,������һ��û�в���������
		if (index >= 0)
			cmd = message.substring(0, index);
		else
			cmd = message;
		// ����ȫ��Ϊ��д��ĸ������
		return cmd.toUpperCase();
	}

	// ���û��������������ȡ��URI
	public String getUri(String message) {
		int index = message.indexOf(" ");
		// �õ�һ��ȥ������Ĳ�����
		if (index >= 0)
			message = message.substring(index + 1, message.length()).trim();
		else
			message = "";
		return message;
	}

	// �ͻ��˺ͷ���˽��н���
	public void process(String req, String uri) {
		String request = req + " " + uri + " HTTP/1.0\n\n";
		try {
			// ��������
			streamSocket.sendMessage(request);
			// ���շ������˵���Ӧ
			String response = streamSocket.receiveMessage();
			// ��ʾͷ����Ϣ
			while (response.length() != 0) {
				System.out.println(response);
				response = streamSocket.receiveMessage();
			}
			// ����Ϣ�屣�浽�ļ���ȥ
			FileOutputStream os = new FileOutputStream("MessageBody.txt");
			response = streamSocket.receiveMessage();
			while (response.length() != 0) {
				response += "\r\n";
				byte[] data = new byte[response.length()];
				data = response.getBytes();
				os.write(data, 0, data.length);
				response = streamSocket.receiveMessage();
			}
			os.close();
			System.out.println("��Ϣ�屣�浽MessageBody.txt!\n");
		} catch (Exception ex) {
			System.out.println("ERROR : " + ex);
			ex.printStackTrace(System.out);
		}
	}
	// �ر�Socket
	public void close() throws SocketException, IOException {
		streamSocket.sendMessage(endMessage);
		streamSocket.close();
	}
}
