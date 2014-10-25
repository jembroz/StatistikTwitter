package com.s3.project.thread;

/*
Authors : Di2t
Date    : 5-9-2014
From http://examples.javacodegeeks.com/core-java/json/java-json-parser-example/
*/

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterObjectFactory;
 
public class FetchTwittThread implements Runnable {

	private Thread t;
	private String threadName;
	private Boolean shutdown = Boolean.FALSE;
	//int delay = 1000 * 60 * 2;
	int delay = 1000 * 10;
	
    
	public FetchTwittThread(String name){
		threadName = name;
		System.out.println("Creating thread : "+name);
	}
     
	public void destroy(){
		System.out.println("Thread is stop");
		this.shutdown = Boolean.TRUE;
	}
	
    public void run() {
    	while (shutdown.equals(Boolean.FALSE)){
	    	System.out.println("Running " +  threadName );
	    	
	    	Twitter twitter = new TwitterFactory().getInstance();
	        System.out.println("Saving public timeline.");
	        
	        try {
	        	
	        	
	    		
	    		// get the property value and print it out
	    		String filePath = "D:/statuses";
	    		
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
    }
    
    public void start(){
    	 System.out.println("Starting : " +  threadName );
         if (t == null)
         {
            t = new Thread (this, threadName);
            t.start ();
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

