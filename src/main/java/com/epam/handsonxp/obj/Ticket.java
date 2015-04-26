package com.epam.handsonxp.obj;

import java.util.Date;

public class Ticket {
	public ClubEvent ce;
	public TicketType ticketType;
	public int num;
	public boolean sold;
	public Date bookedDate;
	public String family;	
	
	
	public Ticket(ClubEvent ce, TicketType ticketType, int num) {
		super();
		this.ce = ce;
		this.ticketType = ticketType;
		this.num = num;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ce == null) ? 0 : ce.hashCode());
		result = prime * result + num;
		result = prime * result + ((ticketType == null) ? 0 : ticketType.hashCode());
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
		Ticket other = (Ticket) obj;
		if (ce == null) {
			if (other.ce != null)
				return false;
		} else if (!ce.equals(other.ce))
			return false;
		if (num != other.num)
			return false;
		if (ticketType != other.ticketType)
			return false;
		return true;
	}
	
	

}
