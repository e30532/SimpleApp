package ejbs;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessHelloHomeBean_2759d38c
 */
public class EJSStatelessHelloHomeBean_2759d38c extends EJSHome {
	static final long serialVersionUID = 61;
	/**
	 * EJSStatelessHelloHomeBean_2759d38c
	 */
	public EJSStatelessHelloHomeBean_2759d38c() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public ejbs.Hello create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
ejbs.Hello result = null;
boolean createFailed = false;
try {
	result = (ejbs.Hello) super.createWrapper(null);
}
catch (javax.ejb.CreateException ex) {
	createFailed = true;
	throw ex;
} catch (java.rmi.RemoteException ex) {
	createFailed = true;
	throw ex;
} catch (Throwable ex) {
	createFailed = true;
	throw new CreateFailureException(ex);
} finally {
	if (createFailed) {
		super.createFailure(beanO);
	}
}
return result;	}
}
