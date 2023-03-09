package com.example.ap.model;

//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import lombok.Data;

import java.util.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;


@Document(collection="event")
public class Event {
	
	@Id
	private String id;
	
	private String name;
	
	//@OneToMany
	private Set<Session> sessions;
	
	public Event(){
		
	}
	
	public Event(String name){
		this.name=name;
		this.sessions=new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}
	
	public void addSession(Session session){
		this.sessions.add(session);
	}
	
	public void deleteSession(Session s){
		sessions.remove(s);
	}
	
	
}
