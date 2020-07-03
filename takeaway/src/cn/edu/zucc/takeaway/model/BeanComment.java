package cn.edu.zucc.takeaway.model;

import java.sql.Blob;
import java.util.Date;

public class BeanComment {
	private int good_no;
	private int user_no;
	private String comment_word;
	private Date comment_date;
	private int commnet_level;
	private Blob comment_pic;
	private int shop_no;
	public int getGood_no() {
		return good_no;
	}
	public void setGood_no(int good_no) {
		this.good_no = good_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getComment_word() {
		return comment_word;
	}
	public void setComment_word(String comment_word) {
		this.comment_word = comment_word;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public int getCommnet_level() {
		return commnet_level;
	}
	public void setCommnet_level(int commnet_level) {
		this.commnet_level = commnet_level;
	}
	public Blob getComment_pic() {
		return comment_pic;
	}
	public void setComment_pic(Blob comment_pic) {
		this.comment_pic = comment_pic;
	}
	public int getShop_no() {
		return shop_no;
	}
	public void setShop_no(int shop_no) {
		this.shop_no = shop_no;
	}
	
	
	
	
	

	
	
}
