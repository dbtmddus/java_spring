<%@ page contentType = "text/html;charset=utf-8" %>
<%	
    String name = request.getParameter("name");
    String phone = request.getParameter("phone");
    String email = request.getParameter("email");
    String size = request.getParameter("size");
    String[] topping = request.getParameterValues("topping");
    String time = request.getParameter("time");
    String txt = request.getParameter("reqtxt");
%>
<h1> 고객명: <%=name %></h1>
<h1> 전화번호: <%=phone %></h1>
<h1> 이메일: <%=email %></h1>
<h1> 사이즈: <%=size %></h1>
<%
    String strT = String.join( ",", topping );
    out.print("<h1> 토핑: "+strT+"</h1");
%>
<h1>희망배송시간: <%=time %></h1>
<h1> 배송시 요청 사항: <%=txt %></h1>