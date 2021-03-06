package com.s3.project.servlet;

/*author : Didit
Date : 28-9-2014
Description : Servlet untuk menghandle Deskripsi
*/
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.s3.project.bean.UserBean;
import com.s3.project.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet",urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -686030824510868832L;

public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {

try
{	    

     UserBean user = new UserBean();
     user.setUsername(request.getParameter("un"));
     user.setPassword(request.getParameter("pw"));

     user = UserDAO.login(user);
	   		    
     if (user.isValid())
     {
	        
          HttpSession session = request.getSession(true);	    
          session.setAttribute("currentSessionUser",user);
          response.sendRedirect("Home.jsp"); //logged-in page      		
     }
	        
     else 
          response.sendRedirect("InvalidLogin.jsp"); //error page 
} 
		
		
catch (Throwable theException) 	    
{
     System.out.println(theException); 
}
       }
	}