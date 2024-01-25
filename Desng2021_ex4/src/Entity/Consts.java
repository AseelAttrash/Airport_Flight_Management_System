package Entity;

import java.net.URLDecoder;

public class Consts {
private Consts() {
	throw new AssertionError();
}
protected static final String DB_FILEPATH = getDBPath();
public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";


public static final String SQL_GET_PLANES = "SELECT * FROM Plane";
public static final String SQL_GET_TICKETS = "SELECT * FROM FlightTicket";

public static final String SQL_GET_FLIGHTS = "SELECT * FROM Flight";
public static final String SQL_GET_SEATS = "SELECT * FROM Seat";
public static final String SQL_add_flights = "{call AddFlight(?,?,?,?,?,?,?,?) }";
public static final String SQL_updateFlight = "{call UpdateFlight(?,?,?,?,?,?,?,?,?) }";
public static final String SQL_AddSeat = "{call AddSeat(?,?,?,?,?)}";
public static final String SQL_UpdateSeat = "{call UpdateSeat(?,?)}";
public static final String SQL_report = "SELECT * FROM  REPORT";
public static final String SQL_GET_CUSTOMERS = "SELECT * FROM Customer";
public static final String SQL_UpdaeTicket = "{call UpdateTicket(?,?)}";




private static String getDBPath() {
	try {
		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decoded = URLDecoder.decode(path, "UTF-8");
		if (decoded.contains(".jar")) {
			decoded = decoded.substring(0, decoded.lastIndexOf('/'));
			return decoded + "/database/DatabaseCustomerFly.accdb";
		} else {
			decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
			return decoded + "src/Entity/DatabaseCustomerFly.accdb";
		}
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}

}
}
