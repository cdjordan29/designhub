package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Class User represents the Users table in gold_team_DB.
 * Allows manipulation of each field. 
 * 
 * @author Ben Marshall version 1.0
 * @revision version 1.0 by Daniel Jordan on 2/10/2018; added Coding Standards
 * @version 1.1
 */

public class User implements UserDetails{

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date createDate;
	private boolean active;
	private String permission;
	
	public User() {}
	
	/**
	 * Return gold_team_DB.users id value
	 * @return this.id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Setting gold_team_DB.users id value
	 * @param long id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Return gold_team_DB.users first_name value
	 * @return this.firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Setting gold_team_DB.users first_name value
	 * @param String firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Return gold_team_DB.users last_name value
	 * @return this.lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Setting gold_team_DB.users last_name value
	 * @param String lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Return gold_team_DB.users email value
	 * @return this.email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Setting gold_team_DB.users email value
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Return gold_team_DB.users password value
	 * @return this.password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Setting gold_team_DB.users password value
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Return gold_team_DB.users create_date value
	 * @return this.createDate
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * Setting gold_team_DB.users create_date value
	 * @param Date createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Return gold_team_DB.users is_active value
	 * @return this.active
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * Setting gold_team_DB.users is_active value
	 * @param boolean active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Return gold_team_DB.users permission value
	 * @return this.permission
	 */
	public String getPermission() {
		return this.permission;
	}

	/**
	 * Setting gold_team_DB.users permission value
	 * @param String permission
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	public static RowMapper<User> getRowMapper() {
		return new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setCreateDate(rs.getDate("create_date"));
				user.setActive(rs.getBoolean("is_active"));
				user.setPermission(rs.getString("permission"));
				return user;
			}
		};
	}

	//UserDetails implementations
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority("ROLE_" + permission));

        return list;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
