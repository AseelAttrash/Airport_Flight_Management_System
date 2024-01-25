package Entity;

public class CustomerRelative {
private String PassPortnum1;
private String PassPortnum2;
public String getPassPortnum1() {
	return PassPortnum1;
}
public void setPassPortnum1(String passPortnum1) {
	PassPortnum1 = passPortnum1;
}
public String getPassPortnum2() {
	return PassPortnum2;
}
public void setPassPortnum2(String passPortnum2) {
	PassPortnum2 = passPortnum2;
}
public CustomerRelative(String passPortnum1, String passPortnum2) {
	super();
	PassPortnum1 = passPortnum1;
	PassPortnum2 = passPortnum2;
}


}
