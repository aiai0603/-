package cn.edu.zucc.takeaway.model;

import java.util.Date;

public class BeanRiderIncome {
	private int order_no;
	private int rider_no;
	private Date rider_income_date;
	private int rider_comment;
	private double income;
	
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getRider_no() {
		return rider_no;
	}
	public void setRider_no(int rider_no) {
		this.rider_no = rider_no;
	}
	public Date getRider_income_date() {
		return rider_income_date;
	}
	public void setRider_income_date(Date rider_income_date) {
		this.rider_income_date = rider_income_date;
	}
	public int getRider_comment() {
		return rider_comment;
	}
	public void setRider_comment(int rider_comment) {
		this.rider_comment = rider_comment;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	
	

	
	
	
	
	
	
	
	
	
}
