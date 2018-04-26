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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mikevogel.events.models.Event;
import com.mikevogel.events.models.Message;
import com.mikevogel.events.models.User;
import com.mikevogel.events.service.EventService;
import com.mikevogel.events.service.MessageService;
import com.mikevogel.events.service.UserService;

@Controller
public class MessageController {
	private final EventService eventService;
	private final MessageService messageService;
	private final UserService userService;
	
	public MessageController(EventService eventService, MessageService messageService, UserService userService) {
		this.eventService = eventService;
		this.messageService = messageService;
		this.userService = userService;
	}
	
	@RequestMapping("/event/{id}")
	public String message(
			Model model,
			@PathVariable("id") Long id,
			@Valid @ModelAttribute("new") Message message,
			Principal principal
			) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
		Event event = eventService.findEventById(id);
		model.addAttribute("event", event);
		model.addAttribute("users", eventService.getAllUsers(event));
		model.addAttribute("user", user);
		model.addAttribute("messages", messageService.getMessagesByEvent(id));
		return "message.jsp";
	}
	
	@PostMapping("/message/new/{eventId}")
	public String addMessage(
			@Valid @ModelAttribute("new") Message message,
			BindingResult result,
			@PathVariable("eventId") Long eventId,
			Model model,
			RedirectAttributes flash,
			Principal principal
			) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errs", result.getAllErrors());
			return "redirect:/event/" + eventId;
		}
		else {
			String username = principal.getName();
	        User user = userService.findByUsername(username);
			Event event = eventService.findEventById(eventId);
			Message msg = messageService.addMessage(message);
			messageService.addEventToMessage(event, msg);
			messageService.addUserToMessage(msg, user);
			return "redirect:/event/"+ eventId;
			
		}
	}

}
