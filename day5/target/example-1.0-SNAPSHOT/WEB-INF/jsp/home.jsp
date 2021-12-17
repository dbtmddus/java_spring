<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%
    String world = (String)request.getAttribute("world");
    String welcome = (String)request.getAttribute("welcome");
    String formattedDate = (String)request.getAttribute("formattedDate");
%>

<!DOCTYPE html>
<html>
<head>
    <title>main</title>
    <meta charset="UTF-8" />
</head>
<body>
    <div>
        <h1>Hello <%= world %></h1>        
        <p>spring version is <%= welcome %></p>
        <h3><%= formattedDate %></h3>
        <p>EL 표현식 ${world}</p>
    </div>
</body>
</html>
