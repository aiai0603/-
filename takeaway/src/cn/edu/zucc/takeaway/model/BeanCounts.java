package cn.edu.zucc.takeaway.model;

public class BeanCounts {
	private int count_no;
	private int shop_no;
	private double ac_money;//�������
	private double count_sale;//�Żݼ۸�
	private boolean together;
	
	public int getCount_no() {
		return count_no;
	}
	public void setCount_no(int count_no) {
		this.count_no = count_no;
	}
	public int getShop_no() {
		return shop_no;
	}
	public void setShop_no(int shop_no) {
		this.shop_no = shop_no;
	}
	public double getAc_money() {
		return ac_money;
	}
	public void setAc_money(double ac_money) {
		this.ac_money = ac_money;
	}
	public double getCount_sale() {
		return count_sale;
	}
	public void setCount_sale(double count_sale) {
		this.count_sale = count_sale;
	}
	public boolean isTogether() {
		return together;
	}
	public void setTogether(boolean together) {
		this.together = together;
	}
	
	
	
}
