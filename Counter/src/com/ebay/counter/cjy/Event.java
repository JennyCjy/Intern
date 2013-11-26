package com.ebay.counter.cjy;

public class Event {
	private String Type;
	private String App;
	private String snev;
    private boolean rv;
    public Event(String type,String app){
    	this.Type=type;
    	this.App=app;
    }
    public Event(String type,String app,String snev){
    	this(type, app);
    	this.snev=snev;
    }
    
    public Event(String type,String app,boolean rv){
    	this(type, app);
    	this.rv=rv;
    }
    
    
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getApp() {
		return App;
	}
	public void setApp(String app) {
		App = app;
	}
	public String getSnev() {
		return snev;
	}
	public void setSnev(String snev) {
		this.snev = snev;
	}
	public boolean isRv() {
		return rv;
	}
	public void setRv(boolean rv) {
		this.rv = rv;
	}
}
