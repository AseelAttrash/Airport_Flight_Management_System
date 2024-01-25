package Control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import Entity.Consts;
import Entity.Customer;
import Entity.Flight;
import Entity.FlightTicket;
import Entity.Seat;

public class CustomerOrderControl {
	private static CustomerOrderControl update_getInstance;

	public static CustomerOrderControl getInstace() {
		if (update_getInstance == null)
			update_getInstance = new CustomerOrderControl();
		return update_getInstance;
	}
	public ArrayList<FlightTicket> getFLightTickets(){
		ArrayList<FlightTicket> results = new ArrayList<FlightTicket>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_TICKETS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;


					results.add(new FlightTicket(rs.getString(i++) , rs.getString(i++), rs.getString(i++), 
							rs.getString(i++), rs.getString(i++), rs.getInt(i++)
						 ,rs.getString(i++),rs.getString(i++)));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
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
	
	public ArrayList<FlightTicket> handleFTickets(Customer c){
		ArrayList<FlightTicket>ft = new ArrayList<>();
		ArrayList<FlightTicket> temp = getFLightTickets();
		ArrayList<String>jsonS = new ArrayList<String>();
		Set<Flight> js = JsonController.getInstace().importJsonFile("json/flights.json").keySet();
		
		for(Flight f  : js) {
			jsonS.add(f.getFlightId());
		}
		
		for(int i = 0 ; i < temp.size() ; i++) {
			if(c.getPassPortNum().equals(temp.get(i).getCustomerNum())) {
				if(jsonS.contains(temp.get(i).getFlightId())) {
					ft.add(temp.get(i));
				}
			}
		}
		return ft;
		
	}
	
	
	
	public ArrayList<Customer> getCustomers(){
		ArrayList<Customer> results = new ArrayList<Customer>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_CUSTOMERS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
				results.add(new Customer(rs.getString(i++) , rs.getString(i++), rs.getString(i++), 
							rs.getString(i++), rs.getDate(i++), rs.getString(i++)
						 ));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}	
	
	public Customer getCusById(String passport) {
		ArrayList<Customer> cus = getCustomers();
		Customer c = null;
		for(int i = 0 ; i < cus.size() ; i++) {
			if(cus.get(i).getPassPortNum().equals(passport)) {
				c = cus.get(i);
				break;
			}
		}
		return c;
	}
	public ArrayList<FlightTicket> getFByCus(Customer c){
		ArrayList<FlightTicket> FT = handleFTickets(c);
		return FT;
		
		
		
	
		
	}
		
		public boolean updateFlightTicket(String flightTicketID , boolean c) {
			

			
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						CallableStatement stmt = conn.prepareCall(Consts.SQL_UpdaeTicket)) {
					int i = 1;

					stmt.setBoolean(i++, c);
					stmt.setString(i++, flightTicketID);
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
		
		public ArrayList<Flight> recFlights(FlightTicket ticket){
			String FlightId = ticket.getFlightId();
			Flight ticketTe = null;
			ArrayList<Flight> flights = getFLights();
			ArrayList<Flight>rec = new ArrayList<>();
			
			for(int i = 0 ; i  < flights.size() ; i++) {
				if(flights.get(i).getFlightId().equals(FlightId)) {
					ticketTe = flights.get(i);
					break;
				}
			}
			
			flights.remove(ticketTe);
			String landing = ticketTe.getLandingCity();
			String departureC = ticketTe.getDepartureCity();
			Date d  = ticketTe.getDepardate();
			 java.sql.Date date = new java.sql.Date(d.getTime());
			 LocalDate time  = date.toLocalDate();
			 

			 for(int i = 0 ; i < flights.size() ; i++) {
				 Flight temp  = flights.get(i);
				 if(temp.getDepartureCity()==null || temp.getLandingCity()==null || temp.getDepardate()==null) {
					 return null;
				 }
				 String lan = temp.getLandingCity();
				 String dep =  temp.getDepartureCity();
				 Date da = temp.getDepardate();
				 java.sql.Date date2 = new java.sql.Date(da.getTime());
				 LocalDate time2  = date2.toLocalDate();
				 
					long day14 = ChronoUnit.DAYS.between(time2, time);
					if(day14 < 14) {
						if(landing.equals(lan) && departureC.equals(dep)) {
							rec.add(temp);
						}
					}
			 }
		
			return rec;
		}
		
		
	}

