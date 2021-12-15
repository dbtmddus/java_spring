package test.my.mymodel;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {
    String oracleURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "goorm";
	String pass = "goorm";
	Connection conn = null;
	Statement stmt = null;
    ResultSet rs = null;
    
    public StudentDAO()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(oracleURL, id, pass);
        }catch (Exception ex) {
        }
    }
    
    public String insertData( String name, int age, String birth)
    {
        String sql = String.format("insert into student values('%s',%d,'%s')",name,age,birth);
        //String sql ="insert into student(name, age, birth) values('이순신',30,'1989-11-12')";
        try {
            stmt = conn.createStatement();  
            stmt.execute(sql);
            conn.close();
            return "insert success";
        }catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    public String deleteData(int age)
    {
        String sql = String.format("delete from student where age=%d",age);
        //String sql ="insert into student(name, age, birth) values('이순신',30,'1989-11-12')";
        try {
            stmt = conn.createStatement();  
            stmt.execute(sql);
            conn.close();
            return "delete success";
        }catch (Exception ex) {
            return ex.getMessage();
        }
    }
    public ArrayList<StudentDTO> selectStudent()
    {
        String sql ="select * from student";
        ArrayList<StudentDTO> arr = new ArrayList<StudentDTO>();
        try{
            stmt = conn.createStatement();  
            rs= stmt.executeQuery(sql); //select
            while( rs.next())
            {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Date birth = rs.getDate("birth");
                arr.add( new StudentDTO(name, age, birth.toString() ) );
            }
            rs.close();
            conn.close();
            return arr;
        }catch( Exception ex){
            return null;
        }        
      }
    
}




