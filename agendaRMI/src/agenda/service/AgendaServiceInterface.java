// AgendaService½Ó¿Ú
package agenda.service;

import java.io.IOException;
import org.jdom.JDOMException;
import agenda.exception.AgendaException;

public interface AgendaServiceInterface extends java.rmi.Remote {

	public abstract String[] excuteCommand(String command)
			throws AgendaException, java.rmi.RemoteException, IOException,
			JDOMException;
}