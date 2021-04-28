
This is a memo for JSF2.3 + WebSocket.

```
    <featureManager>
		<feature>jaxrs-2.1</feature>
		<feature>jsfContainer-2.3</feature>
		<feature>jsp-2.3</feature>
		<feature>localConnector-1.0</feature>
		<feature>websocket-1.1</feature>
    </featureManager>
```

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>AJSF</groupId>
  <artifactId>AJSF</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>war</packaging>
  <dependencies>
	<!-- https://mvnrepository.com/artifact/javax/javaee-api -->
	<dependency>
    	<groupId>javax</groupId>
    	<artifactId>javaee-api</artifactId>
    	<version>8.0</version>
    	<scope>provided</scope>
	</dependency>
	<!-- https://mvnrepository.com/artifact/org.glassfish/javax.faces -->
	<dependency>
    	<groupId>org.glassfish</groupId>
    	<artifactId>javax.faces</artifactId>
    	<version>2.3.9</version>
	</dependency>
  </dependencies>
  <build>
    <sourceDirectory>src</sourceDirectory>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.7.0</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
      <plugin>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.2.1</version>
        <configuration>
          <warSourceDirectory>WebContent</warSourceDirectory>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

```
package a.jsf;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;

@Named
@ApplicationScoped
public class PushBean implements Serializable
{
    @Inject @Push(channel = "hello")
    private PushContext push;

    public void helloAction()
    {
        push.send("Hello");
    }
}

```

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<h:head>
    <title>Websocket Example</title>
</h:head>
<h:body>
    <h:form>
        <h:commandButton value="Hello" action="#{pushBean.helloAction}">
            <f:ajax />
        </h:commandButton>
    </h:form>

    <f:websocket channel="hello" onmessage="socketListener" />

    <hr/>

    <div id="helloId"/>

    <script type="text/javascript">
        function socketListener(message, channel, event) {
            document.getElementById("helloId").innerHTML += message + "&lt;br/&gt;";
        }
    </script>
</h:body>

</html>
```
![image](https://user-images.githubusercontent.com/22098113/116356592-7041e880-a836-11eb-88de-cfbe863a45c0.png)

http://host:9080/AJSF/index.xhtml
