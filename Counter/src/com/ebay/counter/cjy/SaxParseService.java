package com.ebay.counter.cjy;


import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParseService extends DefaultHandler {
	  private ArrayList<Event> eventListOne=null;
	  private ArrayList<Event> eventListTwo=null;
	  private ArrayList<Event> eventListThree=null;
	  private ArrayList<Event> eventListFour=null;
	  private Event event =null;
	  private String preTag = null;
	  private String  key=null;
	  
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
	@Override  
	public void startDocument() throws SAXException {  
		eventListOne=new ArrayList<Event>();
    	eventListTwo = new ArrayList<Event>();
    	eventListThree=new ArrayList<Event>();
    	eventListFour = new ArrayList<Event>();
	}  
	  
	@Override  
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
	     if ("LastEvent".equals(qName)) {
			event=new Event();
			System.out.println("------LastEvent Starts-------");
		 }
	     if ("entry".equals(qName)) {
			key=attributes.getValue("key");
		}
	     preTag = qName;
	}  
	  
	@Override  
	public void endElement(String uri, String localName, String qName) throws SAXException {  
		 if ("LastEvent".equals(qName)) {
			if(event.getType().equals(Constants.eventType_one)&&event.getApp()!=null){
				eventListOne.add(event);
				System.out.println("---->Add to List one");
			}
			if (event.getType().equals(Constants.eventType_two)&&event.getApp()!=null) {
				eventListTwo.add(event);
				System.out.println("---->Add to List two");
			}
			if (event.getType().equals(Constants.eventType_three)&&event.getApp()!=null&&event.getSnev()!=null) {
				if (event.getSnev().equals("1")) {
					eventListThree.add(event);
					System.out.println("---->Add to List three");
				}
			}
			if (event.getType().equals(Constants.eventType_four)&&event.getApp()!=null&&event.getRv()!=null) {
				eventListFour.add(event);
				System.out.println("---->Add to List four");
			}
			event=null;
		}
	      preTag=null;
	      key=null;
	}  
	      
	@Override  
	public void characters(char[] ch, int start, int length) throws SAXException {  
	       if (preTag!=null) {
			 String content = new String(ch,start,length);
			 if ("entry".equals(preTag)) {
				if (key.equals(Constants.KEYTYPE)) {
					event.setType(content);
					System.out.println(key+":"+content);
				}
				if(key.equals(Constants.KEYAPP)){
					event.setApp(content);
					System.out.println(key+":"+content);
				}
				if (key.equals(Constants.KEYSNEV)) {
					event.setSnev(content);
					System.out.println(key+":"+content);
				}
				if (key.equals(Constants.KEYRV)) {
					event.setRv(content);
					System.out.println(key+":"+content);
				}
			}
		}
	}  
	 
}
