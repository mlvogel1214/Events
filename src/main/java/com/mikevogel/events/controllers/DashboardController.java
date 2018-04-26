package com.mikevogel.events.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mikevogel.events.models.Event;
import com.mikevogel.events.models.User;
import com.mikevogel.events.service.EventService;
import com.mikevogel.events.service.UserService;

@Controller
public class DashboardController {
	
	private final EventService eventService;
	private UserService userService;
	
	public DashboardController(EventService eventService, UserService userService) {
		this.eventService = eventService;
		this.userService = userService;
	}
	
	@PostMapping("/add/event/{id}")
	public String add(
			@Valid @ModelAttribute("new") Event event, 
			BindingResult result,
			@PathVariable("id") Long id,
			RedirectAttributes flash
			) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errs", result.getAllErrors());
			return "redirect:/dashboard";
		}
		else {
			User user = userService.findUserById(id);
			Event event1 = eventService.addEvent(event);
			eventService.addUserToEvent(user, event1);
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("/delete/event/{id}")
	public String delete(
			@PathVariable("id") Long id
			) {
		eventService.deleteEvent(id);
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/join/event/{id}/{user}")
	public String addEvent(
			@PathVariable("user") Long user,
			@PathVariable("id") Long id
			) {
		User user1 = userService.findUserById(user);
		Event event = eventService.findEventById(id);
		eventService.addUserToEvent(user1, event);
		return "redirect:/dashboard";
		
	}

}
