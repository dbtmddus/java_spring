<%@ page language="java" contentType = "text/html;charset=utf-8" pageEncoding="UTF-8" %>

<%!
    String my="korea";
    String fn(){
        return "jsp";
    }
%>
<%
    String str="hello";
%>
<%
    //PrintWriter out = new PrintWriter(); //html make버퍼에 기록
    out.print("<h1>test</h1>");
    out.print("<h2>test</h2>");
    out.print("<h2>"+my+"</h2>");
    out.print("<h2>"+str+"</h2>");
%>


<h3>test</h3>
<h4><%=str %></h4>
<h5><%=fn() %></h5>