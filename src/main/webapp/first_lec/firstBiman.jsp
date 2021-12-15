<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="test.my.myclass.*" %>
<%!
    // String result;
    // String resultImg;
    String getObesity( int height, int weight)
    {
        double standardWeight = (height - 100) * 0.85;
        double obesityRate = (weight / standardWeight ) * 100;

        if (obesityRate <= 90) 
            return "under";
        else if (obesityRate > 90 && obesityRate <= 110 ) 
            return "normal";
        else if (obesityRate >110 && obesityRate <= 120 ) 
            return "over";
        else
            return "obese";
    }
%>

<%
    Obese o = new Obese();
    int height = Integer.parseInt( request.getParameter("height") );
    int weight = Integer.parseInt( request.getParameter("weight") );
    o.calcObesity(height,weight);
    // String result = getObesity(height,weight);
    // String resultImg = "/image/"+result+".png";
    
%>
<h1>결과:<%=o.getBimanResult() %></h1>
<img src=<%=o.getBimanImg() %> > 

