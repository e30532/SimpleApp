
package simple.jaxws.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the simple.jaxws.server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SleepResponse_QNAME = new QName("http://server.jaxws.simple/", "sleepResponse");
    private final static QName _Ping_QNAME = new QName("http://server.jaxws.simple/", "ping");
    private final static QName _SayHello_QNAME = new QName("http://server.jaxws.simple/", "sayHello");
    private final static QName _Sleep_QNAME = new QName("http://server.jaxws.simple/", "sleep");
    private final static QName _SayHelloResponse_QNAME = new QName("http://server.jaxws.simple/", "sayHelloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: simple.jaxws.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Sleep }
     * 
     */
    public Sleep createSleep() {
        return new Sleep();
    }

    /**
     * Create an instance of {@link SayHello }
     * 
     */
    public SayHello createSayHello() {
        return new SayHello();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link SayHelloResponse }
     * 
     */
    public SayHelloResponse createSayHelloResponse() {
        return new SayHelloResponse();
    }

    /**
     * Create an instance of {@link SleepResponse }
     * 
     */
    public SleepResponse createSleepResponse() {
        return new SleepResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SleepResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.simple/", name = "sleepResponse")
    public JAXBElement<SleepResponse> createSleepResponse(SleepResponse value) {
        return new JAXBElement<SleepResponse>(_SleepResponse_QNAME, SleepResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.simple/", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.simple/", name = "sayHello")
    public JAXBElement<SayHello> createSayHello(SayHello value) {
        return new JAXBElement<SayHello>(_SayHello_QNAME, SayHello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sleep }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.simple/", name = "sleep")
    public JAXBElement<Sleep> createSleep(Sleep value) {
        return new JAXBElement<Sleep>(_Sleep_QNAME, Sleep.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.simple/", name = "sayHelloResponse")
    public JAXBElement<SayHelloResponse> createSayHelloResponse(SayHelloResponse value) {
        return new JAXBElement<SayHelloResponse>(_SayHelloResponse_QNAME, SayHelloResponse.class, null, value);
    }

}
