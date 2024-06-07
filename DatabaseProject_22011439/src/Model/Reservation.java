package Model;

import java.sql.Date;
import java.sql.Time;

public class Reservation {
    private int reservationId;
    private String movieName;
    private Date screeningDate;
    private Time screeningTime;
    private String theaterName;
    private int screeningRound;
    private int seatNumber;
    private int price;

    // Getters and Setters

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(Date screeningDate) {
        this.screeningDate = screeningDate;
    }

    public Time getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(Time screeningTime) {
        this.screeningTime = screeningTime;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public int getScreeningRound() {
        return screeningRound;
    }

    public void setScreeningRound(int screeningRound) {
        this.screeningRound = screeningRound;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
