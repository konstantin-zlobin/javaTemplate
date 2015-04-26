package com.epam.handsonxp;


public class UnnumberedPlaces implements Places {
	boolean[] tickets;
	
	public UnnumberedPlaces(int ticketsNum) {
		tickets = new boolean[ticketsNum]; 
	}

	@Override
	public void sell(Integer number) {
		for(int i = 0; i < tickets.length; i++) {
			if(!tickets[i]) {
				tickets[i] = true;
				return;
			}
		}
		
		throw new RuntimeException("No free tickets");
	}

}
