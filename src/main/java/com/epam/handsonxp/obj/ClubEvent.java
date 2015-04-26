package com.epam.handsonxp.obj;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ClubEvent {
	public String title;
	public Date date;	
	public Map<TicketType, Integer> prices;
	public List<String> artists;
}
