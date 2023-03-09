package com.example.ap.model;

import java.util.Date;

import javax.persistence.Id;


public class Session {
	
	@Id
	private String id;
	
	private String sessionname;
	private Language lang;
	private String desc;
	private Date date;
	private String url;
	
	
	public Session() {
		super();
	}
	public Session(String sessionname, Language lang, String desc, Date date, String url) {
		super();
		this.sessionname = sessionname;
		this.lang = lang;
		this.desc = desc;
		this.date = date;
		this.url = url;
	}
	public String getName() {
		return sessionname;
	}
	public void setName(String name) {
		this.sessionname = name;
	}
	public Language getLanguage() {
		return lang;
	}
	public void setLanguage(Language language) {
		this.lang = language;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
