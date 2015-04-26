package com.epam.handsonxp;

public class NumberedPlaces implements Places {
	boolean[] tickets;
	
	public NumberedPlaces(int ticketsNum) {
		tickets = new boolean[ticketsNum]; 
	}

	@Override
	public void sell(Integer number) {
		if(!tickets[number - 1]) {
			tickets[number - 1] = true;
		} else {
			throw new RuntimeException("Ticket is sold");
		}
	}
}
