package com.s3.project.thread;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import com.s3.project.thread.JsonParserThread;
import com.s3.project.thread.FetchTwittThread;

public class ThreadInitialize extends HttpServlet{
	
	private static final long serialVersionUID = 6161841584108332050L;

	public void init() throws ServletException
	    {
	          /// Automatically java script can run here
	        System.out.println("************");
	        System.out.println("*** Servlet Initialized successfully ***..");
	        System.out.println("***********");
	        JsonParserThread jsonParserThd = new JsonParserThread("Thread JSon Parser");
	  		jsonParserThd.start();
	  		FetchTwittThread fatchTwittThd = new FetchTwittThread("Thread Fetch Twitter");
	  		fatchTwittThd.start();

	    }

	    public void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	    {


	    }
}
