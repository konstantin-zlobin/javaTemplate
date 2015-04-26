package com.epam.handsonxp;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.Map;

import org.junit.*;

import com.epam.handsonxp.ClubConcertEvent.Category;

public class AdminServiceTest {

	@Test
	public void addEvent_positive() {
		final AdminService adminService = new AdminService();
		final ClubEvent clubEvent = new ClubEvent();
		clubEvent.setTitle("SuperMegaShow 12345");
		adminService.addEvent(clubEvent);
		Assert.assertEquals("SuperMegaShow 12345", adminService.getAllEvents().get(0).getTitle());
	}

	@Test(expected = RuntimeException.class)
	public void addEvent_validationError() {
		final AdminService adminService = new AdminService();
		final ClubEvent clubEvent = new ClubEvent();
		adminService.addEvent(clubEvent);
	}

	@Test
	public void addConcertEvent_positive() {
		final AdminService adminService = new AdminService();
		final ClubConcertEvent c = new ClubConcertEvent(null);
		c.setTitle("SuperMegaShow 12345");
		c.setDate(2015, Calendar.JUNE, 15);
		c.setTime(19, 00);
		c.addActor("Пугачева");
		c.addActor("Киркоров");
		c.setPrice(ClubConcertEvent.Category.VIP, 100);
		c.setPrice(ClubConcertEvent.Category.TABLE, 70);
		c.setPrice(ClubConcertEvent.Category.STANDUP, 50);
		adminService.addEvent(c);
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.JUNE, 15, 19, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		String[] actors = { "Пугачева", "Киркоров" };
		Assert.assertEquals("SuperMegaShow 12345", ((ClubConcertEvent) adminService.getAllEvents().get(0)).getTitle());
		// Assert.assertEquals(calendar.getTime(), ((ClubConcertEvent)
		// adminService.getAllEvents().get(0)).getDatetime().getTime());
		Assert.assertEquals(Arrays.asList(actors), ((ClubConcertEvent) adminService.getAllEvents().get(0)).getActors());
		Assert.assertEquals(new BigDecimal(100), ((ClubConcertEvent) adminService.getAllEvents().get(0)).getPrice(ClubConcertEvent.Category.VIP));
		Assert.assertEquals(new BigDecimal(70), ((ClubConcertEvent) adminService.getAllEvents().get(0)).getPrice(ClubConcertEvent.Category.TABLE));
		Assert.assertEquals(new BigDecimal(50), ((ClubConcertEvent) adminService.getAllEvents().get(0)).getPrice(ClubConcertEvent.Category.STANDUP));

	}
	
	@Test
	public void sellTicket_positive() {
		final AdminService adminService = new AdminService();
		Places vipPlaces = new NumberedPlaces(3);
		Map<Category, Places> placesMap = new EnumMap<ClubConcertEvent.Category, Places>(Category.class);
		placesMap.put(Category.VIP, vipPlaces);
		ClubConcertEvent event = new ClubConcertEvent(placesMap);
		adminService.sellTicket(event, Category.VIP, 1);		
	}
	
	@Test
	public void sellTicketUnnumberedPlacesCountSuccess() {
		final AdminService adminService = new AdminService();
		Places standupPlaces = new UnnumberedPlaces(3);
		Map<Category, Places> placesMap = new EnumMap<ClubConcertEvent.Category, Places>(Category.class);
		placesMap.put(Category.STANDUP, standupPlaces);
		ClubConcertEvent event = new ClubConcertEvent(placesMap);
		adminService.sellTicket(event, Category.STANDUP);
		adminService.sellTicket(event, Category.STANDUP);
		adminService.sellTicket(event, Category.STANDUP);
	
	}
	
	@Test(expected = RuntimeException.class)
	public void sellTicketUnnumberedPlacesCountError() {
		final AdminService adminService = new AdminService();
		Places standupPlaces = new UnnumberedPlaces(3);
		Map<Category, Places> placesMap = new EnumMap<ClubConcertEvent.Category, Places>(Category.class);
		placesMap.put(Category.STANDUP, standupPlaces);
		ClubConcertEvent event = new ClubConcertEvent(placesMap);
		adminService.sellTicket(event, Category.STANDUP);
		adminService.sellTicket(event, Category.STANDUP);
		adminService.sellTicket(event, Category.STANDUP);
		adminService.sellTicket(event, Category.STANDUP);
	
	}
	
	@Test(expected = RuntimeException.class)
	public void sellTicketNumberedPlacesSameNumberError() {
		final AdminService adminService = new AdminService();
		Places standupPlaces = new NumberedPlaces(3);
		Map<Category, Places> placesMap = new EnumMap<ClubConcertEvent.Category, Places>(Category.class);
		placesMap.put(Category.VIP, standupPlaces);
		ClubConcertEvent event = new ClubConcertEvent(placesMap);
		adminService.sellTicket(event, Category.VIP, 1);
		adminService.sellTicket(event, Category.VIP, 1);
	}
}

