
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

