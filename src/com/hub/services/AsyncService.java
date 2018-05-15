package com.hub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hub.models.Message;

@Service
public class AsyncService {

	@Autowired
	MessageService messageService;
	
	@Async
	public void addMessage(Message m){
		messageService.addMessage(m);
	}
}
