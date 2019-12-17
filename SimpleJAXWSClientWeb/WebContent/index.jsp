<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SimpleJAXWSClient</title>
</head>
<body>
        <form action="/SimpleJAXWSClientWeb/SimpleServlet">
                <p><input type="text" name="endpointurl" value="http://localhost:9080/SimpleJAXWSWeb/HelloService" size="50">
                </p>
                <input type="text" name="yourname" value="STSC">
                <input type="text" name="sleeptime" value="10">
                <p><input type="submit">
        </form>

</body>
</html>