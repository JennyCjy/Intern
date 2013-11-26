package com.ebay.counter.cjy;

import java.awt.List;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParseService extends DefaultHandler {
	  private ArrayList<Event> eventListOne;
	  private ArrayList<Event> eventListTwo;
	  private ArrayList<Event> eventListThree;
	  private ArrayList<Event> eventListFour;
	  private String preTag = null;
	  
	public ArrayList<Event> getEventListOne() {
		return eventListOne;
	}
	public void setEventListOne(ArrayList<Event> eventListOne) {
		this.eventListOne = eventListOne;
	}
	public ArrayList<Event> getEventListTwo() {
		return eventListTwo;
	}
	public void setEventListTwo(ArrayList<Event> eventListTwo) {
		this.eventListTwo = eventListTwo;
	}
	public ArrayList<Event> getEventListThree() {
		return eventListThree;
	}
	public void setEventListThree(ArrayList<Event> eventListThree) {
		this.eventListThree = eventListThree;
	}
	public ArrayList<Event> getEventListFour() {
		return eventListFour;
	}
	public void setEventListFour(ArrayList<Event> eventListFour) {
		this.eventListFour = eventListFour;
	}
	public SaxParseService(){
			eventListOne=new ArrayList<Event>();
	    	eventListTwo = new ArrayList<Event>();
	    	eventListThree=new ArrayList<Event>();
	    	eventListFour = new ArrayList<Event>();
	  }
	@Override  
	public void startDocument() throws SAXException {  
		
	}  
	  
	@Override  
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
	     
	}  
	  
	@Override  
	public void endElement(String uri, String localName, String qName) throws SAXException {  
		
	      preTag=null;
	}  
	      
	@Override  
	public void characters(char[] ch, int start, int length) throws SAXException {  
	       
	}  
	 
}
