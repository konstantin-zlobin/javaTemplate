package com.epam.handsonxp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class AdminServiceTest {

	@Test
	public void addEvent_positive() {
		final AdminService adminService = new AdminService();
		Map<TicketCategory, Double> prices = new EnumMap<>(TicketCategory.class);
		prices.put(TicketCategory.Common, 10.0);

		Date eventDate = new DateTime(2015, 6, 5, 10, 30).toDate();
		final ClubEvent clubEvent = new ClubEvent("SuperMegaShow 12345",
				Arrays.asList("CoolArtist"), eventDate, prices);
		adminService.addEvent(clubEvent);

		Assert.assertEquals("SuperMegaShow 12345", adminService.getAllEvents()
				.get(0).getTitle());
		Assert.assertEquals(Arrays.asList("CoolArtist"), adminService
				.getAllEvents().get(0).getArtists());
		Assert.assertEquals(eventDate, adminService.getAllEvents().get(0)
				.getDate());
		Assert.assertEquals(prices, adminService.getAllEvents().get(0)
				.getPrices());
	}

	@Test(expected = RuntimeException.class)
	public void addEventTitleValidationError() {
		testAddEvent(null, Collections.<String> emptyList(), new Date(),
				Collections.<TicketCategory, Double> emptyMap());
	}

	private void testAddEvent(String title, List<String> artists, Date date,
			Map<TicketCategory, Double> prices) {
		final AdminService adminService = new AdminService();
		final ClubEvent clubEvent = new ClubEvent(title, artists, date, prices);
		adminService.addEvent(clubEvent);
	}

	@Test(expected = RuntimeException.class)
	public void addEventArtistsValidationError() {
		testAddEvent("", null, new Date(),
				Collections.<TicketCategory, Double> emptyMap());
	}

	@Test(expected = RuntimeException.class)
	public void addEventDateValidationError() {
		testAddEvent("", Collections.<String> emptyList(), null,
				Collections.<TicketCategory, Double> emptyMap());
	}

	@Test(expected = RuntimeException.class)
	public void addEventPricesValidationError() {
		testAddEvent("", Collections.<String> emptyList(), new Date(), null);
	}

	@Test(expected = RuntimeException.class)
	public void addEventEmptyObjectsValidationError() {
		testAddEvent("", Collections.<String> emptyList(), new Date(),
				Collections.<TicketCategory, Double> emptyMap());
	}

	@Test(expected = RuntimeException.class)
	public void addEventDateNowValidationError() {
		final AdminService adminService = new AdminService();
		Map<TicketCategory, Double> prices = new EnumMap<>(TicketCategory.class);
		prices.put(TicketCategory.Common, 10.0);

		Date now = new Date();
		final ClubEvent clubEvent = new ClubEvent("SuperMegaShow 12345",
				Arrays.asList("CoolArtist"), now, prices);
		adminService.addEvent(clubEvent);
	}

	@Test(expected = RuntimeException.class)
	public void addEventDateInPastValidationError() {
		final AdminService adminService = new AdminService();
		Map<TicketCategory, Double> prices = new EnumMap<>(TicketCategory.class);
		prices.put(TicketCategory.Common, 10.0);

		Date eventDate = new DateTime(2014, 6, 5, 10, 30).toDate();
		final ClubEvent clubEvent = new ClubEvent("SuperMegaShow 12345",
				Arrays.asList("CoolArtist"), eventDate, prices);
		adminService.addEvent(clubEvent);
	}
	
	@Test
    public void getAllFreeSeats() {
		ArrayList<Seat> seats = new ArrayList<Seat>();
		
		for (int i = 0; i < TicketCategory.VIP.getSeatsCount(); i++) {
			seats.add(new Seat(i, TicketCategory.VIP));
			if ((i % 2) == 0) {
				 seats.get(i).takeSeat();
			}
		}
		for (Seat seat : seats) {
			if (TicketCategory.VIP.equals(seat.getTicketCategory())) {
				if ((seat.getNumber() % 2) == 0) {
					Assert.assertEquals(false, seat.isFree());
					Assert.assertEquals(true, !seat.isFree());
				}
			}
		}
	}
	
	@Test(expected = RuntimeException.class)
	public void canBuyVipSeat() {
		final AdminService adminService = new AdminService();
		Map<TicketCategory, Double> prices = new EnumMap<>(TicketCategory.class);
		prices.put(TicketCategory.Common, 10.0);
		Date eventDate = new DateTime(2014, 6, 5, 10, 30).toDate();
		final ClubEvent clubEvent = new ClubEvent("SuperMegaShow 12345",
				Arrays.asList("CoolArtist"), eventDate, prices);
		adminService.addEvent(clubEvent);
		
		Assert.assertEquals(true, clubEvent.buySeat(TicketCategory.VIP, 1));		
		
	}
}
