package com.epam.handsonxp;

import java.util.Date;

public class Ticket {
	private String fio;
	private CreditCard card;
	private Seat seat;
	private Date bookTime;
	private boolean sold;
		
	public Ticket(String fio, CreditCard card, Seat seat) {
		this.fio = fio;
		this.card = card;
		this.seat = seat;
	}
	
	
			
	public boolean isSold() {
		return sold;
	}



	public void setSold(boolean sold) {
		this.sold = sold;
	}



	public Date getBookTime() {
		return bookTime;
	}
	
	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}


	public String getFio() {
		return fio;
	}
	public CreditCard getCard() {
		return card;
	}
	public Seat getSeat() {
		return seat;
	}
	
	
}
