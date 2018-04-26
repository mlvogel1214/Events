package com.mikevogel.events.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mikevogel.events.models.Event;
import com.mikevogel.events.models.User;
import com.mikevogel.events.repositories.EventRepository;

@Service
public class EventService {
    private EventRepository eventRepository;
    
    public EventService(EventRepository eventRepository) {
    	this.eventRepository = eventRepository;
    }
    
    public Event addEvent(Event event) {
    	return eventRepository.save(event);
    	
    }
    public void addUserToEvent(User user, Event event) {
    	List<Event> newEvent = user.getEvent();
    	newEvent.add(event);
    	user.setEvent(newEvent);
    	eventRepository.save(event);
    }
    public List<Event> getByState(String state){
    	return eventRepository.findByStateContaining(state);
    }
    public List<Event> getAllMinus(String state){
    	return eventRepository.findByStateExcluding(state);
    }
    
    public void deleteEvent(Long id) {
    	eventRepository.deleteById(id);
    }
    
    public Event findEventById(Long id) {
    	return eventRepository.findById(id).orElse(null);
    }
    
    public void updateEvent(Event event) {
    	eventRepository.save(event);
    }
    public List<User> getAllUsers(Event event){
    	return event.getUser();
    }

}
