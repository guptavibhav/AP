package com.example.ap.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ap.model.*;

import com.example.ap.repository.EventRepository;

@RestController
public class Controller {
	
	
	@Autowired
	private EventRepository eventRepository;
	
	
	@RequestMapping("/")
	public String redirect(HttpServletResponse response) throws IOException{
		return "Hello";
	}
	
	
	@GetMapping("/events")
	@ResponseBody
	public List<Event> getAllEvents(){
		return eventRepository.findAll();
	}
	
	@PostMapping("/events/{name}")
	@ResponseBody
	public ResponseEntity<Event> createEvent(@PathVariable("name") String name){
		Event u= eventRepository.findByName(name);
		if(u!=null)
			return new ResponseEntity("Event already registered", HttpStatus.BAD_REQUEST);
		
		Event event=new Event(name);
		eventRepository.save(event);
		return new ResponseEntity(event, HttpStatus.OK);
	}
	
	@PutMapping("/events/{eventName:[a-zA-Z -_0-9]+}")
	@ResponseBody
	public ResponseEntity<Event> createSession(@PathVariable("eventName") String eventName,
											
											@RequestParam("lang") String lang,
											@RequestParam("sessionname") String sessionname,
											@RequestParam("desc") String desc,
											@RequestParam("date") String date,
											@RequestParam("url") String url){
		Event u= eventRepository.findByName(eventName);
		if(u==null)
			return new ResponseEntity("Event doesn't exist", HttpStatus.NOT_FOUND);
		
		Event event=eventRepository.findByName(eventName);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date datenew=null;
		try {
			datenew = sdf.parse(date);
		} catch (ParseException e) {
			return new ResponseEntity("Date couldn't be parsed", HttpStatus.BAD_REQUEST);
		}
		
		Language lan=null;
		if(lang.equalsIgnoreCase("hindi"))
			lan=lan.Hindi;
		if(lang.equalsIgnoreCase("english"))
			lan=lan.English;
	
		for(Session s:event.getSessions()){
			if(s.getName().equals(sessionname)){
				return new ResponseEntity("Session name already registered", HttpStatus.BAD_REQUEST);
			}
		}
		
		Session session= new Session(sessionname,lan,desc,datenew,url);
		
		event.addSession(session);
		eventRepository.save(event);
		return new ResponseEntity(event, HttpStatus.OK);
	}
	
	@DeleteMapping("/events/{eventName:[a-zA-Z -_0-9]+}/{sessionname}")
	@ResponseBody
	public ResponseEntity<Event> deleteSession(@PathVariable("eventName") String name,
											@PathVariable("sessionname") String sessionname){
		Event u= eventRepository.findByName(name);
		if(u==null)
			return new ResponseEntity("Event doesn't exist", HttpStatus.NOT_FOUND);
		
		Event event=eventRepository.findByName(name);
		Set<Session> sessions=event.getSessions();
		
		Session result=null;
		for(Session s:sessions){
			if(s.getName().equals(sessionname)){
				result=s;
				break;
			}
		}
		
		if(result==null)
			return new ResponseEntity("Session doesn't exist", HttpStatus.NOT_FOUND);
		
		event.deleteSession(result);
		eventRepository.save(event);
		return new ResponseEntity(event, HttpStatus.OK);
	}

}
