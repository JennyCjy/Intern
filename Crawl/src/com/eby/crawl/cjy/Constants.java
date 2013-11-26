package com.eby.crawl.cjy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Constants {
     public static final String FILE_PATH="data.xml";
     public static ArrayList<String> getURLs(){
    	 ArrayList<String > UrlList= new ArrayList<String>();
    	 File file = new File("data.txt");
    	 String line;
    	 if(!file.exists()){
    		 System.out.println("data.txt is not existed.please create");
    	 }
    	 else {
    		 try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while ((line=reader.readLine())!=null) {
					line=line+"/Event/Channel/InboundMessages?$format=xml";
					UrlList.add(line);
					System.out.println(line);
				}
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("data.txt is not existed.please create");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	 return UrlList;
    	 
     }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
