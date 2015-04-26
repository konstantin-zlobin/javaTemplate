package com.epam.handsonxp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClubConcertEvent extends ClubEvent {

	private final Calendar datetime = Calendar.getInstance();
	private final List<String> actors = new ArrayList<>();
	private final Map<Category, BigDecimal> prices = new HashMap<>();
	private final Map<Category, Places> places;
	private final BookExpireMap bookMap;

	public static enum Category {
		VIP, TABLE, STANDUP;
	}

	public ClubConcertEvent() {
		super();
		this.datetime.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
		this.datetime.set(Calendar.MILLISECOND, 0);
		this.bookMap = new BookExpireMap();
		
		places = new EnumMap<ClubConcertEvent.Category, Places>(Category.class);
		places.put(Category.VIP, new NumberedPlaces(10));
		places.put(Category.TABLE, new NumberedPlaces(25));
		places.put(Category.STANDUP, new UnnumberedPlaces(100));
	}

	/**
	 * Return calendar (date and time)
	 * 
	 * @return date and time of action
	 */
	public Calendar getDatetime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datetime.getTime());
		return calendar;
	}

	/**
	 * Sets date of action
	 *
	 * @param year
	 *            the value used to set the <code>YEAR</code> calendar field.
	 * @param month
	 *            the value used to set the <code>MONTH</code> calendar field. Month value is 0-based. e.g., 0 for
	 *            January.
	 * @param date
	 *            the value used to set the <code>DAY_OF_MONTH</code> calendar field.
	 */
	public void setDate(int year, int month, int date) {
		this.datetime.set(year, month, date);
	}

	/**
	 * Set time of action
	 * 
	 * @param hour
	 *            hour of action (0-23)
	 * @param minute
	 *            minute of action (0-59)
	 */
	public void setTime(int hour, int minute) {
		this.datetime.set(Calendar.HOUR, hour);
		this.datetime.set(Calendar.MINUTE, minute);
	}

	/**
	 * Get actors
	 * 
	 * @return unmodifiable list of actor's names
	 */
	public List<String> getActors() {
		return Collections.unmodifiableList(actors);
	}

	/**
	 * Add actor name to actor's list
	 * 
	 * @param actor
	 *            actor name
	 */
	public void addActor(String actor) {
		this.actors.add(actor);
	}

	/**
	 * Get price for concrete category
	 * 
	 * @param category
	 *            category of ticket
	 * @return price of ticket
	 */
	public BigDecimal getPrice(Category category) {
		return this.prices.get(category);
	}

	/**
	 * Set price for concrete category
	 * 
	 * @param category
	 *            category category of ticket
	 * @param price
	 *            price of ticket
	 */
	public void setPrice(Category category, BigDecimal price) {
		this.prices.put(category, price);
	}

	/**
	 * Set price for concrete category
	 * 
	 * @param category
	 *            category category of ticket
	 * @param price
	 *            price of ticket
	 */
	public void setPrice(Category category, int price) {
		setPrice(category, new BigDecimal(price));
	}
	
	public void sell(Category category, Integer number) {
		Places categoryPlaces = places.get(category);
		categoryPlaces.sell(number);
	}
	
	public void bookTicket(Ticket ticket) {
		ticket.setBookTime(new Date());
		String key = ticket.getCard().getCardNumber() + ticket.getBookTime().toString();
		bookMap.put(key, ticket);
	}

	public void buyTicket(String cardNum, String date) {
		Ticket ticket = (Ticket) bookMap.get(cardNum + date);
		
		ticket.setBookTime(new Date());
		String key = ticket.getCard().getCardNumber() + ticket.getBookTime().toString();
		bookMap.put(key, ticket);
	}

	
}
