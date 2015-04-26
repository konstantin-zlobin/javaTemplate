package com.epam.handsonxp.obj;

public enum TicketType {
	
	VIP_TABLES(10),
	TABLES(25),
	ENTRY(100);
	
	public int max;

	private TicketType(int max) {
		this.max = max;
	}
	
	

}
