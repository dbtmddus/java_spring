package test.my.myservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.my.mymodel.*;

@WebServlet("/InsertControl")
public class InsertControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        
        String  myname= request.getParameter("myname");
        int  myage= Integer.parseInt( request.getParameter("myage") );
        String  mybirth= request.getParameter("mybirth");
        
        StudentDAO stdDAO = new StudentDAO();
        String result = stdDAO.insertData(myname, myage, mybirth);
        
        request.setAttribute("result", result);
        request.getRequestDispatcher("05.insertview.jsp").forward( request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}