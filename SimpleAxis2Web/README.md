Download axis2 runtime (I use 1.5.4 purposefully since it seems the embedded axis2 of WASv8 and v9 is v1.5.4)

http://archive.apache.org/dist/axis/axis2/java/core/1.5.4/

Install the axis2 runtime to eclipse

![image](https://user-images.githubusercontent.com/22098113/82112339-f3357480-9786-11ea-970c-452f243b8870.png)

add axis2 facet

![image](https://user-images.githubusercontent.com/22098113/82112461-23314780-9788-11ea-8b29-ce10cc56876b.png)

create a class which will be published as a webservice

![image](https://user-images.githubusercontent.com/22098113/82112516-786d5900-9788-11ea-8854-cdd4a00aaffb.png)

create a web service. I use Tomocat since I want to try the migration from Tomcat to WAS.

![image](https://user-images.githubusercontent.com/22098113/82112532-9c309f00-9788-11ea-9ba3-3c2bbd269428.png)
![image](https://user-images.githubusercontent.com/22098113/82112547-bcf8f480-9788-11ea-89df-1a93fe3062dd.png)
![image](https://user-images.githubusercontent.com/22098113/82112568-f16cb080-9788-11ea-85f7-b4933033e2ae.png)

create a web service client app

![image](https://user-images.githubusercontent.com/22098113/82112590-31cc2e80-9789-11ea-8eca-096512bfdef3.png)

create a web service client

![image](https://user-images.githubusercontent.com/22098113/82112611-5e804600-9789-11ea-8ea9-df7f77168b60.png)

create a servlet which calls the service

![image](https://user-images.githubusercontent.com/22098113/82112654-c8005480-9789-11ea-8139-350c742bf50a.png)

deploy the client module to Tomocat and access to the application. You would need to restart Tomcat

http://localhost:8080/SimpleAxis2ClientWeb/SimpleServlet

Let's try to deploy the app to WASv9.

First, you will see the error below during the startup of the applications.
```
[5/15/20 23:33:31:245 PDT] 0000006c annotation    W com.ibm.ws.webcontainer.annotation.WASAnnotationHelper collectClasses SRVE8000W: Skipped class that failed to initialize for annotation scanning.
                                 java.lang.ClassNotFoundException: org.apache.axis2.webapp.AxisAdminServlet
```

To fix this, delete AxisAdminServlet setting from web.xml.

Then, if you access to the wsdl (http://localhost:9080/SimpleAxis2Web/services/Hello?wsdl), you will hit the issue below.

```
Caused by: java.lang.ClassNotFoundException: org.apache.commons.fileupload.FileUploadException cannot be found by org.apache.axis2_7.0.0
```

This is because we need to follow the link below for both of the client and server apps

https://www.ibm.com/support/knowledgecenter/SSEQTP_8.5.5/com.ibm.websphere.base.doc/ae/twbs_thirdparty.html

![image](https://user-images.githubusercontent.com/22098113/82112830-ce8fcb80-978b-11ea-9317-8d1dd1fa2dbb.png)
![image](https://user-images.githubusercontent.com/22098113/82112872-17478480-978c-11ea-984d-257cdf83d6ed.png)

But the access to the wsdl still fails

```
[5/15/20 23:46:26:164 PDT] 00000135 ModuleDeploye E org.apache.axis2.deployment.ModuleDeployer deploy The addressing-1.5.4.mar module, which is not valid, caused org.apache.axis2.handlers.addressing.AddressingInHandler incompatible with org.apache.axis2.engine.Handler
                                 java.lang.ClassCastException: org.apache.axis2.handlers.addressing.AddressingInHandler incompatible with org.apache.axis2.engine.Handler
	at org.apache.axis2.deployment.util.Utils.addFlowHandlers(Utils.java:93)
```

To fix this, copy the two mar file (addressing and mex) to lib and rename the extension to jar.

![image](https://user-images.githubusercontent.com/22098113/82112911-a18fe880-978c-11ea-89c1-e2c83d0a248b.png)

Now you can access to the wsdl.

![image](https://user-images.githubusercontent.com/22098113/82112990-55917380-978d-11ea-9d5e-ffc600cd4e4d.png)

Then, access to the client and it fails with the error below.

```
[5/15/20 23:57:24:163 PDT] 00000135 HTTPSender    I org.apache.axis2.transport.http.HTTPSender sendViaPost Unable to sendViaPost to url[http://localhost:8080/SimpleAxis2Web/services/Hello]
                                 java.net.ConnectException: Connection refused: connect
	at java.net.DualStackPlainSocketImpl.waitForConnect(Native Method)
	at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:96)
	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:380)
	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:236)
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:218)
	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:183)
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:403)
	at java.net.Socket.connect(Socket.java:682)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:90)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:55)
	at java.lang.reflect.Method.invoke(Method.java:508)
	at org.apache.commons.httpclient.protocol.ReflectionSocketFactory.createSocket(ReflectionSocketFactory.java:140)
	at org.apache.commons.httpclient.protocol.DefaultProtocolSocketFactory.createSocket(DefaultProtocolSocketFactory.java:125)
	at org.apache.commons.httpclient.HttpConnection.open(HttpConnection.java:707)
	at org.apache.commons.httpclient.MultiThreadedHttpConnectionManager$HttpConnectionAdapter.open(MultiThreadedHttpConnectionManager.java:1361)
	at org.apache.commons.httpclient.HttpMethodDirector.executeWithRetry(HttpMethodDirector.java:387)
	at org.apache.commons.httpclient.HttpMethodDirector.executeMethod(HttpMethodDirector.java:171)
	at org.apache.commons.httpclient.HttpClient.executeMethod(HttpClient.java:397)
	at org.apache.commons.httpclient.HttpClient.executeMethod(HttpClient.java:346)
	at org.apache.axis2.transport.http.AbstractHTTPSender.executeMethod(AbstractHTTPSender.java:557)
	at org.apache.axis2.transport.http.HTTPSender.sendViaPost(HTTPSender.java:199)
	at org.apache.axis2.transport.http.HTTPSender.send(HTTPSender.java:76)
	at org.apache.axis2.transport.http.CommonsHTTPTransportSender.writeMessageWithCommons(CommonsHTTPTransportSender.java:400)
	at org.apache.axis2.transport.http.CommonsHTTPTransportSender.invoke(CommonsHTTPTransportSender.java:225)
	at org.apache.axis2.engine.AxisEngine.send(AxisEngine.java:438)
	at org.apache.axis2.description.OutInAxisOperationClient.send(OutInAxisOperation.java:402)
	at org.apache.axis2.description.OutInAxisOperationClient.executeImpl(OutInAxisOperation.java:229)
	at org.apache.axis2.client.OperationClient.execute(OperationClient.java:165)
	at simple.axis2.HelloStub.say(HelloStub.java:181)
	at simple.axis2.SimpleServlet.doGet(SimpleServlet.java:29)
 ```
 
 This is because the endpoint url is still pointing the tomcat. Let's modify the client logic a bit.
 
 ![image](https://user-images.githubusercontent.com/22098113/82113108-4eb73080-978e-11ea-82ee-df4fbeba19ca.png)
 
 Now you can access to the service running on WAS.
 
http://localhost:9080/SimpleAxis2ClientWeb/SimpleServlet?url=http://localhost:9080/SimpleAxis2Web/services/Hello
