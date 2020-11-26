package ejbs;

/**
 * Remote interface for Enterprise Bean: Hello
 */
public interface Hello extends javax.ejb.EJBObject {

	/**
	 * @param name
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public String say(String name) throws java.rmi.RemoteException;
}
