package cn.edu.zucc.takeaway.model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BeanOwnerCount {
	
	public static final  String[] tableTitles= {"优惠价格","截止日期","数量"};
	private int shop_no;
	private int youhui_no;
	private int user_no;
	private String shop_name;
	private Boolean together;
	public Boolean getTogether() {
		return together;
	}
	public void setTogether(Boolean together) {
		this.together = together;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	private double count_money;
	private int num;
	private Date end_date;
	public int getShop_no() {
		return shop_no;
	}
	public void setShop_no(int shop_no) {
		this.shop_no = shop_no;
	}
	public int getYouhui_no() {
		return youhui_no;
	}
	public void setYouhui_no(int youhui_no) {
		this.youhui_no = youhui_no;
	}
	public double getCount_money() {
		return count_money;
	}
	public void setCount_money(double count_money) {
		this.count_money = count_money;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	
	

	
	
	
	
	
	
	
	
	
	
}
