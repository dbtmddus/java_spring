<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="java.sql.*"%>
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
	ResultSet rs = null;
    try{
        String sql ="select * from student";
		conn = DriverManager.getConnection(oracleURL, id, pass);
		stmt = conn.createStatement();  
        //stmt.execute(sql); //insert, delete, update
        rs= stmt.executeQuery(sql); //select
        while( rs.next())
        {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            Date birth = rs.getDate("birth");
            out.print("<h1>"+name+age+birth+"</h1>");
        }
        rs.close();
        conn.close();
    }catch( Exception ex){
        out.print( ex.getMessage() );
    }
%>

