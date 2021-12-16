<%@ page contentType = "text/event-stream;charset=utf-8" %>
<%
    out.print("data: hello\n");
    out.print("retry: 2000\n\n");
%>