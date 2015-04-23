package com.epam.handsonxp;

import org.junit.*;

public class AdminServiceTest {
	
	@Test
	public void addEvent_positive() {
		final AdminService adminService = new AdminService();
		final ClubEvent clubEvent = new ClubEvent();
		clubEvent.title = "SuperMegaShow 12345";
		adminService.addEvent(clubEvent);
		Assert.assertEquals("SuperMegaShow 12345", adminService.getAllEvents().get(0).title);
	}
	
	@Test(expected=RuntimeException.class)	
	public void addEvent_validationError() {
		final AdminService adminService = new AdminService();
		final ClubEvent clubEvent = new ClubEvent();		
		adminService.addEvent(clubEvent);			
	}
}
