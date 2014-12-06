package com.s3.project.thread;

/*
Authors : Di2t
Date    : 5-9-2014
From http://examples.javacodegeeks.com/core-java/json/java-json-parser-example/
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;
import IndonesianNLP.IndonesianNETagger;
import IndonesianNLP.IndonesianSentenceFormalization;

import com.s3.project.bean.TwittBean;
import com.s3.project.dao.TwittDAO;
import com.s3.project.bean.TwittTaggerBean;
import com.s3.project.dao.TwittTagDAO;
 
public class JsonParserThread implements Runnable {

	private Thread t;
	private String threadName;
	private Boolean shutdown = Boolean.FALSE;
	//int delay = 1000 * 60 * 2;
	int delay = 1000 * 10;
	
    
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
	    	
	    	BufferedReader br = null;
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
	                //System.out.println(it); //Untuk keperluan pengecekkan
	                //Di formalize dulu
	                
	                IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
	        		String kicauFrml = formalizer.formalizeSentence(status.getText().toLowerCase());
	        		String kicauFrmlCln = kicauFrml.replaceAll("[?`~!@#$%^&*()-_+={}|\\/.,><;:'\"â™¥â™Ç♥]", "");
	        		

	        		File fileInput = new File("inputSrc.txt");
	        		File fileOutput = new File("hasilDst.arff");
	        		
	        		// if file doesnt exists, then create it
	        		if (!fileInput.exists()) {
	        			fileInput.createNewFile();
	        		}
	        		if (!fileOutput.exists()) {
	        			fileOutput.createNewFile();
	        		}

	        		FileWriter fw = new FileWriter(fileInput.getAbsoluteFile());
	        		BufferedWriter bw = new BufferedWriter(fw);
	        		bw.write(kicauFrmlCln);
	        		bw.close();
	        		
	        		IndonesianNETagger inner = new IndonesianNETagger();
	                inner.NETagFile("inputSrc.txt", "hasilDst.arff");
	                
	                
	                br = new BufferedReader(new FileReader("hasilDst.arff"));
	                String sCurrentLine;
	                int iCounter1 = 1;//Counter untuk setiap baris dalam file
	        		while ((sCurrentLine = br.readLine()) != null) {
	        			System.out.println(sCurrentLine);
	        			TwittTaggerBean twtTagBean = new TwittTaggerBean();
	        			twtTagBean.setTwittID((int) status.getId());
	        			twtTagBean.setNourut(iCounter1);
	        			
	        			int iCounter2 = 1;//Counter untuk setiap pemisahan kata setelah di split oleh posttagging
	        			for(String retrieval: sCurrentLine.split(",")){
	        				if(iCounter2==1){
	        					twtTagBean.setDeskripsi(retrieval);//Deskripsi
	        				}else if(iCounter2==2){
	        					twtTagBean.setJenisDesc(retrieval);//Jenis Deskripsi
	        				}else{
	        					twtTagBean.setKategori(retrieval);//Kategori
	        				}
	        				iCounter2++;
	        			}
	        			Date date = new Date();
	        			twtTagBean.setTanggal(new Timestamp(date.getTime()));
	        			Boolean twittTagDAO = TwittTagDAO.InsertTwittTagger(twtTagBean);
	        			System.out.println("Insert TwittTagger : " + twittTagDAO);
	        			iCounter1++;
	        		}
	                
	        		file.getAbsoluteFile().delete();
	            }
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	            System.out.println("Failed to store tweets: " + ioe.getMessage());
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to get timeline: " + te.getMessage());
	        }finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
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

