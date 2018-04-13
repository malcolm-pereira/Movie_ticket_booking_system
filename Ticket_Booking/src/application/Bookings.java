package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//import javafx.beans.property.StringProperty;

public class Bookings {

	private final StringProperty movie_name;
	private final StringProperty date;
	private final StringProperty no_of_seats;
	private final StringProperty venue;
	private final StringProperty category;
	public Bookings(String movie_name, String date, String no, String venue, String category) {
		super();
		this.movie_name =new SimpleStringProperty(movie_name);
		this.date = new SimpleStringProperty(date);
		this.no_of_seats = new SimpleStringProperty(no);
		this.venue = new SimpleStringProperty(venue);
		this.category = new SimpleStringProperty(category);
	}
	public String getMovie_name() {
		return movie_name.get();
	}
	public String getDate() {
		return date.get();
	}
	public String getNo_of_items() {
		return no_of_seats.get();
	}
	public String getVenue() {
		return venue.get();
	}
	public String getCategory() {
		return category.get();
	}

	public void setMovie_name(String value)
	{
		movie_name.set(value);
	}
	public void setDate(String value)
	{
		date.set(value);
	}
	public void setNo_of_seats(String value)
	{
		no_of_seats.set(value);
	}
	public void setVenus(String value)
	{
		venue.set(value);
	}
	public void setCategory(String value)
	{
		category.set(value);
	}
	public StringProperty movie_nameProperty()
	{
		return movie_name;
	}
	public StringProperty dateProperty()
	{
		return date;
	}
	public StringProperty no_of_seatsProperty()
	{
		return no_of_seats;
	}
	public StringProperty venueProperty()
	{
		return venue;
	}
	public StringProperty categoryProperty()
	{
		return category;
	}
}
