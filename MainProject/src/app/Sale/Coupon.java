package app.Sale;

import java.sql.Date;
import java.sql.Time;

public class Coupon {
    private Ticket ticket;
    private int couponID;
    private Date flightDepartureDate;
    private Time flightDepartureTime;
    private String departFrom;
    private String flightTo;

    public Coupon(int couponID, Date flightDepartureDate, Time flightDepartureTime, String departFrom, String flightTo) {
        this.couponID = couponID;
        this.flightDepartureDate = flightDepartureDate;
        this.flightDepartureTime = flightDepartureTime;
        this.departFrom = departFrom;
        this.flightTo = flightTo;
    }

    public Coupon(){}

    public Ticket getTicket() {
        return ticket;
    }

    public int getCouponID() {
        return couponID;
    }

    public Date getFlightDepartureDate() {
        return flightDepartureDate;
    }

    public Time getFlightDepartureTime() {
        return flightDepartureTime;
    }

    public String getDepartFrom() {
        return departFrom;
    }

    public String getFlightTo() {
        return flightTo;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setCouponID(int couponID) {
        this.couponID = couponID;
    }

    public void setFlightDepartureDate(Date flightDepartureDate) {
        this.flightDepartureDate = flightDepartureDate;
    }

    public void setFlightDepartureTime(Time flightDepartureTime) {
        this.flightDepartureTime = flightDepartureTime;
    }

    public void setDepartFrom(String departFrom) {
        this.departFrom = departFrom;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }
}
