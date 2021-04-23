# How to develo a simple SpringBoot application


1. To develop a spring framework application, install Spring Toos 3 from Eclipse Marketplace.  
![image](https://user-images.githubusercontent.com/22098113/115875456-319bdf00-a480-11eb-8e69-2e72e58e2e5d.png)

2. File -> New -> Other..., select the starter project.  
![image](https://user-images.githubusercontent.com/22098113/115857127-48363c00-a468-11eb-8f84-844ebf515770.png)  

3. set packaging to WAR so that it can be deployed to liberty/tWAS.  
![image](https://user-images.githubusercontent.com/22098113/115857085-39e82000-a468-11eb-8dc6-6f84d2f49322.png)  

4. Add "Spring Web" since we are going to develop a web module.  
![image](https://user-images.githubusercontent.com/22098113/115857209-5dab6600-a468-11eb-98e3-e76dde90e3c9.png)   

5. click Finish.  
![image](https://user-images.githubusercontent.com/22098113/115857237-669c3780-a468-11eb-99ea-c30057ec69e2.png)   

6. Now the template is generated.  
![image](https://user-images.githubusercontent.com/22098113/115857416-a2cf9800-a468-11eb-8f21-6c6a5224f917.png)  

7. Add "liberty-maven-plugin" to pom.xml so that we can develop an application while confirming the behavior on liberty runtime.  
![image](https://user-images.githubusercontent.com/22098113/115857619-db6f7180-a468-11eb-895f-0c51b34c31ff.png)  

```
			<plugin>
        		<groupId>io.openliberty.tools</groupId>
        		<artifactId>liberty-maven-plugin</artifactId>
        		<version>3.1</version>
    		</plugin>
```

8. create a new run configration.  
![image](https://user-images.githubusercontent.com/22098113/115857679-ed511480-a468-11eb-8b0d-d41dc058fe48.png)  
![image](https://user-images.githubusercontent.com/22098113/115857760-0e196a00-a469-11eb-9898-96ca75725230.png)  

9. set "Base Directory" to the project root and "Goals" to "liberty:dev"
![image](https://user-images.githubusercontent.com/22098113/115857945-520c6f00-a469-11eb-90a9-4063366814b3.png)  

10. Add a REST contoller.  If this application receives a request http://hostname:port/context_root/, then index method is executed.  
![image](https://user-images.githubusercontent.com/22098113/115876857-c81cd000-a481-11eb-81e7-946c39cdef90.png)  

11. To realize a bean injection by @Autowired, create a bean class and its config class.  
![image](https://user-images.githubusercontent.com/22098113/115877264-32ce0b80-a482-11eb-8183-5f70d3c07bab.png)  
![image](https://user-images.githubusercontent.com/22098113/115877336-49746280-a482-11eb-873a-49eaeaa8faa4.png)  

12. If we want to run a specif logic before/after a method, we can use AOP, which is similar to @Interceptor of CDI.  
![image](https://user-images.githubusercontent.com/22098113/115877755-c30c5080-a482-11eb-9cd2-b9ed7893bc56.png)  

13. add dependencies for AOP.  
![image](https://user-images.githubusercontent.com/22098113/115884865-51d09b80-a48a-11eb-8bc3-cb6417aa3cb0.png)  


14. run the application on liberty. The libery maven plugin will download liberty archive and create a server if it doesn't exist, then deploy the application.
![image](https://user-images.githubusercontent.com/22098113/115877837-d91a1100-a482-11eb-9f87-c64dd4f096af.png)  

15. access to the application.  
![image](https://user-images.githubusercontent.com/22098113/115878154-2dbd8c00-a483-11eb-9054-6e501af9966c.png)  
http://localhost:9080/SimpleSpringBoot-0.0.1-SNAPSHOT/  
![image](https://user-images.githubusercontent.com/22098113/115878226-462da680-a483-11eb-9a5c-a37308ae0105.png)  

 
 ※もし、Mavenプロジェクトで
 ```
 ClassNotFoundException: org.w3c.dom.ElementTraversal cannot be found by org.apache.xerces_2.12.1.v20210115-0812
java.lang.reflect.InvocationTargetException
 ```
のようなエラーが出た場合、Eclipseで使用しているJavaのJRE/lib/endorsed/xml-apis-1.4.01.jarを配置すれば抑制できます。 しかし、このxml jarがあると、liberty maven pluginが以下のエラーで動かない場合があります。その場合は、<wlp_home>/etc/server.envでxml jarを配置してないJava homeを指定すれば回避できます。  

```
[INFO] [ERROR   ] CWWKE0701E: bundle com.ibm.ws.org.apache.felix.scr:1.0.51.cl210420210407-0944 (12) Error while loading components of bundle com.ibm.ws.logging.osgi:1.0.51.cl210420210407-0944 (1) java.lang.IllegalStateException: BundleContext is no longer valid com.ibm.ws.config_1.0.51.cl210420210407-0944 [9]
[INFO] 	at org.eclipse.osgi.internal.framework.BundleContextImpl.checkValid(BundleContextImpl.java:1027)
[INFO] 	at [internal classes]
[INFO]   
[INFO] [ERROR   ] CWWKE0701E: FrameworkEvent ERROR org.osgi.framework.BundleException: Exception in com.ibm.ws.config.internal.WSConfigActivator.start() of bundle com.ibm.ws.config.
[INFO] 	at org.eclipse.osgi.internal.framework.BundleContextImpl.startActivator(BundleContextImpl.java:835)
[INFO] 	at [internal classes]
[INFO] Caused by: javax.xml.stream.FactoryConfigurationError: Provider com.ctc.wstx.stax.WstxInputFactory not found
[INFO] 	at javax.xml.stream.XMLInputFactory.newInstance(Unknown Source)
[INFO] 	at com.ibm.ws.kernel.service.util.DesignatedXMLInputFactory$1.run(DesignatedXMLInputFactory.java:35)
[INFO] 	... 1 more
[INFO]  Event:org.osgi.framework.FrameworkEvent[source=com.ibm.ws.config_1.0.51.cl210420210407-0944 [9]] 
```




