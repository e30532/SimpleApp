package simple.sfsb.client;

import java.io.IOException;
import java.util.Hashtable;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import simple.sfsb.view.HelloRemote;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    @EJB(name="ejb/Hello")
    private HelloRemote hello = null;
    
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
		HttpSession session = request.getSession(true);
		HelloRemote hello = (HelloRemote)session.getAttribute("bean");
		if (hello!=null) {
			for(int i = 0; i<10; i++) {
				hello.say();
				try {
					Thread.sleep(1000*120);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		} else {
			InitialContext initialContext = null;
			try{

				Hashtable table = new Hashtable();
				table.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
				table.put(Context.PROVIDER_URL,"corbaloc:iiop:localhost:2809");
				initialContext = new InitialContext(table);
				java.lang.Object o = initialContext.lookup("simple.sfsb.view.HelloRemote");
				hello = (HelloRemote)PortableRemoteObject.narrow(o, HelloRemote.class);
				session.setAttribute("bean", hello);
			}catch(Exception e){
				e.printStackTrace();
			}
			
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
