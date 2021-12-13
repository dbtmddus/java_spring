<%@ page contentType = "text/html;charset=utf-8" %>
<%
    String myname = request.getParameter("myname");
    String myage = request.getParameter("myage");
    String mybirth = request.getParameter("mybirth");
    out.print("<h1>"+myname+"</h1>");
    out.print("<h1>"+myage+"</h1>");
    out.print(mybirth);
%>