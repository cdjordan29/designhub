package com.hub.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.Group;
import com.hub.models.Message;
import com.hub.models.User;

@Repository
public class MessageDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	//Generates ID, don't use it.
	public boolean addMessage(Message m){
		String SQL = "INSERT INTO message (sender_id, recipient_id, content, date_sent, is_group) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, m.getSenderId(), m.getRecipientId(), m.getContent(), m.getDateSent(), m.isGroup());
		
		return rowsAffected == 1;
	}
	
	public Message getMessageById(long id){
		List<Message> results = new ArrayList<Message>();
		String SQL = "SELECT * FROM message WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Message.getRowMapper(), id);	
		return results.get(0);
	}
	
	public List<Message> getAllMessages(){
		List<Message> results = new ArrayList<Message>();
		String SQL = "SELECT * FROM message";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Message.getRowMapper());
		return results;
	}
	
	public List<Message> getAllMessagesForConversation(User one, User two){
		List<Message> results = new ArrayList<Message>();
		String SQL = "SELECT * FROM message WHERE (sender_id = ? AND recipient_id = ?) OR (sender_id = ? AND recipient_id = ?) ORDER BY date_sent DESC";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Message.getRowMapper(), one.getId(), two.getId(), two.getId(), one.getId());
		return results;
	}
	
	public List<Message> getAllMessagesForGroup(Group g){
		List<Message> results = new ArrayList<Message>();
		String SQL = "SELECT * FROM message WHERE recipient_id = ? ORDER BY date_sent DESC";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Message.getRowMapper(), g.getId());
		return results;
	}
	
	public List<Message> getNext10MessagesFromDateForConversation(User one, User two, Date date){
		List<Message> results = new ArrayList<Message>();
		String SQL = "SELECT * FROM message "
				+ "WHERE ((sender_id = ? AND recipient_id = ?) OR (sender_id = ? AND recipient_id = ?)) "
				+ "AND date_sent < ? "
				+ "ORDER BY date_sent DESC "
				+ "LIMIT 10";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Message.getRowMapper(), one.getId(), two.getId(), two.getId(), one.getId(), date);
		return results;
	}
	
	public List<Message> getNext10MessagesFromDateForGroup(Group g, Date date){
		List<Message> results = new ArrayList<Message>();
		String SQL = "SELECT * FROM message "
				+ "WHERE recipient_id = ? AND date_sent < ? "
				+ "ORDER BY date_sent DESC "
				+ "LIMIT 10";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Message.getRowMapper(), g.getId(), date);
		return results;
	}
	
	public boolean updateMessage(Message m){
		String SQL = "UPDATE message SET sender_id = ?, recipient_id = ?, content = ?, date_sent = ?, is_group = ? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, m.getSenderId(), m.getRecipientId(), m.getContent(), m.getDateSent(), m.isGroup(), m.getId());
		return rowsAffected == 1;
	}
	
	public boolean deleteMessage(Message m){
		String SQL = "DELETE FROM message WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, m.getId());
		return rowsAffected == 1;
	}
}
