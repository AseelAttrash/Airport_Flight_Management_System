package Entity;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Flight {
	private String FlightId;
	private String LandingCity;
	private String DepartureCity;
	private int FlightTime;
	private String FlightStatus;
	private String tailnumber;
	private Time DepartureHour;
	private boolean isCanceled;
	private Date depardate;

	
	public Flight(String flightId) {
		super();
		FlightId = flightId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(FlightId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(FlightId, other.FlightId);
	}

	public Flight(String flightId, String landingCity, String departureCity, Date depardate, int flightTime,
			String flightStatus, String tailnumber,  Time departureHour) {
		super();
		FlightId = flightId;
		LandingCity = landingCity;
		DepartureCity = departureCity;
		this.depardate = depardate;
		FlightTime = flightTime;
		FlightStatus = flightStatus;
		this.tailnumber = tailnumber;
		DepartureHour = departureHour;
	}

	public boolean isCanceled() {
		return isCanceled;
	}

	public Date getDepardate() {
		return depardate;
	}

	public void setDepardate(Date depardate) {
		this.depardate = depardate;
	}

	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	public String getFlightId() {
		return FlightId;
	}
	public void setFlightId(String flightId) {
		FlightId = flightId;
	}
	public String getLandingCity() {
		return LandingCity;
	}
	public void setLandingCity(String landingCity) {
		LandingCity = landingCity;
	}
	public String getDepartureCity() {
		return DepartureCity;
	}
	public void setDepartureCity(String departureCity) {
		DepartureCity = departureCity;
	}
	public int getFlightTime() {
		return FlightTime;
	}
	public void setFlightTime(int flightTime) {
		FlightTime = flightTime;
	}
	public String getFlightStatus() {
		return FlightStatus;
	}
	public void setFlightStatus(String flightStatus) {
		FlightStatus = flightStatus;
	}
	public String getTailnumber() {
		return tailnumber;
	}
	public void setTailnumber(String tailnumber) {
		this.tailnumber = tailnumber;
	}
	public Time getDepartureHour() {
		return DepartureHour;
	}
	public void setDepartureHour(Time departureHour) {
		DepartureHour = departureHour;
	}

	@Override
	public String toString() {
		return "Flight [FlightId=" + FlightId + ", LandingCity=" + LandingCity + ", DepartureCity=" + DepartureCity
				+ "]";
	}
 
}
