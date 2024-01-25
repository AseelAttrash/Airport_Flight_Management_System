package Entity;

public class PremiumTicket extends FlightTicket{
	private String RequestText;
	private double MaxWeight;
	private double addtionalprice;
	private double WeightOfLagaugge;
	public PremiumTicket(String id, String orderId, String mealtype, String class1, String customerNum, int price,
			String flightId, String seatId, String requestText, double maxWeight, double addtionalprice,
			double weightOfLagaugge) {
		super(id, orderId, mealtype, class1, customerNum, price, flightId, seatId);
		RequestText = requestText;
		MaxWeight = maxWeight;
		this.addtionalprice = addtionalprice;
		WeightOfLagaugge = weightOfLagaugge;
	}
	public String getRequestText() {
		return RequestText;
	}
	public void setRequestText(String requestText) {
		RequestText = requestText;
	}
	public double getMaxWeight() {
		return MaxWeight;
	}
	public void setMaxWeight(double maxWeight) {
		MaxWeight = maxWeight;
	}
	public double getAddtionalprice() {
		return addtionalprice;
	}
	public void setAddtionalprice(double addtionalprice) {
		this.addtionalprice = addtionalprice;
	}
	public double getWeightOfLagaugge() {
		return WeightOfLagaugge;
	}
	public void setWeightOfLagaugge(double weightOfLagaugge) {
		WeightOfLagaugge = weightOfLagaugge;
	}
	@Override
	public String toString() {
		return "PremiumTicket [RequestText=" + RequestText + ", MaxWeight=" + MaxWeight + ", addtionalprice="
				+ addtionalprice + ", WeightOfLagaugge=" + WeightOfLagaugge + "]";
	}

}
