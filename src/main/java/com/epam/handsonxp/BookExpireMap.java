package com.epam.handsonxp;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class BookExpireMap extends HashMap {
	private HashMap<String, Date> expireDateMap = new HashMap<String, Date>();

	@Override
	public Object put(Object key, Object value) {
		if (expireDateMap.get(key) == null) {
			expireDateMap.put(key.toString(), new Date());
		} else {
			throw new IllegalStateException("Already booked");
		}
		return super.put(key, value);
	}

	@Override
	public Object get(Object key) {
		if (expireDateMap.get(key) != null) {
			Date now = new Date();
			Date sellDate = expireDateMap.get(key);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(GregorianCalendar.MINUTE, 30);
			if (now.after(calendar.getTime())) {
				expireDateMap.remove(key);
				throw new IllegalStateException("Your booking is expired");
			}
			return super.get(key);
		}
		throw new IllegalStateException("You have no book yet");
	}
}
