package com.epam.handsonxp;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;

import com.epam.handsonxp.obj.ClubEvent;
import com.epam.handsonxp.obj.TicketType;

public class AdminServiceTest {	
	
	@Test
	public void addEvent_positive() {
		final AdminService adminService = new AdminService();
		final ClubEvent clubEvent = new ClubEvent();
		
		List<String> artists = new ArrayList<String>();
		artists.add("Shira");
		Map<TicketType, Integer> prices = new HashMap<>();
		prices.put(TicketType.VIP_TABLES, 20000);
		
		clubEvent.title = "SuperMegaShow 12345";
		GregorianCalendar eventDate = new GregorianCalendar();
		eventDate.setTime(new Date());
		eventDate.add(GregorianCalendar.DAY_OF_YEAR, 5);
		clubEvent.date = eventDate.getTime();
		clubEvent.artists = artists;
		clubEvent.prices = prices;
		
		
		adminService.addEvent(clubEvent);
		Assert.assertEquals("SuperMegaShow 12345", adminService.getAllEvents().get(0).title);
	}
	
	@Test(expected=RuntimeException.class)	
	public void addEvent_validationError() {
		final AdminService adminService = new AdminService();
		final ClubEvent clubEvent = new ClubEvent();		
		adminService.addEvent(clubEvent);			
	}
	
	@Test(expected=RuntimeException.class)
	public void clubEventDateIsCorrect_dateIsToday() {
		final AdminService adminService = new AdminService();
		final ClubEvent clubEvent = new ClubEvent();
		
		List<String> artists = new ArrayList<String>();
		artists.add("Shira");
		Map<TicketType, Integer> prices = new HashMap<>();
		prices.put(TicketType.VIP_TABLES, 20000);
		Date date = new Date();
		
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		clubEvent.title = "SuperMegaShow 12345";
		
		clubEvent.artists = artists;
		clubEvent.prices = prices;
		adminService.addEvent(clubEvent);
		
	}
	
}
