package com.videorental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

	private static final String NAME = "Kanghyun";
	private static final String TITLE = "TITLE_NOT_IMPORTANT";

	private static final Movie REGULAR_MOVIE = new Movie(TITLE, Movie.REGULAR);
	private static final Movie NEW_RELEASE_MOVIE = new Movie(TITLE, Movie.NEW_RELEASE);
	private static final Movie CHILDRENS_MOVIE = new Movie(TITLE, Movie.CHILDRENS);

	private Customer customer;

	@BeforeEach
	public void setUp() {
		customer = new Customer(NAME);
	}

	private void rent(Movie movie, int daysRented) {
		customer.addRental(new Rental(movie, daysRented));
	}

	@Test
	public void returnNewCustomer() {
		assertThat(customer).isNotNull();
	}

	@Test
	public void statementForNoRental() {
		// assert
		assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n"
				+ "Amount owed is 0.0\n"
				+ "You earned 0 frequent renter pointers");
	}

	@Test
	public void statementForRegularMovieRentalForLessThan3Days() {
		// arrange
		rent(REGULAR_MOVIE, 2);
		// assert
		assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n"
				+ "\t2.0(" + TITLE + ")\n"
				+ "Amount owed is 2.0\n"
				+ "You earned 1 frequent renter pointers");
	}

	@Test
	public void statementForRegularMovieRentalForMoreThan2Days() {
		// arrange
		rent(REGULAR_MOVIE, 3);

		// assert
		assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n"
				+ "\t3.5(" + TITLE + ")\n"
				+ "Amount owed is 3.5\n"
				+ "You earned 1 frequent renter pointers");
	}

	@Test
	public void statementForNewReleaseMovie() {
		// arrange
		rent(NEW_RELEASE_MOVIE, 1);

		// assert
		assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n"
				+ "\t3.0(" + TITLE + ")\n"
				+ "Amount owed is 3.0\n"
				+ "You earned 1 frequent renter pointers");
	}

	@Test
	public void statementForChildrensMovieRentalMoreThan3Days() {
		// arrange
		rent(CHILDRENS_MOVIE, 4);

		// assert
		assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n"
				+ "\t3.0(" + TITLE + ")\n"
				+ "Amount owed is 3.0\n"
				+ "You earned 1 frequent renter pointers");
	}

	@Test
	public void statementForChildrensMovieRentalLessThan4Days() {
		// arrange
		rent(CHILDRENS_MOVIE, 3);

		// assert
		assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n"
				+ "\t1.5(" + TITLE + ")\n"
				+ "Amount owed is 1.5\n"
				+ "You earned 1 frequent renter pointers");
	}

	@Test
	public void statementForNewReleaseMovieRentalMoreThan1Day() {
		// arrange
		rent(NEW_RELEASE_MOVIE, 2);

		// assert
		assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n"
				+ "\t6.0(" + TITLE + ")\n"
				+ "Amount owed is 6.0\n"
				+ "You earned 2 frequent renter pointers");
	}

	@Test
	public void statementForFewMovieRental() {
		// arrange
		rent(REGULAR_MOVIE, 1);
		rent(NEW_RELEASE_MOVIE, 4);
		rent(CHILDRENS_MOVIE, 4);

		// assert
		assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n"
				+ "\t2.0(" + TITLE + ")\n"
				+ "\t12.0(" + TITLE + ")\n"
				+ "\t3.0(" + TITLE + ")\n"
				+ "Amount owed is 17.0\n"
				+ "You earned 4 frequent renter pointers");
	}

	@Test
	public void statementReflectsPriceCodeChangedViaSetter() {
		// arrange
		Movie movie = new Movie(TITLE, Movie.REGULAR);
		movie.setPriceCode(Movie.NEW_RELEASE);
		rent(movie, 1);

		// assert
		assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n"
				+ "\t3.0(" + TITLE + ")\n"
				+ "Amount owed is 3.0\n"
				+ "You earned 1 frequent renter pointers");
	}
}
