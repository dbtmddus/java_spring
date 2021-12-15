package test.my.mymodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.*;


import java.sql.Connection;
import java.util.ArrayList;

@Component
public class TestPoolDAO {
    
    @Autowired
    BasicDataSource dataSource;
    
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    public String insertStudent(String name, int age, String birth){
        try{
            String sql = "insert into student values(?, ?, ?)";
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, birth);
            int nCnt = pstmt.executeUpdate();
            conn.close();
            
            return "추가 성공: " + nCnt;
        } catch (Exception ex){
            return ex.getMessage();
        }
    }
    
    public ArrayList<StudentDTO> selectStudent(){
        try{
            String sql = "select * from student";
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ArrayList<StudentDTO> studentList = new ArrayList<StudentDTO>();
            
            while(rs.next()){
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Date birth = rs.getDate("birth");
                studentList.add(new StudentDTO(name, age, birth.toString()));
            }
            
            rs.close();
            conn.close();
            return studentList;
        } catch (Exception e){
            return null;
        }
    }
}
