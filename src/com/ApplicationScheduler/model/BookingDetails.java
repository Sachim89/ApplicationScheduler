package com.ApplicationScheduler.model;

import java.io.Serializable;

/**
 * Model: Booking Details: Handles date and time.
 * @author Saranya
 *
 */
public class BookingDetails implements Serializable {

	private static final long serialVersionUID = -7191278986779747863L;

	private String date;
	private String time;

	public String getDate() {
		return date;
	}

	public void setDate(String dateNo) {
		this.date = dateNo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String timeNum) {
		this.time = timeNum;
	}

}