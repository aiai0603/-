package cn.edu.zucc.takeaway.model;

import java.util.Date;

public class BeanGive {
	private int user_no;
	private double youhui_sale;
	private Date endtime;
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public double getYouhui_sale() {
		return youhui_sale;
	}
	public void setYouhui_sale(double youhui_sale) {
		this.youhui_sale = youhui_sale;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	private String shop_name;
	private int shop_no;
	private int need;//需要几单
	private int already;//已经几单
	private int youhui_no;
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getShop_no() {
		return shop_no;
	}
	public void setShop_no(int shop_no) {
		this.shop_no = shop_no;
	}
	public int getNeed() {
		return need;
	}
	public void setNeed(int need) {
		this.need = need;
	}
	public int getAlready() {
		return already;
	}
	public void setAlready(int already) {
		this.already = already;
	}
	public int getYouhui_no() {
		return youhui_no;
	}
	public void setYouhui_no(int youhui_no) {
		this.youhui_no = youhui_no;
	}
	
	
	
	
}
