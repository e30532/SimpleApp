package simple.jaxws.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

import simple.jaxws.server.Hello;
import simple.jaxws.server.HelloService;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
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
		System.out.println("simple.jaxws.client.SimpleServlet#doGet >");
        HelloService service = new HelloService();
        Hello proxy = service.getHelloPort();
        BindingProvider bp = (BindingProvider)proxy;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, request.getParameter("endpointurl"));
        System.out.println(proxy.sayHello(request.getParameter("yourname")));
        
        System.out.println("simple.jaxws.client.SimpleServlet#doGet <");	
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
