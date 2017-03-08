package com.mmt.model.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class FlightBooking {
	@Id
	private String flightBookingId;
	private String userId;
	private String flightId;
	private Date flightBookingDate;
	private String flag;
	public FlightBooking(String flightBookingId, String userId, String flightId, Date flightbookingDate , String flag) {
		super();
		this.flightBookingId = flightBookingId;
		this.userId = userId;
		this.flightId = flightId;
		this.flightBookingDate = flightbookingDate;
		this.flag = flag;
	}
	public FlightBooking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFlightBookingId() {
		return flightBookingId;
	}
	public void setFlightBookingId(String flightBookingId) {
		this.flightBookingId = flightBookingId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public Date getFlightBookingDate() {
		return flightBookingDate;
	}
	public void setFlightBookingDate(Date flightBookingDate) {
		this.flightBookingDate = flightBookingDate;
	}
	public String isFlag() {
		return flag;
	}
	public void setFlag(String string) {
		this.flag = string;
	}
	@Override
	public String toString() {
		return "FlightBooking [flightBookingId=" + flightBookingId + ", userId=" + userId + ", flightId=" + flightId
				+ ", flightBookingDate=" + flightBookingDate + ", flag=" + flag + "]";
	}
	
	
}
