package com.epam.handsonxp;

public class Seat {
	private Integer number;
	private TicketCategory ticketCategory;
	private boolean free;	

	public Seat(Integer number, TicketCategory ticketCategory) {
		super();
		this.number = number;
		this.ticketCategory = ticketCategory;
		this.free = true;
	}
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public TicketCategory getTicketCategory() {
		return ticketCategory;
	}
	public void setTicketCategory(TicketCategory ticketCategory) {
		this.ticketCategory = ticketCategory;
	}
	
	public void takeSeat() {
		this.free = false;
	}
	
	public boolean isFree() {
		return this.free;
	}
}
