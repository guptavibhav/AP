package com.example.ap.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ap.model.Event;

import java.util.*;


@Repository
public interface EventRepository extends MongoRepository<Event,String>{
	
	public Event findByName(String name);
	
	public List<Event> findAll();
}
