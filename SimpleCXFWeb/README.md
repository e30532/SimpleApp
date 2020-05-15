
CXF runtime can be downloaded from here.

https://archive.apache.org/dist/cxf/

![image](https://user-images.githubusercontent.com/22098113/82026696-12bc9680-96ce-11ea-83ef-6f19c49b7da2.png)


Tomcat can be downloaded from here.

https://tomcat.apache.org/download-90.cgi

![image](https://user-images.githubusercontent.com/22098113/82026865-50212400-96ce-11ea-8dcb-4d372d621b7a.png)

Extract both of them.

![image](https://user-images.githubusercontent.com/22098113/82026913-6202c700-96ce-11ea-9f4f-b8fecce154ad.png)

create a web module. (I created an EAR project also because I wanted to deply this to not only tomcat, but also WAS)

![image](https://user-images.githubusercontent.com/22098113/82026966-7ba40e80-96ce-11ea-818c-cb749310ad98.png)

Enable CXF 2.x Web Services, then click "Further configuration available"

![image](https://user-images.githubusercontent.com/22098113/82027127-bad25f80-96ce-11ea-9b4d-385b60dc1a6e.png)

specify the CXF runtime. (If you haven't had one, create it)

![image](https://user-images.githubusercontent.com/22098113/82027217-d89fc480-96ce-11ea-8ad4-43dd366147d5.png)
![image](https://user-images.githubusercontent.com/22098113/82027335-0a189000-96cf-11ea-9d7b-70275b992f25.png)
![image](https://user-images.githubusercontent.com/22098113/82027370-1ac90600-96cf-11ea-8554-a5a01b6d77f2.png)
![image](https://user-images.githubusercontent.com/22098113/82027390-26b4c800-96cf-11ea-84ba-135b31c93b87.png)

create a tomcat test server

![image](https://user-images.githubusercontent.com/22098113/82040508-a6e42900-96e1-11ea-87da-cd0a59aa46e0.png)
![image](https://user-images.githubusercontent.com/22098113/82040546-b499ae80-96e1-11ea-9c76-64fdfdf711f1.png)

create a web service interface

![image](https://user-images.githubusercontent.com/22098113/82040676-ed398800-96e1-11ea-95f7-536146588482.png)

create an implementatin class

![image](https://user-images.githubusercontent.com/22098113/82040778-12c69180-96e2-11ea-9e1e-42a8fa553c4b.png)

"Create Web Service"

![image](https://user-images.githubusercontent.com/22098113/82040947-58835a00-96e2-11ea-85f5-fb8ca70dd25e.png)
![image](https://user-images.githubusercontent.com/22098113/82040973-646f1c00-96e2-11ea-9eb3-013f7728790a.png)

The interface and the implementation class are annotated and the web module will be deployed to tomcat.

![image](https://user-images.githubusercontent.com/22098113/82041347-f8d97e80-96e2-11ea-8d48-d666e8b0cdab.png)

create a client application

![image](https://user-images.githubusercontent.com/22098113/82041770-ac427300-96e3-11ea-93bf-8375cb85bc26.png)

generate a webservice client code

![image](https://user-images.githubusercontent.com/22098113/82042275-95505080-96e4-11ea-8010-90733a25755d.png)
![image](https://user-images.githubusercontent.com/22098113/82042344-af8a2e80-96e4-11ea-989d-bde7321f98c7.png)
![image](https://user-images.githubusercontent.com/22098113/82042640-35a67500-96e5-11ea-92fa-b2c83e078bc3.png)

generate a servlet which calls the web service

![image](https://user-images.githubusercontent.com/22098113/82042907-ab124580-96e5-11ea-98dd-b6a896df56fa.png)

deploy the client application to the tomcat and access to the servlet. You might need to restart the tomcat.

![image](https://user-images.githubusercontent.com/22098113/82043474-9da98b00-96e6-11ea-8e02-964fe78218e9.png)

If we simply deploy the module, you will get the error below during the application startup.
```
[5/15/20 4:08:24:747 PDT] 00000058 WSModuleDescr E   WSWS7027E: JAX-WS Service Descriptions could not be correctly built because of the following error: javax.xml.ws.WebServiceException: Validation error: WebMethod annotations cannot exist on implentation when WebService.endpointInterface is set.  Implementation class: org.apache.cxf.wsn.services.JaxwsPublisher
```
And when the client servlet tries to call the service, you will see the error below.
```
Caused by: java.lang.IncompatibleClassChangeError: org/apache/neethi/AssertionBuilderFactory is not an interface
```
In WAS env, you need to follow the instruction below.

https://www.ibm.com/support/knowledgecenter/SSEQTP_9.0.5/com.ibm.websphere.base.doc/ae/twbs_thirdparty.html

![image](https://user-images.githubusercontent.com/22098113/82044710-d0548300-96e8-11ea-83df-eabe3bc006e2.png)
![image](https://user-images.githubusercontent.com/22098113/82044917-2b867580-96e9-11ea-924f-9c4b703a924a.png)

After that, you will get the error below.
```
Error 404: javax.servlet.UnavailableException: SRVE0201E: Servlet [simple.cxf.SimpleServlet]: not a servlet class 
```

You will need to eliminate the following jar file from the application.

![image](https://user-images.githubusercontent.com/22098113/82045549-3e4d7a00-96ea-11ea-8bce-4cfa9691e569.png)
![image](https://user-images.githubusercontent.com/22098113/82045451-13632600-96ea-11ea-9c05-8e25ca8a284d.png)

Then, you will get the error below.

```
Error 500: javax.xml.ws.WebServiceException: Could not send Message. 
```

```
[5/15/20 4:28:31:400 PDT] 00000101 ReflectionSer I   Creating Service {http://cxf.simple/}HelloIFService from class simple.cxf.HelloIF
[5/15/20 4:28:34:273 PDT] 00000101 PhaseIntercep W org.apache.cxf.phase.PhaseInterceptorChain doDefaultLogging Interceptor for {http://cxf.simple/}HelloIFService#{http://cxf.simple/}say has thrown exception, unwinding now
                                 org.apache.cxf.interceptor.Fault: Could not send Message.
	at org.apache.cxf.interceptor.MessageSenderInterceptor$MessageSenderEndingInterceptor.handleMessage(MessageSenderInterceptor.java:64)
	at org.apache.cxf.phase.PhaseInterceptorChain.doIntercept(PhaseInterceptorChain.java:272)
	at org.apache.cxf.endpoint.ClientImpl.doInvoke(ClientImpl.java:565)
	at org.apache.cxf.endpoint.ClientImpl.invoke(ClientImpl.java:474)
	at org.apache.cxf.endpoint.ClientImpl.invoke(ClientImpl.java:377)
	at org.apache.cxf.endpoint.ClientImpl.invoke(ClientImpl.java:330)
	at org.apache.cxf.frontend.ClientProxy.invokeSync(ClientProxy.java:96)
	at org.apache.cxf.jaxws.JaxWsClientProxy.invoke(JaxWsClientProxy.java:135)
	at com.sun.proxy.$Proxy146.say(Unknown Source)
	at simple.cxf.SimpleServlet.doGet(SimpleServlet.java:36)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:687)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:790)
	at com.ibm.ws.webcontainer.servlet.ServletWrapper.service(ServletWrapper.java:1235)
	at com.ibm.ws.webcontainer.servlet.ServletWrapper.handleRequest(ServletWrapper.java:779)
	at com.ibm.ws.webcontainer.servlet.ServletWrapper.handleRequest(ServletWrapper.java:478)
	at com.ibm.ws.webcontainer.servlet.ServletWrapperImpl.handleRequest(ServletWrapperImpl.java:179)
	at com.ibm.ws.webcontainer.filter.WebAppFilterManager.invokeFilters(WebAppFilterManager.java:1124)
	at com.ibm.ws.webcontainer.webapp.WebApp.handleRequest(WebApp.java:4219)
	at com.ibm.ws.webcontainer.webapp.WebAppImpl.handleRequest(WebAppImpl.java:2210)
	at com.ibm.ws.webcontainer.webapp.WebGroup.handleRequest(WebGroup.java:304)
	at com.ibm.ws.webcontainer.WebContainer.handleRequest(WebContainer.java:1030)
	at com.ibm.ws.webcontainer.WSWebContainer.handleRequest(WSWebContainer.java:1817)
	at com.ibm.ws.webcontainer.channel.WCChannelLink.ready(WCChannelLink.java:382)
	at com.ibm.ws.http.channel.inbound.impl.HttpInboundLink.handleDiscrimination(HttpInboundLink.java:465)
	at com.ibm.ws.http.channel.inbound.impl.HttpInboundLink.handleNewRequest(HttpInboundLink.java:532)
	at com.ibm.ws.http.channel.inbound.impl.HttpInboundLink.processRequest(HttpInboundLink.java:318)
	at com.ibm.ws.http.channel.inbound.impl.HttpInboundLink.ready(HttpInboundLink.java:289)
	at com.ibm.ws.tcp.channel.impl.NewConnectionInitialReadCallback.sendToDiscriminators(NewConnectionInitialReadCallback.java:214)
	at com.ibm.ws.tcp.channel.impl.NewConnectionInitialReadCallback.complete(NewConnectionInitialReadCallback.java:113)
	at com.ibm.ws.tcp.channel.impl.AioReadCompletionListener.futureCompleted(AioReadCompletionListener.java:175)
	at com.ibm.io.async.AbstractAsyncFuture.invokeCallback(AbstractAsyncFuture.java:217)
	at com.ibm.io.async.AsyncChannelFuture.fireCompletionActions(AsyncChannelFuture.java:161)
	at com.ibm.io.async.AsyncFuture.completed(AsyncFuture.java:138)
	at com.ibm.io.async.ResultHandler.complete(ResultHandler.java:204)
	at com.ibm.io.async.ResultHandler.runEventProcessingLoop(ResultHandler.java:775)
	at com.ibm.io.async.ResultHandler$2.run(ResultHandler.java:905)
	at com.ibm.ws.util.ThreadPool$Worker.run(ThreadPool.java:1909)
Caused by: java.net.ConnectException: ConnectException invoking http://localhost:8080/SimpleCXFWeb/services/HelloPort: Connection refused: connect
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:83)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:57)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:437)
	at org.apache.cxf.transport.http.HTTPConduit$WrappedOutputStream.mapException(HTTPConduit.java:1339)
	at org.apache.cxf.transport.http.HTTPConduit$WrappedOutputStream.close(HTTPConduit.java:1323)
	at org.apache.cxf.transport.AbstractConduit.close(AbstractConduit.java:56)
	at org.apache.cxf.transport.http.HTTPConduit.close(HTTPConduit.java:628)
	at org.apache.cxf.interceptor.MessageSenderInterceptor$MessageSenderEndingInterceptor.handleMessage(MessageSenderInterceptor.java:62)
	... 36 more
Caused by: java.net.ConnectException: Connection refused: connect
	at java.net.DualStackPlainSocketImpl.waitForConnect(Native Method)
	at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:96)
	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:380)
	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:236)
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:218)
	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:183)
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:403)
	at java.net.Socket.connect(Socket.java:682)
	at sun.net.NetworkClient.doConnect(NetworkClient.java:187)
	at sun.net.www.http.HttpClient.openServer(HttpClient.java:494)
	at sun.net.www.http.HttpClient.openServer(HttpClient.java:589)
	at sun.net.www.http.HttpClient.<init>(HttpClient.java:256)
	at sun.net.www.http.HttpClient.New(HttpClient.java:360)
	at sun.net.www.http.HttpClient.New(HttpClient.java:378)
	at sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:1238)
	at sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:1174)
	at sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:1068)
	at sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:1002)
	at sun.net.www.protocol.http.HttpURLConnection.getOutputStream0(HttpURLConnection.java:1352)
	at sun.net.www.protocol.http.HttpURLConnection.getOutputStream(HttpURLConnection.java:1327)
	at org.apache.cxf.transport.http.URLConnectionHTTPConduit$URLConnectionWrappedOutputStream.setupWrappedStream(URLConnectionHTTPConduit.java:174)
	at org.apache.cxf.transport.http.HTTPConduit$WrappedOutputStream.handleHeadersTrustCaching(HTTPConduit.java:1283)
	at org.apache.cxf.transport.http.HTTPConduit$WrappedOutputStream.onFirstWrite(HTTPConduit.java:1239)
	at org.apache.cxf.transport.http.URLConnectionHTTPConduit$URLConnectionWrappedOutputStream.onFirstWrite(URLConnectionHTTPConduit.java:201)
	at org.apache.cxf.io.AbstractWrappedOutputStream.write(AbstractWrappedOutputStream.java:47)
	at org.apache.cxf.io.AbstractThresholdOutputStream.write(AbstractThresholdOutputStream.java:69)
	at org.apache.cxf.transport.http.HTTPConduit$WrappedOutputStream.close(HTTPConduit.java:1296)
	... 39 more
```

Of course, we need to modify the client code as well.

```
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientProxyFactoryBean factory= new JaxWsProxyFactoryBean();
//		factory.setAddress("http://localhost:8080/SimpleCXFWeb/services/HelloPort");
		factory.setAddress(request.getParameter("url"));
		factory.setServiceClass(ClientProxyFactoryBean.class);
		HelloIF proxy = factory.create(HelloIF.class);
		System.out.println(proxy.say("Chihiro"));		
	}
```

Now the application works on WAS.
http://localhost:9080/SimpleCXFClientWeb/SimpleServlet?url=http://localhost:9080/SimpleCXFWeb/services/HelloPort

