package com.s3.project.thread;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import com.s3.project.thread.JsonParserThread;

public class ThreadInitialize extends HttpServlet{
	 public void init() throws ServletException
	    {
	          /// Automatically java script can run here
	          System.out.println("************");
	          System.out.println("*** Servlet Initialized successfully ***..");
	          System.out.println("***********");
	          JsonParserThread threadMy = new JsonParserThread("Thread Gw");
	  		threadMy.start();

	    }

	    public void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	    {


	    }
}
