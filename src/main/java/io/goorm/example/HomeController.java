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
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.*;
import test.my.mymodel.*;

@Controller
public class HomeController 
{
    
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
    
    @RequestMapping(value = "/sub2_input", method = RequestMethod.GET)
	public String sub2_input(String p_name, int p_num, String p_date, Model model)  
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(oracleURL, id, pass);
        }catch (Exception ex) {
        }
        
        String sql = String.format("insert into student values('%s',%d,'%s')",p_name,p_num,p_date);
        try {
            stmt = conn.createStatement();  
            stmt.execute(sql);
            conn.close();
        }catch (Exception ex) {
        }
            model.addAttribute("sub2_input", result);
		return "day2_sub"; //forward: test.jsp
	}        
    
    @RequestMapping(value = "/sub2_show", method = RequestMethod.GET)
	public String sub2_show(Model model) 
    {
        StudentDAO stdDAO = new StudentDAO();
        String result = stdDAO.insertData(myname, myage, mybirth);
        model.addAttribute("sub2_show", result);
		return "day2_sub"; //forward: test.jsp
	}        
    
    
        
        
}
