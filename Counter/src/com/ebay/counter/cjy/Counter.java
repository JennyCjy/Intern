package com.ebay.counter.cjy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

public class Counter extends DefaultHandler{
    private File inputFile;
    private BufferedReader input;
    private File outputFile;
    private BufferedWriter output;
  
 
    public Counter(){
    	try {
    		inputFile=new File(Constants.readPath);
        	input = new BufferedReader(new FileReader(inputFile));
        	outputFile=new File(Constants.writePath);
        	if (outputFile.exists()) {
				if (outputFile.delete()) {
					outputFile=new File(Constants.writePath);
				}
			}
        	output= new BufferedWriter(new FileWriter(outputFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getEvents(InputStream xmlStream) throws Exception{  
        SAXParserFactory factory = SAXParserFactory.newInstance();  
        SAXParser parser = factory.newSAXParser();  
        SaxParseService handler = new SaxParseService();  
        parser.parse(xmlStream, handler); 
    }  
	public void parseXML(){
		
	}
	
	public void Count(){
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Constants.getProperties();
	}

}
