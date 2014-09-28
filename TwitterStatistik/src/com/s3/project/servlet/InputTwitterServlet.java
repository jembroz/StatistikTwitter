package com.s3.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.s3.project.dao.RegisteredTweeterDAO;

/**
 * Servlet implementation class InputTwitterServlet
 */
@WebServlet("/InputTwitterServlet")
public class InputTwitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputTwitterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Method untuk Register user twitter
		try{
			String screenName = request.getParameter("screenName");
			boolean regValid = RegisteredTweeterDAO.CreateUser(screenName);
			String pesan = "";
			if(regValid){
				pesan = "Register user twitter : "+screenName+" ,berhasil.";
				response.sendRedirect("Success.jsp?pesan="+pesan);
			}else{
				pesan = "Register user twitter : "+screenName+" ,tidak berhasil.";
				response.sendRedirect("Success.jsp"+pesan);
			}
			
		}catch(Exception e){
			
		}
	}

}
