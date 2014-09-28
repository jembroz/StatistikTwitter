package com.s3.project.thread;

/*
Authors : Di2t
Date    : 5-9-2014
From http://examples.javacodegeeks.com/core-java/json/java-json-parser-example/
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import com.s3.project.bean.TwittBean;
import com.s3.project.dao.TwittDAO;
 
public class JsonParserThread implements Runnable {

	private Thread t;
	private String threadName;
	private Boolean shutdown = Boolean.FALSE;
	//int delay = 1000 * 60 * 2;
	int delay = 1000;
	
    
	public JsonParserThread(String name){
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
	    	
	    	try {
	            File[] files = new File("D:/statuses").listFiles(new FilenameFilter() {
	                public boolean accept(File dir, String name) {
	                    return name.endsWith(".json");
	                }
	            });
	            for (File file : files) {
	                String rawJSON = readFirstLine(file);
	                Status status = TwitterObjectFactory.createStatus(rawJSON);
	                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	                String it = insertTwitter(status);
	                System.out.println(it);
	                /*file.delete();*/
	            }
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	            System.out.println("Failed to store tweets: " + ioe.getMessage());
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to get timeline: " + te.getMessage());
	        }
	    	if (shutdown) break;
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
    
    private static String readFirstLine(File fileName) throws IOException {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(fileName);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            return br.readLine();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ignore) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignore) {
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
    
    private static String insertTwitter(Status status){
    	String message= "";
    	try{
    		TwittBean ut = new TwittBean();
    		ut.setIdTwitt(String.valueOf(status.getId()));
    		ut.setTextTwitt(status.getText());
    		ut.setScreenName(status.getUser().getScreenName());
    		ut.setRetweetCount(status.getRetweetCount());
    		ut.setCreatedAt(status.getCreatedAt());
    		Boolean twittDAO = TwittDAO.CreateUser(ut);
    		if(twittDAO){
    			message = "Data Berhasil di Insert";
    		}else{
    			message = "Data tidak Berhasil di Insert";
    		}
    		
    	}catch(Exception e){
    		message = "Data tidak berhasil di Insert";
    	}
    	return message;
    }
}

