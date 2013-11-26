package com.ebay.counter.cjy;

public class Event {
	private String Type;
	private String App;
	private String snev;
    private String rv;
    public Event(){
    	Type=null;
    	App=null;
    	snev=null;
    	rv=null;
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
	public String getRv() {
		return rv;
	}
	public void setRv(String rv) {
		this.rv = rv;
	}
}
