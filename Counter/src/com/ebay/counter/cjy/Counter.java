package com.ebay.counter.cjy;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;

public class Counter extends DefaultHandler{
	private File inputFile;
    private File outputFile;
    private BufferedWriter output;
  
 
    public Counter(){
    	try {
    		inputFile= new File(Constants.readPath);
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
    
    public int[] parseXML() throws Exception{  
        SAXParserFactory factory = SAXParserFactory.newInstance();  
        SAXParser parser = factory.newSAXParser();  
        SaxParseService handler = new SaxParseService();
        InputStream xmlStream= new FileInputStream(inputFile);
        parser.parse(xmlStream, handler); 
        int one = handler.getEventListOne().size();
        int two = handler.getEventListTwo().size();
        int three = handler.getEventListThree().size();
        int four = handler.getEventListFour().size();
        System.out.println(one+" "+two+" "+three+" "+four);
        return new int[]{one,two,three,four};
    }  
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Constants.getProperties();
       Counter counter = new Counter();
       try {
		counter.parseXML();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
