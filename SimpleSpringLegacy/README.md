# How to develop a simple spring legacy application

1. Create a new Legacy project.  
![image](https://user-images.githubusercontent.com/22098113/115878578-a45a8980-a483-11eb-8158-5a73323c09d6.png)  

2. selet "Spring MVC Project" as a template.  
![image](https://user-images.githubusercontent.com/22098113/115878647-bccaa400-a483-11eb-9172-a9fcf00e8049.png)  

3. specify the application package.  
![image](https://user-images.githubusercontent.com/22098113/115878714-d10ea100-a483-11eb-8ad1-46c6169ee7b3.png)  

4. a template is generated.  
![image](https://user-images.githubusercontent.com/22098113/115878841-fac7c800-a483-11eb-9495-425a7161f5e3.png)  

5. create a bean which is injected.  
![image](https://user-images.githubusercontent.com/22098113/115879147-4da17f80-a484-11eb-9bb5-e60d7dae9972.png)  

6. define a bean so that it can be injected.  
![image](https://user-images.githubusercontent.com/22098113/115879314-79bd0080-a484-11eb-85fe-a185cf72ceee.png)  

7. By using @AutoWired, we can inject the bean.  
![image](https://user-images.githubusercontent.com/22098113/115879442-a244fa80-a484-11eb-8bca-6ef77ea814bb.png)  

8. If we want to use AOP(like @Interceptor of CDI), create a logic class which can be executed just before/after the method invocatoin.  
![image](https://user-images.githubusercontent.com/22098113/115879813-08ca1880-a485-11eb-9d17-7bba8b5d3451.png)  

9. define a configuration for AOP.  
![image](https://user-images.githubusercontent.com/22098113/115879857-141d4400-a485-11eb-8605-1ac69ba4dc12.png)  

10. Add dependencies for AOP.  
![image](https://user-images.githubusercontent.com/22098113/115880267-82fa9d00-a485-11eb-8f61-16d9493bcda9.png)  

11. Add "liberty-maven-plugin" to pom.xml so that we can develop an application while confirming the behavior on liberty runtime.  
![image](https://user-images.githubusercontent.com/22098113/115880296-8a21ab00-a485-11eb-89f6-982b94492709.png)  

12. access to the application.  
![image](https://user-images.githubusercontent.com/22098113/115880871-26e44880-a486-11eb-9615-3e081b8080fe.png)  
![image](https://user-images.githubusercontent.com/22098113/115880961-39f71880-a486-11eb-8554-a2d8d2137f6e.png)  








