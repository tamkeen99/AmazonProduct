package com.Program;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetProperty {

	
	
	   public  String getProperty(String key) throws IOException {
	    	
	    	FileInputStream f= new FileInputStream("./src/test/resources/LogInCredensials.txt");
	    	
	    	Properties p= new Properties();
	    	p.load(f);
	    	String text = p.getProperty(key);
	    	return text;
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
