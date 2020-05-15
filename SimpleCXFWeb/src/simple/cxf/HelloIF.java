package simple.cxf;

import javax.jws.WebService;

@WebService(name = "HelloIF", targetNamespace = "http://cxf.simple/")
public interface HelloIF {
	public String say(String name);
}
