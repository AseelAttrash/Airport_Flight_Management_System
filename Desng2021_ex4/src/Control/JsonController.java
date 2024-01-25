package Control;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import Entity.Consts;
import Entity.Flight;
import Entity.Seat;

public class JsonController {
	
	private static JsonController addF_getInstance;

	public static JsonController getInstace() {
		if (addF_getInstance == null)
			addF_getInstance = new JsonController();
		return addF_getInstance;
	}

	public HashMap<Flight,ArrayList<Seat>> importJsonFile(String path){
		HashMap<Flight,ArrayList<Seat>>FlightsAndSeats = new HashMap<>();
		try (FileReader reader = new FileReader(new File(path))){
			JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
			JsonArray flights = (JsonArray) doc.get("flights");
			Iterator<Object> it = flights.iterator();
			while(it.hasNext()) {
				
				JsonObject obj = (JsonObject) it.next();
				
				String flightId = (String)obj.get("flightID");
				String landdingCity = (String)obj.get("LandingCity");
				String departureCity = (String)obj.get("DepartureCity");
				String departureDate = (String)obj.get("DepartureDate");
				String flightstatus = (String)obj.get("FlightStatus");
				LocalDate depDate  = LocalDate.parse(departureDate);
				ZoneId defaultZoneId = ZoneId.systemDefault();
				Date depardate = Date.from(depDate.atStartOfDay(defaultZoneId).toInstant());
				
				String fT = (String)obj.get("FlightTime");
				
				int flightTime = Integer.parseInt(fT);
				
				String tn = (String)obj.get("PlaneTailNumber");
				
				
				String lm = (String) obj.get("DepartureHour");
				LocalTime hour = LocalTime.parse(lm);
				java.sql.Time departureDateHour = java.sql.Time.valueOf(hour);
				
				String isCanc = (String) obj.get("isCancelled");
				boolean isCanclled = Boolean.parseBoolean(isCanc);
				
				JsonArray arr = (JsonArray) obj.get("seats");
				ArrayList<Seat>seats = new ArrayList<>();
				Iterator<Object> it2 = arr.iterator();
				while(it2.hasNext()) {
					JsonObject obj2 = (JsonObject) it2.next();
				
					String seatID = (String)obj2.get("Id");
					
					String lineN = (String)obj2.get("LineNumber");
					int lineNum = Integer.parseInt(lineN);
					
					String seatLN = (String) obj2.get("LineNumberInRow");
					int seatNumberInRow = Integer.parseInt(seatLN);
					
					String tailN = (String) obj2.get("PlaneTailNumber");
					
					String seatClass = (String) obj2.get("seatClass");
					
					Seat seat = new Seat(seatID , tailN ,seatClass, lineNum , seatNumberInRow);

					seats.add(seat);
					
				}
				
				for(int i =0 ; i < seats.size() ; i++) {
					Seat  seat = seats.get(i);
					if(addSeat(seat) == false) {
						updateSeat(seat.getId(), seat.getClassType());
					}
				}

				Flight flight = new Flight(flightId, landdingCity, departureCity, depardate,flightTime, 
						flightstatus, tn,departureDateHour);
				flight.setCanceled(isCanclled);
				if(FlightsAndSeats.containsKey(flight) == false) {
					FlightsAndSeats.put(flight, seats);
				}
				if(addFlgiht(flight) == false) {
				updateFlight(flight.getFlightId(), flight.getLandingCity(), flight.getDepartureCity(), flight.getDepardate(),
							flight.getFlightTime(), flight.getFlightStatus(), flight.getTailnumber(), flight.getDepartureHour(), flight.isCanceled());
				
			}
			

			}
			return FlightsAndSeats;
			
		}catch (IOException | DeserializationException e) {
			e.printStackTrace();
		}
		return FlightsAndSeats;
	}
	public ArrayList<Flight> getFLights(){
		ArrayList<Flight> results = new ArrayList<Flight>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_FLIGHTS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					

					results.add(new Flight(rs.getString(i++) , rs.getString(i++), rs.getString(i++), 
							rs.getDate(i++), rs.getInt(i++), rs.getString(i++)
						 ,rs.getString(i++),rs.getTime(i++)));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
	public ArrayList<Seat> getSeats(){
		ArrayList<Seat> results = new ArrayList<Seat>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_SEATS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
				
					results.add(new Seat(rs.getString(i++) , rs.getString(i++), rs.getString(i++), rs.getInt(i++),rs.getInt(i++)));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean addSeat(Seat seat) {
		ArrayList<Seat>seats = getSeats();

		if(seats.contains(seat)) {
			return false;
		}

		String seatId = seat.getId();
		String seatClass = seat.getClassType();
		int lineNumber = seat.getLineNumber();
		int lineNumberInLine = seat.getLineNumberInRow();
		String tailNumber = seat.getPlanetailnumber();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_AddSeat)) {
				int i = 1;
				stmt.setString(i++, seatId);
				stmt.setString(i++, tailNumber);
				stmt.setString(i++, seatClass);
				stmt.setInt(i++, lineNumber);
				stmt.setInt(i++, lineNumberInLine);
				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;


	}
	
	public boolean updateSeat(String seatId, String seatClass) {
		ArrayList<Seat>seats = getSeats();
		
		Seat seat = new Seat(seatId);
		if(seats.contains(seat) == false)
			return false;
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UpdateSeat)) {
				int i = 1;

				stmt.setString(i++, seatClass);
				stmt.setString(i++, seatId);
				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	
	
	public boolean addFlgiht(Flight f) {
		ArrayList<Flight>flights = getFLights();

		if(flights.contains(f)) {
			return false;
		}

	
		
		String FlightID = f.getFlightId();
		String landingCity =f.getLandingCity();
		String departureCity= f.getDepartureCity();
		int flightTime = f.getFlightTime();
		String flightStatus=f.getFlightStatus();
		String tail =f.getTailnumber();
		Time departureHour =f.getDepartureHour();
		Date depardate = f.getDepardate();
		
		
		
	

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_add_flights)) {
				int i = 1;
		        stmt.setString(i++, FlightID);
				stmt.setString(i++, landingCity);
				stmt.setString(i++, departureCity);
				stmt.setDate(i++, new java.sql.Date(depardate.getTime()));
				stmt.setInt(i++, flightTime);
				stmt.setString(i++, flightStatus);
				stmt.setString(i++, tail);
				stmt.setTime(i++, departureHour);
				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;

	}
	


