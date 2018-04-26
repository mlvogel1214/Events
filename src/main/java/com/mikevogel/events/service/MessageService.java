package com.mikevogel.events.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mikevogel.events.models.Event;
import com.mikevogel.events.models.Message;
import com.mikevogel.events.models.User;
import com.mikevogel.events.repositories.MessageRepository;

@Service
public class MessageService {
	private MessageRepository messageRepository;
	
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public Message addMessage(Message message) {
		return messageRepository.save(message);
	}
	
	public void addEventToMessage(Event event, Message message) {
		message.setEvent(event);
		messageRepository.save(message);
	}
	public List<Message> getMessagesByEvent(Long id) {
		return messageRepository.getMessageByEvent(id);
	}
	public void addUserToMessage(Message message, User user) {
		List<Message> newUserMessage = user.getMessages();
		newUserMessage.add(message);
		user.setMessages(newUserMessage);
		messageRepository.save(message);
	}

}
