<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="java.sql.*" %>
<%
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		out.print("드라이버로딩 성공");
	} catch (Exception ex) {
		out.print("드라이버로딩 실패");
	}
	String oracleURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "goorm";
	String pass = "goorm";
	Connection conn = null;
	Statement stmt = null;
    try{
        String sql ="insert into student(name, age, birth) values('이순신',30,'1989-11-12')";
		conn = DriverManager.getConnection(oracleURL, id, pass);
		stmt = conn.createStatement();  
        stmt.execute(sql);
        conn.close();
        out.print("<h1>insert success</h1>");
    }catch( Exception ex){
        out.print( ex.getMessage() );
    }
%>


