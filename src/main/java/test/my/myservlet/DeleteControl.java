package test.my.myservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.my.mymodel.*;

@WebServlet("/DeleteControl") //uri
public class DeleteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteControl() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        int myage = Integer.parseInt(request.getParameter("myage"));
        
        StudentDAO stdDAO = new StudentDAO();
        String result = stdDAO.deleteData(myage);
        
        request.setAttribute("result", result);   
        request.getRequestDispatcher("06.deleteview.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}