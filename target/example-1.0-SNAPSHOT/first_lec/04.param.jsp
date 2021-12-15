<%@ page contentType = "text/html;charset=utf-8" %>
<%	
//   hobby=낚시&hobby=골프
    String myname = request.getParameter("myname");
    String myage = request.getParameter("myage");
    String mybirth = request.getParameter("mybirth");
    String myphone = request.getParameter("myphone");
    String myemail = request.getParameter("myemail");
    String color = request.getParameter("color");
    String[] hobby = request.getParameterValues("hobby");
    String company = request.getParameter("company");
    String mytime = request.getParameter("mytime");
    String mytxt = request.getParameter("mytxt");

    for( String h : hobby)
    {
        System.out.println(h);
    }
    // {"낚시","골프"}
%>
<h1><%=myname %></h1>
<h1><%=myage %></h1>
<h1><%=mybirth %></h1>
<h1><%=myphone %></h1>
<h1><%=myemail %></h1>
<h1><%=color %></h1>
<%
    String strH = String.join( ",", hobby );  //낚시,골프
    out.print("<h1>"+strH+"</h1");

    out.print("<ul>");
    for( String h : hobby)
    {
        out.print("<li>"+h+"</li>");    
    }
    out.print("</ul>");
%>
<h1><%=company %></h1>
<h1><%=mytime %></h1>
<h1><%=mytxt %></h1>




