package com.ebay.counter.cjy;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {
	public static String readPath;
	public static String writePath;
	public static String eventType_one;
	public static String eventType_two;
	public static String eventType_three;
	public static String eventType_four;
	public static String snev;
	public static void getProperties() {
		InputStream in;
		Properties p = new Properties();
		try {
			in = new BufferedInputStream(new FileInputStream("setting.properties"));
			p.load(in);
			readPath=p.getProperty("file.readpath");
			writePath=p.getProperty("file.writepath");
			eventType_one=p.getProperty("SOJEvent");
			eventType_two=p.getProperty("SessionEndEvent");
			eventType_three=p.getProperty("SessionEndEvent");
			eventType_four=p.getProperty("SessionEndEvent");
			snev=p.getProperty("snev");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("setting initialized");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
