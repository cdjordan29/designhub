package com.hub.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.MessageDAO;
import com.hub.models.Group;
import com.hub.models.Message;
import com.hub.models.User;

@Service
public class MessageService {

	@Autowired
	MessageDAO dao;
	
	public boolean addMessage(Message m){
		return dao.addMessage(m);
	}
	
	public Message getMessageById(int id){
		return dao.getMessageById(id);
	}
	
	public List<Message> getAllMessages(){
		return dao.getAllMessages();
	}
	
	public List<Message> getAllMessagesForConversation(User one, User two){
		return dao.getAllMessagesForConversation(one, two);
	}
	
	public List<Message> getAllMessagesForGroup(Group g){
		return dao.getAllMessagesForGroup(g);
	}
	
	public List<Message> getNext10MessagesFromDateForConversation(User one, User two, Date date){
		return dao.getNext10MessagesFromDateForConversation(one, two, date);
	}
	
	public List<Message> getNext10MessagesFromDateForGroup(Group g, Date date){
		return dao.getNext10MessagesFromDateForGroup(g, date);
	}
	
	public boolean updateMessage(Message m){
		return dao.updateMessage(m);
	}
	
	public boolean deleteMessage(Message m){
		return dao.deleteMessage(m);
	}
}
