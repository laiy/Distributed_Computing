import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleFTPClient {
	public static void main(String[] args) {
		System.out.println("welcome to use FTP client");
		while (true) {
			System.out.print("ftp>");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();
			String[] cmd = input.split(" ");
			if (cmd[0].equalsIgnoreCase("open")) {
				try {
					InetAddress addr = InetAddress
							.getByName(new String(cmd[1]));
					int num = Integer.parseInt(cmd[2]);
					ClientThread ct = new ClientThread();
					ct.testConnection(addr, num);
					break;
				} catch (UnknownHostException e) {
					System.out.println("500 can not found the host!");
				} catch (Exception e) {
					System.out.println("500 incorrect command!");
					continue;
				}

			} else {
				System.out.println("you should open the host first!");
				System.out.println("open [host address] [port]");
			}
		}
	}
}
