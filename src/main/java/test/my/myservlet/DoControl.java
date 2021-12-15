package test.my.myservlet;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.my.mymodel.*;
import java.util.ArrayList;

@WebServlet("*.do") // a*.do
public class DoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoControl() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String uri =request.getRequestURI();
        // if(uri=="/a.do"){
        //     request.getRequestDispatcher("a.jsp").forward(request, response);
        // }else if(uri=="/b.do"){
        //     request.getRequestDispatcher("a.jsp").forward(request, response);
        // }
        
        PrintWriter out = response.getWriter();
		out.print("<h1>"+uri+"</h1>");
		out.flush(); 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}