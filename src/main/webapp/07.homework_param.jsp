<%@ page contentType = "text/html;charset=utf-8" %>
<%
    int num1 = Integer.parseInt(request.getParameter("num1"));
    int num2 = Integer.parseInt(request.getParameter("num2"));
    
    Double standard_weight = (num1-100)*0.85;
    Double obese_point = num2/standard_weight * 100; 
   
    out.println("<div id=\"box1\"");    
    out.println("키:"+num1+"<br>");
    out.println("몸무게:"+num2+"<br>");
    if ( obese_point < 90){
         out.print("결과:저체중</div>");
         out.print("<img src=\"/image/under.png\">");
    } 
    else if ( obese_point < 120){
         out.print("결과:정상</div>");
         out.print("<img src=\"/image/normal.png\">");
    }
    else{
         out.print("결과:과체중</div>");
         out.print("<img src=\"/image/over.png\">");
    }                   
%>