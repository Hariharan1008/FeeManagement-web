package com.hari.model;

import java.sql.Date;

public class Tickets {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int ticketId;
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	private String myQuery;
	private Date createdOn;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMyQuery() {
		return myQuery;
	}
	public void setMyQuery(String ticket) {
		this.myQuery = ticket;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date date) {
		this.createdOn = date;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	private String ticketStatus;
	

}
