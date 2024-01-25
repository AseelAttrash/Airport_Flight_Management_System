package Entity;

public class FlightTicket {
	@Override
	public String toString() {
		return "FlightTicket [Id=" + Id + ", Mealtype=" + Mealtype + ", price=" + price + ", FlightId=" + FlightId
				+ ", SeatId=" + SeatId + ", iscanceled=" + iscanceled + "]";
	}
	protected String Id;
	protected String OrderId;
	protected String Mealtype;
	protected String ClassType;
	protected String CustomerNum;
	protected int price;
	protected String FlightId;
	protected String SeatId;
	protected boolean iscanceled;
	public FlightTicket(String id, String orderId, String mealtype, String class1, String customerNum, int price,
			String flightId, String seatId) {
		super();
		Id = id;
		OrderId = orderId;
		Mealtype = mealtype;
		ClassType = class1;
		CustomerNum = customerNum;
		this.price = price;
		FlightId = flightId;
		SeatId = seatId;
	}
	
	public FlightTicket(String id) {
		super();
		Id = id;
	}

	public FlightTicket(String id, String orderId, String mealtype, String classType, String customerNum, int price,
			String flightId, String seatId, boolean iscanceled) {
		super();
		Id = id;
		OrderId = orderId;
		Mealtype = mealtype;
		ClassType = classType;
		CustomerNum = customerNum;
		this.price = price;
		FlightId = flightId;
		SeatId = seatId;
		this.iscanceled = iscanceled;
	}

	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	public String getMealtype() {
		return Mealtype;
	}
	public void setMealtype(String mealtype) {
		Mealtype = mealtype;
	}
	public String getClassType() {
		return ClassType;
	}
	public void setClassType(String class1) {
		ClassType = class1;
	}
	public String getCustomerNum() {
		return CustomerNum;
	}
	public void setCustomerNum(String customerNum) {
		CustomerNum = customerNum;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getFlightId() {
		return FlightId;
	}
	public void setFlightId(String flightId) {
		FlightId = flightId;
	}
	public String getSeatId() {
		return SeatId;
	}
	public void setSeatId(String seatId) {
		SeatId = seatId;
	}

}
