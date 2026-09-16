package com.videorental;

public abstract class Movie {
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDRENS = 2;

	private String title;

	protected Movie(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	abstract double getChargeFor(int daysRented);

	abstract int getFrequentRenterPointsFor(int daysRented);
}
