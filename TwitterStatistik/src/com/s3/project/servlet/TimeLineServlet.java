package com.s3.project.servlet;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.*;

import java.util.List;
import java.util.Properties;

/**
 * Servlet implementation class TimeLineServlet
 */
@WebServlet("/TimeLineServlet")
public class TimeLineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeLineServlet() {
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
		Twitter twitter = new TwitterFactory().getInstance();
        System.out.println("Saving public timeline.");
        Properties prop = new Properties();
    	InputStream input = null;
        try {
        	input = new FileInputStream(request.getServletContext().getRealPath("/")+"config.properties");
        	
    		// load a properties file
    		prop.load(input);
     
    		// get the property value and print it out
    		String filePath = prop.getProperty("PathFileStatus");
    		
            //new File("statuses").mkdir();
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                String rawJSON = TwitterObjectFactory.getRawJSON(status);
                String fileName = filePath+"/" + status.getId() + ".json";
                storeJSON(rawJSON, fileName);
                System.out.println(fileName + " - " + status.getText());
            }
            System.out.print("\ndone.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to store tweets: " + ioe.getMessage());
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
        }
	}
	
	private static void storeJSON(String rawJSON, String fileName) throws IOException {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(fileName);
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write(rawJSON);
            bw.flush();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ignore) {
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException ignore) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ignore) {
                }
            }
        }
    }

}
