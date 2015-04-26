package com.epam.handsonxp;

import java.util.ArrayList;
import java.util.List;

import com.epam.handsonxp.ClubConcertEvent.Category;

public class AdminService {
	private final List<ClubEvent> clubEvents;
	
	public AdminService() {
		clubEvents = new ArrayList<>();
	}
	
	public void addEvent(ClubEvent event) {
		if (event.getTitle() == null) {
			throw new RuntimeException("validation error: title must not be null!");
		}
		clubEvents.add(event);
	}
	
	public List<ClubEvent> getAllEvents() {
		return clubEvents;
	}
	
	public void sellTicket(ClubConcertEvent event, Category category, Integer number) {
		event.sell(category, number);
	}
	
	public void sellTicket(ClubConcertEvent event, Category category) {
		sellTicket(event, category, null);
	}
}
