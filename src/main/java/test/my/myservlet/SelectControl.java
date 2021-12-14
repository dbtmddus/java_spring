package test.my.myservlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.my.mymodel.*;
import java.util.ArrayList;

@WebServlet("/SelectControl") //uri
public class SelectControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectControl() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        StudentDAO stdDAO = new StudentDAO();
        ArrayList<StudentDTO> arr = stdDAO.selectStudent();
        request.setAttribute("stdArr", arr);   
        request.getRequestDispatcher("08.selectview.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}