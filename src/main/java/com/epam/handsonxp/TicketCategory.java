package com.epam.handsonxp;

public enum TicketCategory {
	VIP(10),
	Simple(25),
	Common(100);
	
	private int seatsCount;
	
	private TicketCategory(int seatsCount) {
		this.seatsCount = seatsCount;
	}
	
	public int getSeatsCount() {
		return seatsCount;
	}
	
}
