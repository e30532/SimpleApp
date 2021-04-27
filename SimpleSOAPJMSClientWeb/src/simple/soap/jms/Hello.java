
package simple.soap.jms;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "Hello", targetNamespace = "http://jms.soap.simple/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Hello {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "say", targetNamespace = "http://jms.soap.simple/", className = "simple.soap.jms.Say")
    @ResponseWrapper(localName = "sayResponse", targetNamespace = "http://jms.soap.simple/", className = "simple.soap.jms.SayResponse")
    @Action(input = "http://jms.soap.simple/Hello/sayRequest", output = "http://jms.soap.simple/Hello/sayResponse")
    public String say(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
