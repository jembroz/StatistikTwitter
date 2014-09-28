package com.s3.project.test;

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
