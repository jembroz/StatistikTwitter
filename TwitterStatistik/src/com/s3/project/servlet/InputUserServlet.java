package com.s3.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.s3.project.dao.UserDAO;
import com.s3.project.bean.UserBean;

/**
 * Servlet implementation class InputUserServlet
 */
@WebServlet("/InputUserServlet")
public class InputUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputUserServlet() {
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
					UserBean userBean =  new UserBean();
					userBean.setUsername(request.getParameter("userName"));
					userBean.setPassword(request.getParameter("password"));
					userBean.setFirstName(request.getParameter("firstName"));
					userBean.setLastName(request.getParameter("lastName"));
					
					boolean regValid = UserDAO.CreateUser(userBean);
					String pesan = "";
					if(regValid){
						pesan = "Register user twitter : "+userBean.getUsername()+" ,berhasil.";
						response.sendRedirect("Success.jsp?pesan="+pesan);
					}else{
						pesan = "Register user twitter : "+userBean.getUsername()+" ,tidak berhasil.";
						response.sendRedirect("Success.jsp"+pesan);
					}
					
				}catch(Exception e){
					
				}
	}

}
