package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Class Reminder_Frequency represents the Reminder_Frequency table in gold_team_DB.
 * Allows manipulation of each field. 
 * 
 * @author Daniel Jordan v. 1.0
 * @revision
 * @version 1.0
 */

public class ReminderFrequency {

	private long id;
	private String title;
	private int frequency;
	private boolean active;
	
	public ReminderFrequency () {}
	
	/**
	 * Return gold_team_DB.reminder_frequency id value
	 * @return this.id
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * Setting gold_team_DB.reminder_frequency id value
	 * @param long id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Return gold_team_DB.reminder_frequency title value
	 * @return this.title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Setting gold_team_DB.reminder_frequency title value
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Return gold_team_DB.reminder_frequency frequency value 
	 * @return this.frequency
	 */
	public int getFrequency() {
		return this.frequency;
	}

	/**
	 * Setting gold_team_DB.reminder_frequency frequency value
	 * @param int frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * Return gold_team_DB.reminder_frequency is_active value
	 * @return this.active
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * Setting gold_team_DB.reminder_frequency is_active value
	 * @param boolean active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public static RowMapper<ReminderFrequency> getRowMapper() {
		return new RowMapper<ReminderFrequency>() {
			@Override
			public ReminderFrequency mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReminderFrequency reminderFrequency = new ReminderFrequency();
				reminderFrequency.setId(rs.getLong("id"));
				reminderFrequency.setTitle(rs.getString("title"));
				reminderFrequency.setFrequency(rs.getInt("frequency"));
				reminderFrequency.setActive(rs.getBoolean("is_active"));
				return reminderFrequency;
			}
		};
	}
}
