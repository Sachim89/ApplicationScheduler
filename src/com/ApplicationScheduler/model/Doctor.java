package com.ApplicationScheduler.model;

import java.io.Serializable;

/**
 * Model: Doctor: Handles date and time for viewing.
 * @author Saranya
 *
 */
public class Doctor implements Serializable{

	private static final long serialVersionUID = -8779683036950344716L;
	
	private String date;
	private String time;
	
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
				
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}		
}
