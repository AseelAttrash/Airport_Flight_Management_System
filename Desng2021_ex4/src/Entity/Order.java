package Entity;

import java.time.LocalDate;

public class Order {
	private String orderNum;
	private LocalDate OrderDate;
	private String PaymentMethod;
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public LocalDate getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		OrderDate = orderDate;
	}
	public String getPaymentMethod() {
		return PaymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		PaymentMethod = paymentMethod;
	}
	public Order(String orderNum, LocalDate orderDate, String paymentMethod) {
		super();
		this.orderNum = orderNum;
		OrderDate = orderDate;
		PaymentMethod = paymentMethod;
	}
	@Override
	public String toString() {
		return "Order [orderNum=" + orderNum + ", OrderDate=" + OrderDate + ", PaymentMethod=" + PaymentMethod + "]";
	}

	
}
