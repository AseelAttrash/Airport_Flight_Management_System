package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import Boundry.ReportElemnts;
import Entity.Consts;
import Entity.Flight;
import Entity.FlightTicket;

public class reportController {
	
	private static reportController report_getInstance;

	public static reportController getInstace() {
		if (report_getInstance == null)
			report_getInstance = new reportController();
		return report_getInstance;
	}

	
	
	public ArrayList<ReportElemnts> getReport(){
		ArrayList<FlightTicket> flightTickets =  getFLightTickets();
		ArrayList<Flight>jsonFLights = new ArrayList<>(JsonController.getInstace().importJsonFile("json/flights.json").keySet());
		ArrayList<String>jsonFlightsId = new ArrayList<>();
		ArrayList<String> customersRelevnt = new ArrayList<>();

		ArrayList<ReportElemnts> results = new ArrayList<ReportElemnts>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_report);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					

					results.add(new ReportElemnts(rs.getString(i++) , rs.getString(i++), rs.getString(i++), 
							rs.getString(i++) , rs.getString(i++), rs.getString(i++),rs.getString(i++)));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		for(int i = 0 ; i < jsonFLights.size() ; i++) {
			String id = jsonFLights.get(i).getFlightId();
			jsonFlightsId.add(id);
		}
		for(int i = 0 ; i < flightTickets.size() ; i++) {
			FlightTicket ticket = flightTickets.get(i);
			String flightId = ticket.getFlightId();
			
			if(jsonFlightsId.contains(flightId)) {
				customersRelevnt.add(ticket.getCustomerNum());
			}
			
		}
		ArrayList<ReportElemnts>toRemove = new ArrayList<>();
		for(int i = 0 ; i < results.size() ; i++) {
			ReportElemnts temp = results.get(i);
			String pass = temp.getPassport();
			if(customersRelevnt.contains(pass) == false) {
				toRemove.add(results.get(i));
			}

		}
		results.removeAll(toRemove);
		return results;
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
	
	
	
}
