package com.mikevogel.events.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mikevogel.events.models.Event;
import com.mikevogel.events.models.User;
import com.mikevogel.events.service.EventService;
import com.mikevogel.events.service.UserService;

@Controller
public class EditController {
	private final EventService eventService;
	private final UserService userService;
	
	public EditController(EventService eventService, UserService userService) {
		this.eventService = eventService;
		this.userService = userService;
	}
	
	@RequestMapping("/edit/event/{id}")
	public String editView(
			Principal principal,
			Model model,
			@PathVariable("id") Long id,
			@ModelAttribute("new") Event event
			) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
		model.addAttribute("event", eventService.findEventById(id));
		model.addAttribute("currentUser", user);
		return "edit.jsp";
		
	}
	
	@PostMapping("/edit/event")
	public String editEvent(
			@Valid @ModelAttribute("new") Event event, 
			BindingResult result
			) {
		eventService.updateEvent(event);
		return "redirect:/dashboard";
	}

}
