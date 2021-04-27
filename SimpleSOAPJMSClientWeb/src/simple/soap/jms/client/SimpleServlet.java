package simple.soap.jms.client;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

import simple.soap.jms.Hello;
import simple.soap.jms.HelloService;
import simple.soap.jms.view.HelloRemote;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//    @EJB(name="ejb/Hello")
//    private HelloRemote hello = null;       
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
//		System.out.println(hello.say("IBM"));
        HelloService service = new HelloService();
        Hello proxy = service.getHelloPort();
        BindingProvider bp = (BindingProvider)proxy;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, 
        		"jms:jndi:jms/SimpleQ?targetService=Hello&jndiConnectionFactoryName=jms/SimpleQCF");
        proxy.ping();
//                System.out.println(proxy.say("IBM"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
