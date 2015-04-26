package com.epam.handsonxp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

public class ClubEvent {
	private final String title;
	private final List<String> artists;
	private final Date date;
	private final Map<TicketCategory, Double> prices;

	public ClubEvent(String title, List<String> artists, Date date, Map<TicketCategory, Double> prices) {
		validateEmptyObjects(title, artists, date, prices);
		validateDate(date);
		
		this.title = title;
		this.artists = artists;
		this.date = date;
		this.prices = prices;
	}

	private void validateDate(Date date) {
		LocalDate now = LocalDate.now();
		LocalDate localDate = new LocalDate(date);
		if(now.isEqual(localDate)) throw new IllegalArgumentException("Date is now");
		if(now.isAfter(localDate)) throw new IllegalArgumentException("Date is in past");
	}

	private void validateEmptyObjects(String title, List<String> artists, Date date, Map<TicketCategory, Double> prices) {
		if(title == null || title.isEmpty()) throw new IllegalArgumentException("title is empty");
		if(artists == null || artists.isEmpty()) throw new IllegalArgumentException("artists is empty");
		if(date == null) throw new IllegalArgumentException("date is empty");
		if(prices == null || prices.isEmpty()) throw new IllegalArgumentException("prices is empty");
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
}
