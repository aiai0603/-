package cn.edu.zucc.takeaway.model;

import java.util.Date;

public class BeanUsers {
	public static BeanUsers currentLoginUser=null;
	private int user_no;
	private String user_name;
	private int sex;
	private String pwd;
	private String tele;
	private String email;
	private String city;
	private Date sign_date;
	private boolean vip;
	private Date vip_end;
	
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getSign_date() {
		return sign_date;
	}
	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	public Date getVip_end() {
		return vip_end;
	}
	public void setVip_end(Date vip_end) {
		this.vip_end = vip_end;
	}
	
	
	
	
	
}
