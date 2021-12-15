<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="test.my.myclass.*" %>
<%
    MyTest obj = new MyTest();
%>
<h1><%=obj.hello() %></h1>
<img src="/image/a.jpg">