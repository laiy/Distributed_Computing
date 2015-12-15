// ���������н����HTTP�ͻ�����
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleHTTPClient {
	static final String endMessage = ".";
	public static void main(String[] args) {
		// ����̨��������
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("\n�����������������(Ĭ��������Ϊlocalhost)��");
			String hostName = br.readLine();
			if (hostName.length() == 0)
				hostName = "localhost";
			System.out.println("\n����������������˿ں�(Ĭ�϶˿ں�Ϊ8000)��");
			String portNum = br.readLine();
			System.out.println(portNum);
			if (portNum.length() == 0)
				portNum = "8000";
			SimpleHTTPClientHelper helper = new SimpleHTTPClientHelper(
					hostName, portNum);
			boolean done = false;
			String message;
			System.out.println("\n�����ʽ��<HTTP method><space><Request-URI>\n"
					+ "ʾ����GET /index.html\n" + "��Ҫ�˳������� . \n");
			while (!done) {
				System.out.println("\n��������������>");
				message = br.readLine();
				if ((message.trim()).equals(endMessage)) {
					done = true;
					helper.close();
				} else if ((helper.getCommand(message)).equals("GET")) {
					String req = helper.getCommand(message);
					String uri = helper.getUri(message);
					helper.process(req, uri);
				} else {
					System.out.println("������������������������룡");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}