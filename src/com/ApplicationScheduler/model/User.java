package com.ApplicationScheduler.model;

import java.io.Serializable;

/**
 * Model: User: Handles name, password, timezone and role.
 * @author Saranya
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = -8779683036950344716L;
	
	private String name;
	private String password;
	private String timezone;
	private String role;
	
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getTimezone() {
			return timezone;
		}
		public void setTimezone(String timezone) {
			this.timezone = timezone;
		}
		
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		
}
