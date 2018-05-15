package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;

/**
 * Class Message represents the Message table in gold_team_DB.
 * Allows manipulation of each field. 
 * 
 * @author Daniel Jordan v. 1.0
 * @revision
 * @version 1.0
 */

public class Message {

	private long id;
	private long senderId;
	private long recipientId;
	private String content;
	private Date dateSent;
	private boolean group;
	private User sender;
	private User recipient;
	private Group recipientGroup;
	
	public Message () {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(long recipientId) {
		this.recipientId = recipientId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public boolean isGroup() {
		return group;
	}

	public void setGroup(boolean group) {
		this.group = group;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
		this.senderId = sender.getId();
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
		this.setRecipientId(recipient.getId());
	}

	public Group getRecipientGroup() {
		return recipientGroup;
	}

	public void setRecipientGroup(Group recipientGroup) {
		this.recipientGroup = recipientGroup;
		this.setRecipientId(recipientGroup.getId());
	}

	public static RowMapper<Message> getRowMapper() {
		return new RowMapper<Message>() {
			@Override
			public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
				Message message = new Message();
				message.setId(rs.getLong("id"));
				message.setSenderId(rs.getLong("sender_id"));
				message.setRecipientId(rs.getLong("recipient_id"));
				message.setContent(rs.getString("content"));
				message.setDateSent(new Date(rs.getTimestamp("date_sent").getTime()));
				message.setGroup(rs.getBoolean("is_group"));
				return message;
			}
		};
	}
}
