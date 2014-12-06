package com.s3.project.test;

//Testing untuk Thread
//Author Didit
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.s3.project.thread.*;

import IndonesianNLP.IndonesianNETagger;
import IndonesianNLP.IndonesianSentenceFormalization;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*JsonParserThread threadMy = new JsonParserThread("Thread Gw");
		threadMy.start();
		*/
		BufferedReader br = null;
		try{
		String kata = "P4d4 h4r1 minggu ku turut ayah ke kota. Naik delman istimewa, ku duduk di muka !@#$%^&*()-_+={}|\\/.,><;:'\"";
		
		IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
		String kataRslt = formalizer.formalizeSentence(kata.toLowerCase());
		System.out.println(kataRslt);
		
		String ktRsltRplc = kataRslt.replaceAll("[!@#$%^&*()-_+={}|\\/.,><;:'\"]", "");
		System.out.println(ktRsltRplc);
		
		File file = new File("sample.txt");
		 
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(ktRsltRplc);
		bw.close();
		
		IndonesianNETagger inner = new IndonesianNETagger();
        inner.NETagFile("sample.txt", "hasil.arff");
        
        
        br = new BufferedReader(new FileReader("hasil.arff"));
        String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			System.out.println(sCurrentLine);
		}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		}finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
