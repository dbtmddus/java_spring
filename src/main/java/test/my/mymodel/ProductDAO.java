package test.my.mymodel;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    String oracleURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "goorm";
	String pass = "goorm";
	Connection conn = null;
	Statement stmt = null;
    ResultSet rs = null;
    
    public ProductDAO()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(oracleURL, id, pass);
        }catch (Exception ex) {
        }
    }
    
    public String insertData( String name, int qty, String yyyymmdd)
    {
        String sql = String.format("insert into product values('%s',%d,'%s')",name,qty,yyyymmdd);
        try {
            stmt = conn.createStatement();  
            stmt.execute(sql);
            conn.close();
            return "insert success";
        }catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    public ArrayList<ProductDTO> selectProduct()
    {
        String sql ="select * from product";
        ArrayList<ProductDTO> arr = new ArrayList<ProductDTO>();
        try{
            stmt = conn.createStatement();  
            rs= stmt.executeQuery(sql); //select
            while( rs.next())
            {
                String name = rs.getString("name");
                int qty = rs.getInt("qty");
                Date yyyymmdd = rs.getDate("yyyymmdd");
                arr.add( new ProductDTO(name, qty, yyyymmdd.toString() ) );
            }
            rs.close();
            conn.close();
            return arr;
        }catch( Exception ex){
            return null;
        }        
      }
}