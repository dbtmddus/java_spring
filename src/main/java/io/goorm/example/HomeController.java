package io.goorm.example;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.SpringVersion;

import java.text.DateFormat;
import java.util.Date;
import java.io.PrintWriter;



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
import test.my.mybean.*;
import test.my.myclass.*;

@Controller
public class HomeController 
{
    //bean factory 에 생성된 bean객체의 참조를 할당..
    @Autowired
    MyData myData;
    
    @Autowired
    Student std;
    
    @Autowired
    Obese obese;
    
    @Autowired
    ApplicationContext context;
    
    @Autowired
	BasicDataSource dataSource;
    
    @Autowired
    StdDbPoolDAO stdDAO;
    
    @Autowired
    TestPoolDAO testDAO;
    
    
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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) 
    {
        model.addAttribute("test", "spring test");
		return "test"; //forward: test.jsp
	}
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Locale locale, Model model) 
    {
        model.addAttribute("hello", "spring hello");
		return "hello"; //forward: test.jsp
	}
    
    @RequestMapping(value = "/aoptest", method = RequestMethod.GET)
	public String aoptest(Locale locale, Model model) {

		 // //OtherClass my2 = new OtherClass();
		 // int n = myT.method1();
		 // System.out.println("n:"+n);
		 // model.addAttribute("serverTime", "test" );
		return "home";
	}
    /*
    @RequestMapping(value = "/param", method = RequestMethod.GET)
	public String param(HttpServletRequest request, Model model) 
    {
        String myname = request.getParameter("myname");
        String myage = request.getParameter("myage");
        String mybirth = request.getParameter("mybirth");
        
        model.addAttribute("myname", myname);
        model.addAttribute("myage", myage);
        model.addAttribute("mybirth", mybirth);
		return "paramView"; //forward: test.jsp
	}*/
    
    
    @RequestMapping(value = "/param", method = RequestMethod.GET)
	public String param(String myname, int myage, String mybirth, Model model) 
    {
        model.addAttribute("myname", myname);
        model.addAttribute("myage", myage);
        model.addAttribute("mybirth", mybirth);
		return "paramView"; //forward: test.jsp
	}
    
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(String myname, int myage, String mybirth, Model model) 
    {
        StudentDAO stdDAO = new StudentDAO();
        String result = stdDAO.insertData(myname, myage, mybirth);
        model.addAttribute("result", result);
		return "insertView"; //forward: test.jsp
	}
    
    @RequestMapping(value = "/plus", method = RequestMethod.GET)
	public String plus(int num1, int num2, Model model) {
        int result = num1 + num2;
        model.addAttribute("result", result);
        return "calcview";
	}
    
    @RequestMapping(value = "/mydata", method = RequestMethod.GET)
	public void mydata(HttpServletResponse response, Model model) {
        //test.my.myclass.My();
        //test.my.mybean.My();
        
        //MyData obj = new MyData();
        MyData info1 = (MyData)context.getBean(MyData.class);
        System.out.println("==========>"+info1);
        
        System.out.println(myData.getMyData());
        PrintWriter out=null;
        try{
            out = response.getWriter();
            out.print( "<h1>"+myData.getMyData()+"</h1>");
            out.print( "<h1>"+std.getName()+"</h1>");
            out.print( "<h1>"+std.getAge()+"</h1>");
            out.print( "<h1>"+std.getMyData().getMyData()+"</h1>");
            out.flush();
        }catch( Exception ex){
            out.print(ex.getMessage());
        }
	}    
    
    @RequestMapping(value = "/BimanControl", method = RequestMethod.GET)
	public String bimanControl(int height, int weight, Model model) {
        //Obese obj = new Obese();
        /*
        obese.calcObesity(height, weight);
        //forward까지 유효한 변수
        model.addAttribute("result", obese.getBimanResult());
        model.addAttribute("resultImg", obese.getBimanImg());
        //forwarding -> 
        return "04.bimanview";
        */
        synchronized (this){
            return this.processBiman(height, weight, model);
        }
	}
    
    public synchronized String processBiman(int height, int weight, Model model){
        obese.calcObesity(height, weight);
        //forward까지 유효한 변수
        model.addAttribute("result", obese.getBimanResult());
        model.addAttribute("resultImg", obese.getBimanImg());
        return "04.bimanview";
    }
    
    @RequestMapping(value = "/poolInsert", method = RequestMethod.GET)
	public void poolInsert(HttpServletResponse response, Model model) {
        //BasicDataSource dbSource = (BasicDataSource)context.getBean(BasicDataSource.class);
        
        PrintWriter out=null;
        Connection conn=null;
        Statement stmt=null;
        PreparedStatement pstmt=null;
        System.out.println( dataSource.getMaxActive() );
        try{
            //String sql = "insert into student values('dbpool',20,'2021-12-15')";
            String sql = "insert into student values(?,?,?)";
            conn = dataSource.getConnection(); //dbpool idle
            //stmt = conn.createStatement();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"이이");
            pstmt.setInt(2,24);
            pstmt.setString(3,"1989-11-12");
            //stmt.execute(sql);
            //pstmt.execute();
            int nCnt = pstmt.executeUpdate();//반영갯수(insert,delete,update)
            conn.close(); //pool 에 반납
            
            out = response.getWriter();
            out.print( "<h1>추가성공:"+nCnt+"</h1>");
            out.flush();
        }catch( Exception ex){
            out.print(ex.getMessage());
        }
	} 
    
    @RequestMapping(value = "/pooldaoInsert", method = RequestMethod.GET)
	public String pooldaoInsert(HttpServletResponse response, Model model) {
        try{
            //String result = stdDAO.insertStudent("이황",40,"2017-11-12");
            String result = testDAO.insertStudent("이황",40,"2017-11-12");
            model.addAttribute("result",result);
        }catch( Exception ex){
        }
        return "insertView";
	}
    
    @RequestMapping(value = "/pooldaoSelect", method = RequestMethod.GET)
	public String pooldaoSelect(HttpServletResponse response, Model model) {
        try{
            ArrayList<StudentDTO> results = testDAO.selectStudent();
            model.addAttribute("stList", results);
            return "studentList";
        } catch (Exception ex){
            return "실패";
        }
	}
    
    @RequestMapping(value = "/imgsave", method = RequestMethod.POST)
	public String imgsave( @RequestParam("myfile") MultipartFile myfile, Model model) 
    {
        //public String bimanControl( @RequestParam("height") int height, int weight, Model model)
        String fname = myfile.getOriginalFilename();
        System.out.println("전송파일이름:"+fname);
        System.out.println("전송파일크기:"+myfile.getSize() );
        try{
            InputStream sFile = myfile.getInputStream();
            // File file = new File(fname); //"/my/"+fname
            // FileUtils.copyInputStreamToFile( sFile, file);
            String sql = "insert into imgup values(?,?)";
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,fname);
            pstmt.setBlob( 2, sFile );
            pstmt.execute();
            conn.close();
            model.addAttribute("result","수신성공");
        }catch( Exception ex){
            model.addAttribute("result", ex.getMessage());
        }
        return "fileResult";
	}
    
    @RequestMapping(value = "/imgselect", method = RequestMethod.GET)
	public String imgselect(HttpServletResponse response, Model model) {
        ArrayList<ImgDTO> arr = new ArrayList<ImgDTO>();
        
        try{
            String sql = "select * from imgup";
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery( sql );
            while( rs.next())
            {
                String imgname = rs.getString("imgname");
                Blob imgdata = rs.getBlob("imgdata");
                
                byte[] bimg =  imgdata.getBytes(1, (int)imgdata.length());
				String sImg="data:img/png;base64,"+Base64.getEncoder().encodeToString(bimg);
                arr.add( new ImgDTO( imgname, sImg ));
            }
            conn.close();
            model.addAttribute("imgArr", arr );
        }catch( Exception ex){
            model.addAttribute("imgArr", ex.getMessage());
        }
        return "imgview";
	}
    
    //method = { RequestMethod.GET, RequestMethod.POST }
    @RequestMapping(value = "/jsonCall", method = RequestMethod.GET,produces = "application/json; charset=utf8" )
	public @ResponseBody  String jsonCall(HttpServletResponse response, Model model) 
    {
        // JavaScript Object Notation;
        //{"name":"홍길동","age":20} //
        //[{"name":"홍길동","age":20},{"name":"이순신","age":30} ]
        // JSONObject jObj = new JSONObject();
        // jObj.put("name","홍길동");
        // jObj.put("age",20);
        // String makeJson =jObj.toJSONString(); //"{\"name\":\"홍길동\",\"age\":20}"
        // return makeJson;
        JSONArray jarr = new JSONArray();
        JSONObject j1 = new JSONObject();
        j1.put("name","hong");
        j1.put("age",20);
        jarr.add(j1);        
        JSONObject j2 = new JSONObject();
        j2.put("name","lee");
        j2.put("age",30);
        jarr.add(j2);            
        return jarr.toJSONString();
	}
    
    @RequestMapping(value = "/jsonSelect",  method = RequestMethod.GET, produces = "application/json; charset=utf8" )
	public @ResponseBody  String jsonSelect(HttpServletResponse response, Model model) 
    {
        //response.setContentType("text/html; charset=UTF-8");
        JSONArray jarr = new JSONArray();
       try{
            String sql = "select name, age from student";
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery( sql );
            while( rs.next())
            {
                JSONObject jo = new JSONObject();
                String name = rs.getString("name");
                int age = rs.getInt("age");
                jo.put("name",name);
                jo.put("age",age);
                jarr.add( jo );
            }
            conn.close();
        }catch( Exception ex){
           }
        System.out.println(jarr.toJSONString());
        return jarr.toJSONString();
	}
    
    @RequestMapping(value = "/sseSvr", method = RequestMethod.GET)
	public void sseSvr(HttpServletResponse response, Model model) {
        response.setContentType("text/event-stream; charset=UTF-8");
        PrintWriter out=null;
        try{
            String sData =String.format("data:%s\n", testDAO.getJsonData() );
            
            out = response.getWriter();
            //out.print( "data:ssetest\n");
            out.print( sData);
            out.print( "retry:2000\n\n");
            out.flush();
            
            //out.print("close:\n\n");
        }catch( Exception e){
        }
	}    
    
    @RequestMapping(value = "/productListImg", method = RequestMethod.GET)
	public String select(Model model) 
    {
        ProductDAO productDao = new ProductDAO();
        ArrayList<ProductDTO> product = productDao.selectProduct();
        model.addAttribute("product", product);
        return "productSelectViewImg";
	}

}
















