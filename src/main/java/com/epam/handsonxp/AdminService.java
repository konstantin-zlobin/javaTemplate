package com.epam.handsonxp;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.epam.handsonxp.obj.ClubEvent;
import com.epam.handsonxp.obj.Ticket;
import com.epam.handsonxp.obj.TicketType;
import com.epam.handsonxp.obj.WrongClubEventException;
import com.epam.handsonxp.obj.WrongClubTicketException;

public class AdminService {
	private final List<ClubEvent> clubEvents;

	private final Set<Ticket> sellTickets = new HashSet<Ticket>();
	private final Map<ClubEvent, List<Ticket>> entryMapList = new HashMap<>();

	public AdminService() {
		clubEvents = new ArrayList<>();
	}

	public void addEvent(ClubEvent event) {
		if (event.title == null) {
			throw new WrongClubEventException("validation error: title must not be null!");
		}
		if (event.date == null) {
			throw new WrongClubEventException("validation error: date must not be null!");
		}
		Date todayDate = new Date();
		GregorianCalendar eventCalendar = new GregorianCalendar();
		GregorianCalendar todayCalendar = new GregorianCalendar();
		eventCalendar.setTime(event.date);
		todayCalendar.setTime(todayDate);
		todayCalendar.set(GregorianCalendar.HOUR_OF_DAY, eventCalendar.get(GregorianCalendar.HOUR_OF_DAY));
		todayCalendar.set(GregorianCalendar.MINUTE, eventCalendar.get(GregorianCalendar.MINUTE));
		if (eventCalendar.compareTo(todayCalendar) == 0) {
			throw new WrongClubEventException("validation error: today date is deprecated!");
		}

		if (event.artists == null || event.artists.isEmpty()) {
			throw new WrongClubEventException("validation error: artists shouldn't be null or empty!");
		}
		if (event.prices == null || event.prices.isEmpty()) {
			throw new WrongClubEventException("validation error: prices shouldn't be null or empty!");
		}
		clubEvents.add(event);
	}

	public List<ClubEvent> getAllEvents() {
		return clubEvents;
	}

	public void sellTicket(ClubEvent clubEvent, TicketType type, int num, String family) {
		Ticket ticket = new Ticket(clubEvent, type, num);
		for (Ticket currentTicket : sellTickets) {
			if (currentTicket.equals(ticket)) {
				ticket = currentTicket;
			}
		}
		
		if (ticket.ticketType == TicketType.ENTRY) {
			throw new WrongClubTicketException("validation error: entry");
		}
		if (sellTickets.contains(ticket) && ticket.sold) {
			throw new WrongClubTicketException("validation error: alrady buy");
		}
		if ((num > type.max) && (num <= 0)) {
			throw new WrongClubTicketException("validation error: this ticket number not exist");
		}
			
		ticket.sold = true;
		ticket.family = family;
		sellTickets.add(ticket);
	}

	public void sellTicket(ClubEvent clubEvent, TicketType type, String family) {
		Ticket ticket = new Ticket(clubEvent, type, 0);
		for (Ticket currentTicket : sellTickets) {
			if (currentTicket.equals(ticket)) {
				ticket = currentTicket;
			}
		}
		if (ticket.ticketType != TicketType.ENTRY) {
			throw new WrongClubTicketException("validation error: not Entry");

		}
		List<Ticket> l = entryMapList.get(clubEvent);
		if (l == null) {
			l = new ArrayList<>();
			entryMapList.put(clubEvent, l);
		}
		if (l.size() >= type.max) {
			throw new WrongClubTicketException("validation error: more than " + type.max);

		}
		ticket.sold = true;
		ticket.family = family;
		l.add(ticket);
	}
	
	public void bookTicket(ClubEvent clubEvent, TicketType type, int num, String family) {
		Ticket ticket = new Ticket(clubEvent, type, num);
		if (ticket.ticketType == TicketType.ENTRY) {
			throw new WrongClubTicketException("validation error: entry");
		}
		if (sellTickets.contains(ticket)) {
			throw new WrongClubTicketException("validation error: alrady buy");
		}
		if ((num > type.max) && (num <= 0)) {
			throw new WrongClubTicketException("validation error: this ticket number not exist");
		}
			
		ticket.family = family; 
		ticket.bookedDate = new Date();
		sellTickets.add(ticket);				
	}

	public void bookTicket(ClubEvent clubEvent, TicketType type, String family) {
		Ticket ticket = new Ticket(clubEvent, type, 0);
		if (ticket.ticketType != TicketType.ENTRY) {
			throw new WrongClubTicketException("validation error: not Entry");

		}
		List<Ticket> l = entryMapList.get(clubEvent);
		if (l == null) {
			l = new ArrayList<>();
			entryMapList.put(clubEvent, l);
		}
		if (l.size() >= type.max) {

			throw new WrongClubTicketException("validation error: more than " + type.max);

		}
		ticket.family = family; 
		ticket.bookedDate = new Date();
		l.add(ticket);
	}
}
