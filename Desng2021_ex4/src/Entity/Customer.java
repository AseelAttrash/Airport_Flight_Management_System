package Entity;

import java.sql.Date;
import java.time.LocalDate;

public class Customer {
private String PassPortNum;
private String FirstName;
private String LastName;
private String Email;
private Date birthday;
private String PrimaryCitizinship;





public Customer(String passPortNum, String firstName, String lastName, String email, Date birthday,
		String primaryCitizinship) {
	super();
	PassPortNum = passPortNum;
	FirstName = firstName;
	LastName = lastName;
	Email = email;
	this.birthday = birthday;
	PrimaryCitizinship = primaryCitizinship;
}
public String getPassPortNum() {
	return PassPortNum;
}
public void setPassPortNum(String passPortNum) {
	PassPortNum = passPortNum;
}
public String getLastName() {
	return LastName;
}
public void setLastName(String lastName) {
	LastName = lastName;
}
public String getFirstName() {
	return FirstName;
}
public void setFirstName(String firstName) {
	FirstName = firstName;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getPrimaryCitizinship() {
	return PrimaryCitizinship;
}
public void setPrimaryCitizinship(String primaryCitizinship) {
	PrimaryCitizinship = primaryCitizinship;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
@Override
public String toString() {
	return "Customer [PassPortNum=" + PassPortNum + ", LastName=" + LastName + ", FirstName=" + FirstName + ", Email="
			+ Email + ", PrimaryCitizinship=" + PrimaryCitizinship + ", birthday=" + birthday + "]";
}


}
