package ejbs;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessHello_2759d38c
 */
public class EJSRemoteStatelessHello_2759d38c extends EJSWrapper implements Hello {
	/**
	 * EJSRemoteStatelessHello_2759d38c
	 */
	public EJSRemoteStatelessHello_2759d38c() throws java.rmi.RemoteException {
		super();	}
	/**
	 * say
	 */
	public java.lang.String say(java.lang.String name) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.lang.String _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = name;
			}
	ejbs.HelloBean beanRef = (ejbs.HelloBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.say(name);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 0, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
}
