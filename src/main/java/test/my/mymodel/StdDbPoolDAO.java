package test.my.mymodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.*;

public class StdDbPoolDAO 
{
    @Autowired
	BasicDataSource dataSource;
    
    Connection conn=null;
    Statement stmt=null;
    PreparedStatement pstmt=null;
    
    public String insertStudent( String name, int age, String birth){
        try{
            String sql = "insert into student values(?,?,?)";
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setInt(2,age);
            pstmt.setString(3,birth);
            int nCnt = pstmt.executeUpdate();//반영갯수(insert,delete,update)
            conn.close(); //pool 에 반납
            return "추가 성공:"+nCnt;
        }catch( Exception ex){
            return "실패:"+ex.getMessage();
        }        
    }
}
