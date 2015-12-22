import java.rmi.*;
import java.rmi.registry.*;
import java.io.*;

import agenda.service.*;

public class agendaRMIServer {
    
	private static final String DELEGATE_NAME = "REGISTRY_DELEGATE"; 
	private static String registryHost = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			//��ȡע�������������ip
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			do{
				System.out.print("������ע��������ڵ�����ip��");
				registryHost = br.readLine();
			}while((registryHost == null) || "".equals(registryHost.trim()));
			
			//�����ŷ�����
			AgendaServiceImplement asi = new AgendaServiceImplement();
			//ע���ŷ�����
			Registry remoteRegistry = LocateRegistry.getRegistry(registryHost);
			Registry delegate = (Registry) remoteRegistry.lookup(DELEGATE_NAME);
			delegate.bind("AgendaService", asi);
			System.out.println("Զ�̶���:AgendaService ������ע��ɹ���");
		}catch(RemoteException exc){
			System.out.println("����Զ�̶���ʱ�����쳣��");
			exc.printStackTrace();
		}catch(AlreadyBoundException exc){
			System.out.println("�����ظ����쳣��");
		}catch(NotBoundException exc){
			System.out.println("Զ��ע�����δ�󶨣�ע���ŷ�����ʧ�ܣ�");
		}catch(IOException exc){
			System.out.println("����ipʱ����IO�쳣��");
		}
		System.out.println("���������׼������ ...");
	}

}