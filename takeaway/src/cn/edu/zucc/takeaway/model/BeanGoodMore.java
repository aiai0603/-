package cn.edu.zucc.takeaway.model;

public class BeanGoodMore {
	public static final  String[] tableTitles= {"商品","购买数量","原价","实际价格"};
	private int order_no;
	private int good_no;
	private int good_count;
	private double good_price;
	private double good_sale;
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	private String good_name;
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getGood_no() {
		return good_no;
	}
	public void setGood_no(int good_no) {
		this.good_no = good_no;
	}
	public int getGood_count() {
		return good_count;
	}
	public void setGood_count(int good_count) {
		this.good_count = good_count;
	}
	public double getGood_price() {
		return good_price;
	}
	public void setGood_price(double good_price) {
		this.good_price = good_price;
	}
	public double getGood_sale() {
		return good_sale;
	}
	public void setGood_sale(double good_sale) {
		this.good_sale = good_sale;
	}
	
	public String getCell(int col){
		
		if(col==0) return this.getGood_name();
		else if(col==1)  return Integer.toString(this.getGood_count());
		else if(col==2) return Double.toString(this.getGood_price());
		else if(col==3) return Double.toString(this.getGood_sale());
		else return "";
	}
	
	
	
	
	
}