	public boolean updateFlight(String FlightId, String Landing,String DepartureCity,Date DepartureDate
			, Integer flightTime,String flightStatus, String tailnumber,  Time departureHour, boolean isCanceled) {
		ArrayList<Flight>flights = getFLights();
		
		Flight f = new Flight(FlightId);
		if(flights.contains(f) == false)
			return false;
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_updateFlight)) {
				int i = 1;
				
				if(Landing != null) {
				stmt.setString(i++, Landing);
				
				}else {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				if(DepartureCity != null) {
					stmt.setString(i++, DepartureCity);
					}else {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
				if(DepartureDate != null) {
					stmt.setDate(i++, new java.sql.Date(DepartureDate.getTime()));
					}else {
						stmt.setNull(i++, java.sql.Types.DATE);
					}
				if(flightTime != null) {
					stmt.setInt(i++, flightTime);
					}else {
						stmt.setNull(i++, java.sql.Types.INTEGER);
					}
				if(flightStatus != null) {
					stmt.setString(i++, flightStatus);
					}else {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
				if(tailnumber != null) {
					stmt.setString(i++, tailnumber);
					}else {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
				if(departureHour != null) {
					stmt.setTime(i++, departureHour);
					}else {
						stmt.setNull(i++, java.sql.Types.TIME);
					}
				stmt.setBoolean(i++, isCanceled);
				stmt.setString(i++, FlightId);
				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

}
