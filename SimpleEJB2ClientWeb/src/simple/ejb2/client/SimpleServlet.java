package simple.ejb2.client;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbs.Hello;
import ejbs.HelloHome;

/**
 * Servlet implementation class SimpleServlet
 */
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InitialContext initialContext = null;
		HelloHome hellohome = null;
		Hello hello = null;
		try{

			//For tWAS
			/**
			Hashtable table = new Hashtable();
			table.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
			table.put(Context.PROVIDER_URL,"corbaloc:iiop:fyre2:2809");
			initialContext = new InitialContext(table);
			 **/
			//For liberty
			initialContext = new InitialContext();

			//For tWAS
			/**
			java.lang.Object o = initialContext.lookup("java:comp/env/ejb/Hello");
			**/
			//For Liberty
			java.lang.Object o = initialContext.lookup("java:global/SimpleEJB2/SimpleEJB2EJB/Hello!ejbs.HelloHome");

			hellohome = (HelloHome)PortableRemoteObject.narrow(o, HelloHome.class);
			hello = hellohome.create();
			System.out.println(hello.say("Chihiro"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
