package com.s3.project.test;
import sun.org.mozilla.javascript.internal.json.JsonParser;

//Testing untuk Thread
//Author Didit
import com.s3.project.thread.*;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonParserThread threadMy = new JsonParserThread("Thread Gw");
		threadMy.start();
		
	}

}
