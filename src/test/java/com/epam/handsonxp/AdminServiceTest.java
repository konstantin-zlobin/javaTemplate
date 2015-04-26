package com.epam.handsonxp;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import org.junit.*;

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
		final ClubConcertEvent c = new ClubConcertEvent();
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
}
