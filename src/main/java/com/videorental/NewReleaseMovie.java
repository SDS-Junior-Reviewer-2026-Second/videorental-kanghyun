package com.videorental;

public class NewReleaseMovie extends Movie {

	public NewReleaseMovie(String title) {
		super(title);
	}

	@Override
	double getChargeFor(int daysRented) {
		return daysRented * 3;
	}

	@Override
	int getFrequentRenterPointsFor(int daysRented) {
		if (daysRented > 1) {
			return 2;
		} else {
			return 1;
		}
	}
}
