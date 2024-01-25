package Boundry;

public class ReportElemnts {
	private String passport;
	private String firstName;
	private String LastName;
	private String morning;
	private String noon;
	private String evening;
	private String night;
	
	
	public ReportElemnts(String passport, String firstName, String lastName, String morning, String noon,
			String evening, String night) {
		super();
		this.passport = passport;
		this.firstName = firstName;
		LastName = lastName;
		this.morning = morning;
		this.noon = noon;
		this.evening = evening;
		this.night = night;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getMorning() {
		return morning;
	}
	public void setMorning(String morning) {
		this.morning = morning;
	}
	public String getNoon() {
		return noon;
	}
	public void setNoon(String noon) {
		this.noon = noon;
	}
	public String getEvening() {
		return evening;
	}
	public void setEvening(String evening) {
		this.evening = evening;
	}
	public String getNight() {
		return night;
	}
	public void setNight(String night) {
		this.night = night;
	}
	
}
