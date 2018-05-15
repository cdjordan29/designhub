package com.hub.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hub.models.Group;
import com.hub.models.Message;
import com.hub.models.User;
import com.hub.services.AsyncService;
import com.hub.services.MessageService;
import com.hub.services.UserService;

@Controller
public class ChatController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	AsyncService asyncService;
	
	@Autowired
	SessionRegistry sessionRegistry;
	
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload Message chatMessage) {
    	asyncService.addMessage(chatMessage);
    	messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
    
    @MessageMapping("/chat.sendMessageToUser")
    public void sendMessageToUser(@Payload Message chatMessage){
    	asyncService.addMessage(chatMessage);
    	String toSendTo = userService.findUserById(chatMessage.getRecipientId()).getEmail();
    	System.out.println(toSendTo);
    	messagingTemplate.convertAndSendToUser(toSendTo, "/queue/message", chatMessage);
    }
    
    //JSP page
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chatPage(ModelMap model){
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	User user = userService.findByUsername(email);
    	//Obscure password from client side
    	user.setPassword("");
    	ObjectMapper o = new ObjectMapper();
    	String userJson;
		try {
			userJson = o.writeValueAsString(user);
			model.addAttribute("user", userJson);
			model.addAttribute("activeUsers", getActiveUsersJson());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	
    	return "chat";
    }
    
    @RequestMapping(value = "/load10MessagesConversation/{uid1}/{uid2}/{date}", method = RequestMethod.GET)
    @ResponseBody
    public String load10MessagesConversation(@PathVariable("uid1") long user1,
    		@PathVariable("uid2") long user2,
    		@PathVariable("date") Date date){
    	
    	User u1 = new User();
    	u1.setId(user1);
    	User u2 = new User();
    	u2.setId(user2);
    	
    	List<Message> messages = messageService.getNext10MessagesFromDateForConversation(u1, u2, date);
    	for(Message m: messages){
    		m.setSender(userService.findUserById(m.getSenderId()));
    		m.getSender().setPassword("");
    		m.setRecipient(userService.findUserById(m.getRecipientId()));
    		m.getRecipient().setPassword("");
    	}
    	ObjectMapper o = new ObjectMapper();
    	
    	try {
			return o.writeValueAsString(messages);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "error";
		}
    }
    
    @RequestMapping(value = "/load10MessagesGroup/{gid}/{date}", method = RequestMethod.GET)
    @ResponseBody
    public String load10MessagesGroup(@PathVariable("gid") long groupId,
    		@PathVariable("date") Date date){
    	Group g = new Group();
    	g.setId(groupId);
    	
    	List<Message> messages = messageService.getNext10MessagesFromDateForGroup(g, date);
    	for(Message m: messages){
    		m.setSender(userService.findUserById(m.getSenderId()));
    		m.getSender().setPassword("");
    	}
    	ObjectMapper o = new ObjectMapper();
    	
    	try {
			return o.writeValueAsString(messages);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "error";
		}
    }
    
    @RequestMapping(value = "/getActiveUsers", method = RequestMethod.GET)
    @ResponseBody
    public String getActiveUsersJson() throws JsonProcessingException{
    	List<Object> users = sessionRegistry.getAllPrincipals().stream()
        .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
        .collect(Collectors.toList());
    	
    	ObjectMapper o = new ObjectMapper();
    	return o.writeValueAsString(users);
    }

}