package com.epam.handsonxp;

public class CreditCard {
	private CreditCardType cardType;
	private long cardNumber;
	
	public CreditCard(CreditCardType cardType, long cardNumber) {
		this.cardType = cardType;
		this.cardNumber = this.cardNumber;
	}
	
	public CreditCardType getCardType() {
		return cardType;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	
	
}
