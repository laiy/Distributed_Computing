// RMI registry ����

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RegistryDelegateImpl extends UnicastRemoteObject
		implements
			Registry {

	// ���� Registry
	private Registry localRegistry;

	public static final String DELEGATE_NAME = "REGISTRY_DELEGATE";

	// Ϊ����Registry��������
	public RegistryDelegateImpl() throws RemoteException {
		this.localRegistry = LocateRegistry.getRegistry();
	}
	// Ϊָ���˿ڱ���Registry��������
	public RegistryDelegateImpl(int port) throws RemoteException {
		this.localRegistry = LocateRegistry.getRegistry(port);
	}

	// Ϊ����Registryʵ����������
	public RegistryDelegateImpl(Registry reg) throws RemoteException {
		this.localRegistry = reg;
	}

	// �󶨷���
	public void bind(String name, Remote obj) throws RemoteException,
			AlreadyBoundException, AccessException {
		System.out.println("bind:" + obj.getClass().getName());
		localRegistry.bind(name, obj);
	}

	public String[] list() throws RemoteException, AccessException {
		return localRegistry.list();
	}

	public Remote lookup(String name) throws RemoteException,
			NotBoundException, AccessException {
		return localRegistry.lookup(name);
	}

	public void rebind(String name, Remote obj) throws RemoteException,
			AccessException {
		localRegistry.rebind(name, obj);
	}

	public void unbind(String name) throws RemoteException, NotBoundException,
			AccessException {
		localRegistry.unbind(name);
	}

	// ����������Զ�������ϵ�Registry����
	public static Registry getRegistryDelegate(String remoteHost, int remotePort)
			throws RemoteException, NotBoundException {
		Registry remoteRegistry = LocateRegistry.getRegistry(remoteHost,
				remotePort);
		return (Registry) remoteRegistry.lookup(DELEGATE_NAME);
	}

	public static Registry getRegistryDelegate(String remoteHost)
			throws RemoteException, NotBoundException {
		return getRegistryDelegate(remoteHost, REGISTRY_PORT);
	}

	// ����Registry������Registry����
	public static void main(String[] args) throws AccessException,
			RemoteException, AlreadyBoundException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);
		registry.bind(DELEGATE_NAME, new RegistryDelegateImpl());
		System.out.println("ע������Ѿ�׼������...");
		do {
			try {
				Thread.sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				; // do nothing
			} catch (Throwable e) {
				e.printStackTrace();
				System.exit(1);
			}
		} while (true);
	}
}
