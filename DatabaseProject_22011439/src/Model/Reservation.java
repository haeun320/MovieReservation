package Model;

import java.sql.Date;
import java.sql.Time;

public class Reservation {
    private int reservationId;
    private String movieName;
    private Date screeningDate;
    private String screeningDay;
    private Time screeningTime;
    private int screeningRound;
    private String theaterName;
    private int seatNumber;
    private int price;
    private String paymentMethod;
    private Boolean paymentStatus;
    private Date paymentDate;

    public Date getPaymentDate() {
    	return paymentDate;
    }
    
    public void setPaymentDate(Date paymentDate) {
    	this.paymentDate = paymentDate;
    }
    
    public Boolean getPaymentStatus() {
    	return paymentStatus;
    }
    
    public void setPaymentStatus(Boolean paymentStatus) {
    	this.paymentStatus = paymentStatus;
    }
    
    public String getPaymentMethod() {
    	return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
    	this.paymentMethod = paymentMethod;
    }
    
    public String getScreeningDay() {
    	return screeningDay;
    }
    
    public void setScreeningDay(String screeningDay) {
    	this.screeningDay = screeningDay;
    }
    
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
