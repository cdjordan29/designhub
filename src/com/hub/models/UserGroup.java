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

public class UserGroup {

	private long id; 
	private long userId;
	private long groupId;
	private Date createDate;
	private boolean active;
	
	public UserGroup () {}
	
	/**
	 * Return gold_team_DB.user_group id value
	 * @return this.id
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * Setting gold_team_DB.user_group id value
	 * @param long id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Return gold_team_DB.user_group user_id value
	 * @return this.userId
	 */
	public long getUserId() {
		return this.userId;
	}

	/**
	 * Setting gold_team_DB.user_group user_id value
	 * @param long _user_id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Return gold_team_DB.user_group group_id value
	 * @return this.groupId
	 */
	public long getGroupId() {
		return this.groupId;
	}
	
	/**
	 * Setting gold_team_DB.user_group group_id value
	 * @param long gorupId
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * Return gold_team_DB.user_group create_date value
	 * @return this.createDate
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * Setting gold_team_DB.user_group create_date value
	 * @param Date createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Return gold_team_DB.user_group is_active value
	 * @return this.active
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * Setting gold_team_DB.user_group is_active value
	 * @param boolean active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public static RowMapper<UserGroup> getRowMapper() {
		return new RowMapper<UserGroup>() {
			@Override
			public UserGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserGroup userGroup = new UserGroup();
				userGroup.setId(rs.getLong("id"));
				userGroup.setUserId(rs.getLong("user_id"));
				userGroup.setGroupId(rs.getLong("group_id"));
				userGroup.setCreateDate(rs.getDate("create_date"));
				userGroup.setActive(rs.getBoolean("is_active"));
				return userGroup;
			}
		};
	}
}
