package cn.edu.zucc.takeaway.model;

public class BeanKind {
	public static final  String[] tableTitles2= {"类别名","含有商品数"};
	public static final  String[] tableTitles= {"类别名"};
	private int shop_no;
	private int kind_no;
	private String kind_name;
	private int kind_sum;//商品数量
	
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
	public String getKind_name() {
		return kind_name;
	}
	public void setKind_name(String kind_name) {
		this.kind_name = kind_name;
	}
	public int getKind_sum() {
		return kind_sum;
	}
	public void setKind_sum(int kind_sum) {
		this.kind_sum = kind_sum;
	}
	
	public String getCell(int col){
		
		if(col==0) return this.getKind_name();
		else if(col==1) return Integer.toString(this.getKind_sum());
		
		else return "";
	}
	
	
	
	
	
	
}
