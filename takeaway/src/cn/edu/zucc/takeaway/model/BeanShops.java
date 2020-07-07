package cn.edu.zucc.takeaway.model;

import java.util.Date;

public class BeanShops {
	public static final  String[] tableTitles= {"商家名","星级"};
	public static final  String[] tableTitles2= {"商家名","星级","人均消费","销量"};
	private int shop_no;
	private String shop_name;
	private int level;
	private double avg_consume;
	private double sum_sale;
	public int getShop_no() {
		return shop_no;
	}
	public void setShop_no(int shop_no) {
		this.shop_no = shop_no;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public double getAvg_consume() {
		return avg_consume;
	}
	public void setAvg_consume(double avg_consume) {
		this.avg_consume = avg_consume;
	}
	public double getSum_sale() {
		return sum_sale;
	}
	public void setSum_sale(double sum_sale) {
		this.sum_sale = sum_sale;
	}
	

	public String getCell(int col){
		
		if(col==0) return this.getShop_name();
		else if(col==1) return Integer.toString(this.getLevel());
		else if(col==2) return Double.toString(this.getAvg_consume());
		else if(col==3) return Double.toString(this.getSum_sale());
		else return "";
	}

	
	
	
	
	
	
	
	
}
