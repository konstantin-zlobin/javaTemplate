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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (free ? 1231 : 1237);
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((ticketCategory == null) ? 0 : ticketCategory.hashCode());
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
		Seat other = (Seat) obj;
		if (free != other.free)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (ticketCategory != other.ticketCategory)
			return false;
		return true;
	}
	
	
}
