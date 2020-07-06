package cn.edu.zucc.takeaway.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanYouHui {
	public static final  String[] tableTitles= {"优惠金额","需求订单数","开始时间","结束时间"};
	private int youhui_no;
	private int shop_no;
	private double youhui_sale;
	private int request;
	private Date startday;
	private Date endday;
	public int getYouhui_no() {
		return youhui_no;
	}
	public void setYouhui_no(int youhui_no) {
		this.youhui_no = youhui_no;
	}
	public int getShop_no() {
		return shop_no;
	}
	public void setShop_no(int shop_no) {
		this.shop_no = shop_no;
	}
	public double getYouhui_sale() {
		return youhui_sale;
	}
	public void setYouhui_sale(double youhui_sale) {
		this.youhui_sale = youhui_sale;
	}
	public int getRequest() {
		return request;
	}
	public void setRequest(int request) {
		this.request = request;
	}
	public Date getStartday() {
		return startday;
	}
	public void setStartday(Date startday) {
		this.startday = startday;
	}
	public Date getEndday() {
		return endday;
	}
	public void setEndday(Date endday) {
		this.endday = endday;
	}
	
public String getCell(int col){
		
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		if(col==0) return Double.toString(this.getYouhui_sale());
		else if(col==1) return Integer.toString(this.getRequest());
		else if(col==2) return f.format(this.getStartday());
		else if(col==3) return f.format(this.getEndday());
		else return "";
	}
	
	
	
	
}
