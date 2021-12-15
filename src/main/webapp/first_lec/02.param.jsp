<%@ page contentType = "text/html;charset=utf-8" %>
<%	
    String myname = request.getParameter("myname");
    String myage = request.getParameter("myage");
    String mybirth = request.getParameter("mybirth");
    String s = String.format("<h1>이름:%s</h1>", myname);
    out.print( s );
%>
<h1>나이:<%=myage %></h1>
<h1>생일:<%=mybirth %></h1>