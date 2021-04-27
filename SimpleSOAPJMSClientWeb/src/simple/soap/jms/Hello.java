
package simple.soap.jms;

import java.util.concurrent.Future;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "Hello", targetNamespace = "http://jms.soap.simple/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Hello {


    /**
     * 
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "ping", targetNamespace = "http://jms.soap.simple/", className = "simple.soap.jms.Ping")
    @Action(input = "http://jms.soap.simple/Hello/ping")
    public void ping();

    /**
     * 
     * @param arg0
     * @return
     *     returns javax.xml.ws.Response<simple.soap.jms.SayResponse>
     */
    @WebMethod(operationName = "say")
    @RequestWrapper(localName = "say", targetNamespace = "http://jms.soap.simple/", className = "simple.soap.jms.Say")
    @ResponseWrapper(localName = "sayResponse", targetNamespace = "http://jms.soap.simple/", className = "simple.soap.jms.SayResponse")
    public Response<SayResponse> sayAsync(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @param asyncHandler
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "say")
    @RequestWrapper(localName = "say", targetNamespace = "http://jms.soap.simple/", className = "simple.soap.jms.Say")
    @ResponseWrapper(localName = "sayResponse", targetNamespace = "http://jms.soap.simple/", className = "simple.soap.jms.SayResponse")
    public Future<?> sayAsync(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<SayResponse> asyncHandler);

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
