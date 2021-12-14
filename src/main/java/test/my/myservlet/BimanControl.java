package test.my.myservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.my.myclass.*;

@WebServlet("/BimanControl")
public class BimanControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BimanControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        int  height= Integer.parseInt( request.getParameter("height") );
        int  weight= Integer.parseInt( request.getParameter("weight") );
        Obese o = new Obese();
        o.calcObesity(height, weight);
        //forward 까지 유효한 변수
        request.setAttribute("result", o.getBimanResult() );
        request.setAttribute("resultImg", o.getBimanImg() );
        
        //forward
        request.getRequestDispatcher("04.bimanview.jsp").forward( request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}