# This page shows you minimal steps to convert an EJB application to a SOAP over JMS application

1. Create an EJB application. The method needs to be promoted.  
![image](https://user-images.githubusercontent.com/22098113/116161326-89f80880-a72e-11eb-85ea-d83d14801dd8.png)   

2. Create an EJB client application. This web module contains the EJB Client jar file in the Depoyment Assembly adapter.  
![image](https://user-images.githubusercontent.com/22098113/116161408-ad22b800-a72e-11eb-8bcd-a023a03dce3f.png)  

3. confirm the EJB application is running fine.  
![image](https://user-images.githubusercontent.com/22098113/116161526-dcd1c000-a72e-11eb-9ea0-dc027b8fa9cd.png)

4. To expose the EJB business logic as a SOAP/JMS service, add the two annotations below.  
![image](https://user-images.githubusercontent.com/22098113/116161554-e78c5500-a72e-11eb-954e-110b01a0fd87.png)  

5. Then, export the ear application.  
![image](https://user-images.githubusercontent.com/22098113/116161590-f8d56180-a72e-11eb-9be1-0acc8b12365e.png)  

6. run endptEnabler command against the ear file. This command will add an EJB module which has the MDB definition of the webservice component. This MDB fetch the message in a queue and pass the message to the business logic.    
```
[root@ladings1 bin]# /opt/IBM/WebSphere/AppServer85ND/bin/endptEnabler.sh -transport jms /home/yamayoshi/SimpleSOAPJMS.ear

WSWS2004I: IBM WebSphere Application Server Version 8.5.5
WSWS2005I: Web services Enterprise Archive Endpoint Enabler.
WSWS2007I: (C) COPYRIGHT International Business Machines Corp. 1997, 2010.

WSWS2003I: Backing up the enterprise archive (EAR) file to: /home/yamayoshi/SimpleSOAPJMS.ear~

WSWS2016I: Loading enterprise archive (EAR) file: /home/yamayoshi/SimpleSOAPJMS.ear
WSWS2017I: Found enterprise bean module: SimpleSOAPJMSEJB.jar
WSWS2024I: Adding jms router for enterprise bean module SimpleSOAPJMSEJB.jar.
WSWS2036I: Saving the enterprise archive (EAR) file /home/yamayoshi/SimpleSOAPJMS.ear...
WSWS2037I: Finished saving the enterprise archive (EAR) file.
WSWS2018I: Finished processing enterprise archive (EAR) file /home/yamayoshi/SimpleSOAPJMS.ear.
[root@ladings1 bin]#
```


7. During the deployment of the EAR file, you need to specify the activation spec and the queue defenition. You need to create the resources in advance.  
![image](https://user-images.githubusercontent.com/22098113/116161874-8ca72d80-a72f-11eb-8a31-558cffab1620.png)  


8. you also need to bind the QCF. By default, the reply Q is created dynamically every time of the webservice invocation.    
![image](https://user-images.githubusercontent.com/22098113/116161900-9cbf0d00-a72f-11eb-99e4-cc59e16e82b7.png)  

9. Generate the webservice client code.  
![image](https://user-images.githubusercontent.com/22098113/116162028-e0197b80-a72f-11eb-9333-261349ae5fb3.png)  
![image](https://user-images.githubusercontent.com/22098113/116162048-f1fb1e80-a72f-11eb-91d9-d15cf852fdf3.png)  

10. As a service endpoint, specify the JMS specific endpoint URL. The client code will delegate the mesesage put to the underlying webservice component layer by using the Q and QCF. "targetService" is the compornent type name (NOT component name).   
![image](https://user-images.githubusercontent.com/22098113/116162124-1f47cc80-a730-11eb-9910-04eebf54b57d.png)  
![image](https://user-images.githubusercontent.com/22098113/116162278-6930b280-a730-11eb-970e-185992ebc9f6.png)  



Note: You can also run endptEnabler in GUI. In the generated EJB module, you will see the MDB definition of WebService component is generated.    
![image](https://user-images.githubusercontent.com/22098113/116165112-1528cc80-a736-11eb-9857-5ec235e5f0ed.png)  
![image](https://user-images.githubusercontent.com/22098113/116165154-3689b880-a736-11eb-88f2-cb4ed91a4dda.png)  
![image](https://user-images.githubusercontent.com/22098113/116165199-5325f080-a736-11eb-965c-65e2517cd69f.png)  
![image](https://user-images.githubusercontent.com/22098113/116165272-7781cd00-a736-11eb-9030-7e8fa28476fe.png)  
