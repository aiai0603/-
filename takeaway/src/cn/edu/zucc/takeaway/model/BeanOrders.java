package cn.edu.zucc.takeaway.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanOrders {
	public static final  String[] tableTitles= {"用户名","商家名","配送地址","价格","需要送达时间","状态"};
	
	private int order_no;
	public int getAddress_no() {
		return address_no;
	}
	public void setAddress_no(int address_no) {
		this.address_no = address_no;
	}
	private int address_no;
	private int shop_no;
	private int kind_no;
	private int rider_no;
	private int count_no;
	private int youhui_no;
	private double money;
	private double true_money;
	private Date order_time;
	private Date arrive;
	private int site;
	private int user_no;
	private String shop_name;
	private String user_name;
	private String address_name;

	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String string) {
		this.shop_name = string;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getShop_no() {
		return shop_no;
	}
	public void setShop_no(int shop_no) {
		this.shop_no = shop_no;
	}
	public int getKind_no() {
		return kind_no;
	}
	public void setKind_no(int kind_no) {
		this.kind_no = kind_no;
	}
	public int getRider_no() {
		return rider_no;
	}
	public void setRider_no(int rider_no) {
		this.rider_no = rider_no;
	}
	public int getCount_no() {
		return count_no;
	}
	public void setCount_no(int count_no) {
		this.count_no = count_no;
	}
	public int getYouhui_no() {
		return youhui_no;
	}
	public void setYouhui_no(int youhui_no) {
		this.youhui_no = youhui_no;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getTrue_money() {
		return true_money;
	}
	public void setTrue_money(double true_money) {
		this.true_money = true_money;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public Date getArrive() {
		return arrive;
	}
	public void setArrive(Date arrive) {
		this.arrive = arrive;
	}
	public int getSite() {
		return site;
	}
	public void setSite(int site) {
		this.site = site;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	
	public String getCell(int col){
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(col==0) return this.getUser_name();
		else if(col==1) return this.getShop_name();
		else if(col==2) return this.getAddress_name();
		else if(col==4) return  f.format(this.getArrive());
		else if(col==3) return  Double.toString(this.getTrue_money());
		else if(col==5) {
			
			if(this.getSite()==1)
				return "等待接单";
			else if(this.getSite()==2)
				return "正在配送";
			else if(this.getSite()==3)
				return "超时未接单";
			else if(this.getSite()==4)
				return "配送超时";
			else if(this.getSite()==0)
				return "用户取消";
		}
		return "";
	}
	
	
	
	
}
