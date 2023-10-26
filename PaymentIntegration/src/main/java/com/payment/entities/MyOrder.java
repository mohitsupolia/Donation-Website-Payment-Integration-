package com.payment.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="paymentorder")
public class MyOrder
{
	public MyOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyOrder(long myOrderId, String orderId, String amount, String receipt, String status,
			String paymentId) {
		super();
		this.myOrderId = myOrderId;
		this.orderId = orderId;
		this.amount = amount;
		this.receipt = receipt;
		this.status = status;
		this.paymentId = paymentId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long myOrderId;
	private String orderId;
	private String amount;
	private String receipt;
	private String status;
	private String paymentId;
	public long getMyOrderId() {
		return myOrderId;
	}
	public void setMyOrderId(long myOrderId) {
		this.myOrderId = myOrderId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
}
