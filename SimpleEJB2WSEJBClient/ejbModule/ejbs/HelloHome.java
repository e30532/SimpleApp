package ejbs;

/**
 * Home interface for Enterprise Bean: Hello
 */
public interface HelloHome extends javax.ejb.EJBHome {

	/**
	 * Creates a default instance of Session Bean: Hello
	 */
	public ejbs.Hello create() throws javax.ejb.CreateException, java.rmi.RemoteException;
}
