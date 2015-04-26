package com.epam.handsonxp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

public class ClubEvent {
	private final String title;
	private final List<String> artists;
	private final Date date;
	private final Map<TicketCategory, Double> prices;
	final ArrayList<Seat> seats;

	private final Map<Seat, BookAttribute> booksList;

	public ClubEvent(String title, List<String> artists, Date date, Map<TicketCategory, Double> prices) {
		validateEmptyObjects(title, artists, date, prices);
		validateDate(date);

		this.title = title;
		this.artists = artists;
		this.date = date;
		this.prices = prices;
		this.seats = new ArrayList<Seat>();
		this.booksList = new HashMap<Seat, BookAttribute>();
	}

	private void validateDate(Date date) {
		LocalDate now = LocalDate.now();
		LocalDate localDate = new LocalDate(date);
		if (now.isEqual(localDate))
			throw new IllegalArgumentException("Date is now");
		if (now.isAfter(localDate))
			throw new IllegalArgumentException("Date is in past");
	}

	private void validateEmptyObjects(String title, List<String> artists, Date date, Map<TicketCategory, Double> prices) {
		if (title == null || title.isEmpty())
			throw new IllegalArgumentException("title is empty");
		if (artists == null || artists.isEmpty())
			throw new IllegalArgumentException("artists is empty");
		if (date == null)
			throw new IllegalArgumentException("date is empty");
		if (prices == null || prices.isEmpty())
			throw new IllegalArgumentException("prices is empty");
	}

	public String getTitle() {
		return title;
	}

	public List<String> getArtists() {
		return artists;
	}

	public Date getDate() {
		return date;
	}

	public Map<TicketCategory, Double> getPrices() {
		return prices;
	}

	public boolean getAllFreeSeats(TicketCategory category) {
		return getAllFreeSeats(category);
	}

	public boolean buySeat(TicketCategory category, Integer number) {
		if (number > category.getSeatsCount()) {
			throw new IllegalArgumentException("Seat number wrong!");
		}
		for (Seat seat : seats) {
			if (seat.getNumber().equals(number)) {
				throw new IllegalArgumentException("Seat is busy!");
			}
		}
		return seats.add(new Seat(number, category));

	}

	private List<Seat> getFreeSeatsForCategory(TicketCategory category) {
		List<Seat> freeSeats = new ArrayList<Seat>();
		int freePlaceCount = 0;
		for (Seat seat : seats) {
			if ((freePlaceCount <= seat.getTicketCategory().getSeatsCount()) && (category.equals(seat.getTicketCategory())) && (seat.isFree())) {

				freeSeats.add(seat);
				freePlaceCount++;
			}
		}
		return freeSeats;
	}

	public boolean buySeat(String string, TicketCategory category, int number) {
		Seat seat = new Seat(number, category);
		Date date = Calendar.getInstance().getTime();
		BookAttribute bookAttribute = new BookAttribute();
		bookAttribute.date = date;
		bookAttribute.name = string;

		BookAttribute b = booksList.get(seat);
		if (b == null || ((b.date.getTime() + 30 * 60 * 1000) > date.getTime())) {
			return false;
		}
		if (!b.name.equals(string)) {
			return false;
		}

		
		return buySeat(category, number);
	}

	public Boolean bookTicket(String string, TicketCategory category, int i) {
		Seat seat = new Seat(i, category);
		Date date = Calendar.getInstance().getTime();
		BookAttribute bookAttribute = new BookAttribute();
		bookAttribute.date = date;
		bookAttribute.name = string;

		BookAttribute b = booksList.get(seat);
		if (b == null || ((b.date.getTime() + 30 * 60 * 1000) < date.getTime())) {
			booksList.put(seat, bookAttribute);
			return true;
		}
		return false;
	}
}
