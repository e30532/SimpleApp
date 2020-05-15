package simple.cxf;

import javax.jws.WebService;

@WebService(targetNamespace = "http://cxf.simple/", endpointInterface = "simple.cxf.HelloIF", portName = "HelloPort", serviceName = "HelloService")
public class Hello implements HelloIF {

	@Override
	public String say(String name) {
		// TODO Auto-generated method stub
		return "Hello " + name;
	}

}
