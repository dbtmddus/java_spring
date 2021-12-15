package io.goorm.example;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.SpringVersion;

import java.text.DateFormat;
import java.util.Date;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.Locale;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.apache.commons.dbcp.BasicDataSource;
import java.sql.*;
import test.my.mymodel.*;
import test.my.mybean.*;
import test.my.myclass.*;
import java.util.concurrent.ThreadLocalRandom;

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
    
    @RequestMapping(value = "/RKDNLQKDNLQH", method = RequestMethod.GET)
	public String RKDNLQKDNLQH(String user_input, Model model) {
       int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
       String cpu_input = "가위";
       String cpu_inputImg = "/image/s.jpeg";
       if (randomNum == 1){
          model.addAttribute("cpu_input", "가위");            
          cpu_inputImg = "/image/s.jpeg";
       }else if (randomNum == 2){
          model.addAttribute("cpu_input", "바위");            
          cpu_inputImg = "/image/r.jpeg";
       }else {
          model.addAttribute("cpu_input", "보");            
          cpu_inputImg = "/image/b.png";
       }
       model.addAttribute("cpu_inputImg",cpu_inputImg);            

            
       int userNum = 1;
       String user_inputImg = "/image/s.jpeg";
       if (user_input.equals("가위")){
           userNum = 1;
           user_inputImg = "/image/s.jpeg";
       }else if (user_input.equals("바위")){
           userNum = 2;
           user_inputImg = "/image/r.jpeg";
       }else {
           userNum = 3;
           user_inputImg = "/image/b.png";
       }
       model.addAttribute("user_input", user_input);            
       model.addAttribute("user_inputImg", user_inputImg);            
    
        if ( userNum == ((randomNum+1)%3) ){
            model.addAttribute("result", "win");
        }else if ( userNum == randomNum ){
            model.addAttribute("result", "draw");            
        }else {
            model.addAttribute("result", "lose");            
        }
        
        return "day3_sub";
	}               
}
