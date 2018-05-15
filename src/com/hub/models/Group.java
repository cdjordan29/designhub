package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;

/**
 * Class Group represents the Group table in gold_team_DB.
 * Allows manipulation of each field. 
 * 
 * @author Daniel Jordan v. 1.0
 * @revision
 * @version 1.0
 */

public class Group {

	private long id; 
	private String name;
	private boolean active;
	
	public Group () {}
	
	/**
	 * Return gold_team_DB.group id value
	 * @return this.id
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * Setting gold_team_DB.group id value
	 * @param long id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Return gold_team_DB.group name value
	 * @return this.name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setting gold_team_DB.group name value
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return gold_team_DB.group is_active value
	 * @return this.active
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * Setting gold_team_DB.group is_active value
	 * @param boolean active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public static RowMapper<Group> getRowMapper() {
		return new RowMapper<Group>() {
			@Override
			public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
				Group group = new Group();
				group.setId(rs.getLong("id"));
				group.setName(rs.getString("name"));
				group.setActive(rs.getBoolean("is_active"));
				return group;
			}
		};
	}
}
