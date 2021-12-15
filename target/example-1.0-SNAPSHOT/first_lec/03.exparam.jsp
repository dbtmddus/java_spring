<%@ page contentType = "text/html;charset=utf-8" %>
<%	
    String num1 = request.getParameter("num1");
    String num2 = request.getParameter("num2");
    Integer sum = Integer.parseInt(num1) + Integer.parseInt(num2);
    String s = String.format("<h1>합:%d</h1>", sum);
    out.print( s );
%>
<h1>합:<%=sum %></h1>