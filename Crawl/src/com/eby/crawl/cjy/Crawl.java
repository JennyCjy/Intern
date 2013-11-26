package com.eby.crawl.cjy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Crawl extends Thread{
	private String name;
	private String url;
	private int  runCount;
	BufferedWriter output;
	public Crawl(String url,BufferedWriter output,int  runCount,String name){
		this.name=name;
		this.url=url;
		this.output=output;
		this.runCount =runCount;
	}
	public  Crawl(String url,BufferedWriter output,String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.url=url;
		this.output=output;
		this.runCount=1;
	}
		
	public void run(){
		for (int i = 0; i < runCount; i++) {
			System.out.println("------------------------Thread-"+name+"-"+(i+1)+" Start-----------------------");
			this.crawl((i+1));
		}
	}
	public void crawl(int id){
		String  webURL="(http|https)://.*";
		Pattern patternOne = Pattern.compile(webURL,Pattern.CASE_INSENSITIVE);
		if (url!=null) {
			printCrawlUrlInfo(url,id);
			Matcher matcherOne = patternOne.matcher(url);
			if (matcherOne.find()) {
				String content = getContent(url,id);
				String  eventData = extractData(content);
				InputToFile(eventData,id);
			}
			else {
				System.out.println("URL format error ");
			}
		}
		else {
			System.out.println("URL is null ");
		}
		
	}
     
	private String extractData(String content) {
		// TODO Auto-generated method stub
		String eventData="";
		String  xmlParse="\\<LastEvent\\>.*\\</LastEvent\\>";
		Pattern pattern =Pattern.compile(xmlParse);
		Matcher matcher = pattern.matcher(content);
		boolean rs = matcher.find();  
		if (rs) { 
			eventData =matcher.group(0);
		    System.out.println("----------------------Thread-"+name+" Data:");
			System.out.println(eventData);
		}
		return eventData;
	}

	private void InputToFile(String event,int id) {
		System.out.println("-----------------Thread-"+name+"-"+id+" Input to file:");
		try {
			if (output!=null) {
			    output.write(event);
			    output.write("\n");
			    output.flush();
			}else {
				System.out.println("BufferedWriter is null");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Thread-"+name+"-"+id+":file write done");
	}

	private void printCrawlUrlInfo(String url,int id) {
		// TODO Auto-generated method stub
		System.out.println("--------------Thread-"+name+"-"+id+" URL:");
		System.out.println(url);
	}
	
	public String getContent(String crawlUrl,int id){
		URL url;
		int responsecode;
		HttpURLConnection urlConnectionl;
		BufferedReader reader;
		StringBuffer stringBuffer = null;
		String line;
		try {
			url = new URL(crawlUrl);
			urlConnectionl =(HttpURLConnection) url.openConnection();
			responsecode=urlConnectionl.getResponseCode();
			if (responsecode==200) {
				System.out.println("Thread-"+name+"-"+id+": 200 OK");
				reader = new BufferedReader(new InputStreamReader(urlConnectionl.getInputStream(),"UTF-8"));
				stringBuffer = new StringBuffer();
				while ((line=reader.readLine())!=null) {
				    stringBuffer.append(line);	
				}
			}
			else {
				System.out.println("Can't grab web content,Server Response Code:"+responsecode);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return  stringBuffer.toString();
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		if (args[0]!=null) {
			ArrayList<String> urlList = Constants.getURLs();
			File file = new File(Constants.FILE_PATH);
			if (file.exists()) {
				if(file.delete()){
					file=new File(Constants.FILE_PATH);
				}
			}
			BufferedWriter output = null;
			try {
				output = new BufferedWriter(new FileWriter(file,true));
				output.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				output.write("\n");
				output.write("<Root>");
				output.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			int total =Integer.parseInt(args[0]);
			if (total>0) {
	        	int size =urlList.size();
	        	int num = total/size;
	        	int last=total%size;
	        	for (int i = 0; i < size; i++) {
	        		if (output!=null) {
	        			Thread crawlThread;
	        			if (i<last) {
	        				crawlThread=new Crawl(urlList.get(i),output,num+1,"Grab"+(i+1));
						}else {
							crawlThread=new Crawl(urlList.get(i),output,num,"Grab"+(i+1));
						}
	        			crawlThread.start();
					}
				}
			}
			else {
				if (args[1]!=null) {
					int time=Integer.parseInt(args[1]);
					long startTime= System.currentTimeMillis();
					while (System.currentTimeMillis()-startTime<time) {
						for (int i = 0; i < urlList.size(); i++) {
							Thread crawlThread = new Crawl(urlList.get(i),output,"Grab"+i);
			        		crawlThread.start();
						}
					}   
				}
				else {
					System.out.println("Please input parameter(run time) Before Application Run");
				}
			}
		}else {
			System.out.println("Please input parameter(event nums) Before Application Run");
		}
          
	}

}
