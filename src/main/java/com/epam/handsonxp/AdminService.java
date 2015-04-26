package com.epam.handsonxp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import sun.misc.GC;
import util.Utils;

import com.epam.handsonxp.obj.ClubEvent;

public class AdminService {
	private final List<ClubEvent> clubEvents;
	
	public AdminService() {
		clubEvents = new ArrayList<>();
	}
	
	public void addEvent(ClubEvent event) {
		if (event.title == null) {
			throw new RuntimeException("validation error: title must not be null!");
		}
		if (event.date == null) {
			throw new RuntimeException("validation error: date must not be null!");
		}
		Date todayDate = new Date();
		GregorianCalendar eventCalendar = new GregorianCalendar();
		GregorianCalendar todayCalendar = new GregorianCalendar();
		eventCalendar.setTime(event.date);
		todayCalendar.setTime(todayDate);
		todayCalendar.set(GregorianCalendar.HOUR_OF_DAY, eventCalendar.get(GregorianCalendar.HOUR_OF_DAY));
		todayCalendar.set(GregorianCalendar.MINUTE, eventCalendar.get(GregorianCalendar.MINUTE));
		if (eventCalendar.compareTo(todayCalendar) == 0) {
			throw new RuntimeException("validation error: today date is deprecated!");
		}
		
		if (event.artists == null || event.artists.isEmpty()) {
			throw new RuntimeException("validation error: artists shouldn't be null or empty!");
		}
		if (event.prices == null || event.prices.isEmpty()) {
			throw new RuntimeException("validation error: prices shouldn't be null or empty!");
		}
		clubEvents.add(event);
	}
	
	public List<ClubEvent> getAllEvents() {
		return clubEvents;
	}
	
	

	
}
