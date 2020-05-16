package simple.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.9
 * 2020-05-15T03:50:13.818-07:00
 * Generated source version: 2.7.9
 * 
 */
@WebService(targetNamespace = "http://cxf.simple/", name = "HelloIF")
@XmlSeeAlso({ObjectFactory.class})
public interface HelloIF {

    @WebMethod
    @RequestWrapper(localName = "say", targetNamespace = "http://cxf.simple/", className = "simple.cxf.Say")
    @ResponseWrapper(localName = "sayResponse", targetNamespace = "http://cxf.simple/", className = "simple.cxf.SayResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String say(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}