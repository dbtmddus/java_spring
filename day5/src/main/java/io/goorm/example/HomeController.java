package io.goorm.example;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.SpringVersion;

import java.text.DateFormat;
import java.util.Date;
import java.io.PrintWriter;
import java.util.HashMap;


import java.util.Locale;
import java.util.ArrayList;
import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.apache.commons.dbcp.BasicDataSource;
import java.sql.*;
import test.my.mymodel.*;
import my.aoptest.*;
import com.test.mybatis.*;

@Controller
public class HomeController 
{

    @Autowired
	BasicDataSource dataSource;
    
    @Autowired
	Hello hello;
    
    @Autowired
	StudentDAOImpl daoImpl;
    
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);        
        model.addAttribute("welcome", SpringVersion.getVersion());
        model.addAttribute("world", "spring test");
        model.addAttribute("formattedDate", formattedDate);
                
		return "home";
	}
    
  @RequestMapping(value = "/prodsave", method = RequestMethod.POST)
	public String prodsave( 
        @RequestParam("myname") String myname,
        @RequestParam("myqty") int myqty,
        @RequestParam("mydate") String mydate,
        @RequestParam("myfile") MultipartFile myfile,
        Model model) 
    {
        String fname = myfile.getOriginalFilename();
        System.out.println("전송파일이름:"+fname);
        System.out.println("전송파일크기:"+myfile.getSize() );
        try{
            InputStream sFile = myfile.getInputStream();
            // File file = new File(fname); //"/my/"+fname
            // FileUtils.copyInputStreamToFile( sFile, file);
            String sql = "insert into product values(?,?,?,?)";
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, myname);
            pstmt.setInt(2, myqty);
            pstmt.setString(3, mydate);
            pstmt.setBlob(4, sFile);
            pstmt.execute();
            conn.close();
            model.addAttribute("result","수신성공");
        }catch( Exception ex){
            model.addAttribute("result", ex.getMessage());
        }
        return "insertView";
	}    

    @RequestMapping(value = "/prodlist", method = RequestMethod.GET)
	public String prodlist(HttpServletResponse response, Model model) {
        ArrayList<ProductDTO> arr = new ArrayList<ProductDTO>();
        
        try{
            String sql = "select * from product";
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery( sql );
            while( rs.next())
            {
                String myname = rs.getString("myname");
                int myqty = rs.getInt("myqtry");
                String mydate = rs.getString("mydate");
                Blob imgdata = rs.getBlob("myimg");
                
                byte[] bimg =  imgdata.getBytes(1, (int)imgdata.length());
				String sImg="data:img/png;base64,"+Base64.getEncoder().encodeToString(bimg);
                arr.add( new ProductDTO( myname, myqty, mydate, sImg ));
            }
            conn.close();
            model.addAttribute("prodArr", arr );
        }catch( Exception ex){
            model.addAttribute("prodArr", ex.getMessage());
        }
        return "prodview";
	}
    
    @RequestMapping(value = "/aoptest", method = RequestMethod.GET)
	public String aoptest(Locale locale, Model model) {
        
        // String result= hello.testA();
        // System.out.println("result:"+result);
        hello.method1();
		return "aopview";
	}
    
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) 
    {
    	int n =  daoImpl.insertStudent( new StudentModel("이순신", 40, "2021-11-12") );
		model.addAttribute("result","성공");
		System.out.println("갯수:"+n);
		return "insertView";
	}    
    @RequestMapping(value = "/select", method = RequestMethod.GET)
	public String select(Locale locale, Model model) 
    {
    	ArrayList<StudentModel> arr =  daoImpl.selectStudent();
		model.addAttribute("stdArr",arr);
		return "studentView";
	}        
    
    @RequestMapping(value = "/selectOrder", method = RequestMethod.GET)
	public String selectOrder(Locale locale, Model model) 
    {
    	ArrayList<StudentModel> arr =  daoImpl.selectStudentOrder();
		model.addAttribute("stdArr",arr);
		return "studentView";
    }   
    
    @RequestMapping(value = "/selectW", method = RequestMethod.GET)
	public String selectW(Locale locale, Model model) 
    {
    	ArrayList<StudentModel> arr =  daoImpl.selectStudentWhere("이순신");
		model.addAttribute("stdArr",arr);
		return "studentView";
	} 
    
    @RequestMapping(value = "/selectO", method = RequestMethod.GET)
	public String selectO(Locale locale, Model model) 
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "이순신");
        map.put("age", 20);
        
    	ArrayList<StudentModel> arr =  daoImpl.selectStudentOr(map);
		model.addAttribute("stdArr",arr);
		return "studentView";
	} 
}





