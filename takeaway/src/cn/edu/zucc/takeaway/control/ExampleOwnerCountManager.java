package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.model.BeanYouHui;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;




public class ExampleOwnerCountManager {

	public List<BeanOwnerCount> loadowner(int order,int id, boolean choose) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanOwnerCount> result = new ArrayList<BeanOwnerCount>();
		try {
			int shop = 0;
			conn=DBUtil.getConnection();
			String sql="select shop_no from orders where order_no=?";
			java.sql.PreparedStatement pst =conn.prepareStatement(sql);
			pst.setInt(1, order);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
				shop=rs.getInt(1);
			
			
			if(choose==false)
			{
				sql="select owner_count.youhui_no,owner_count.user_no,owner_count.count_money,owner_count.num,"
						+ "owner_count.end_date,owner_count.shop_no from owner_count,youhui where youhui.youhui_no=owner_count.youhui_no and  owner_count.shop_no=? and owner_count.user_no=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,shop);
				pst.setInt(2, id);
				rs=pst.executeQuery();
				while(rs.next())
				{
					BeanOwnerCount u=new BeanOwnerCount();
					if(rs.getTimestamp(5).getTime()>System.currentTimeMillis())
					{
						u.setYouhui_no(rs.getInt(1));
						u.setUser_no(rs.getInt(2));
						u.setCount_money(rs.getDouble(3));
						u.setNum(rs.getInt(4));
						u.setEnd_date(rs.getTimestamp(5));
						u.setShop_no(rs.getInt(6));
					}
					result.add(u);
				}
			
			
			}else {
				sql="select owner_count.youhui_no,owner_count.user_no,owner_count.count_money,owner_count.num,"
						+ "owner_count.end_date,owner_count.shop_no from owner_count,youhui where youhui.youhui_no=owner_count.youhui_no and youhui.together=1 and owner_count.shop_no=? and owner_count.user_no=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,shop);
				pst.setInt(2, id);
				rs=pst.executeQuery();
				while(rs.next())
				{
					
					BeanOwnerCount u=new BeanOwnerCount();
					if(rs.getTimestamp(5).getTime()>System.currentTimeMillis())
					{
						u.setYouhui_no(rs.getInt(1));
						u.setUser_no(rs.getInt(2));
						u.setCount_money(rs.getDouble(3));
						u.setNum(rs.getInt(4));
						u.setEnd_date(rs.getTimestamp(5));
						u.setShop_no(rs.getInt(6));
					}
					result.add(u);
				
				}
			}
			
			rs.close();
			pst.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
	
	/*
	public List<BeanYouHui> loadyouhui(BeanShops beanshop) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanYouHui> result = new ArrayList<BeanYouHui>();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from youhui where shop_no = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,beanshop.getShop_no());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanYouHui u=new BeanYouHui();
				u.setYouhui_no(rs.getInt(1));
				u.setShop_no(rs.getInt(2));
				u.setYouhui_sale(rs.getDouble(3));
				u.setRequest(rs.getInt(4));
				u.setStartday(rs.getTimestamp(5));
				u.setEndday(rs.getTimestamp(6));
				result.add(u);
			}
			rs.close();
			pst.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
	

	public void addyouhui(BeanShops shop, int p1, double p2, String string, String string2) throws BusinessException, ParseException, DbException {
		// TODO 自动生成的方法存根
		if(p1>=10)throw new BusinessException("需求订单不得高于10单");
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from youhui where shop_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setLong(1,shop.getShop_no());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)>=3)throw new BusinessException("优惠活动不得多于3项");
			
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Timestamp d1=new java.sql.Timestamp(f.parse(string).getTime());
			java.sql.Timestamp d2=new java.sql.Timestamp(f.parse(string2).getTime());
			if(f.parse(string2).getTime()<f.parse(string).getTime())throw new BusinessException("结束日期不得早于开始日期");
			sql="insert into youhui(shop_no,youhui_sale,request,startday,endday) values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, shop.getShop_no());
			pst.setDouble(2,p2);
			pst.setInt(3, p1);
			pst.setTimestamp(4, d1);
			pst.setTimestamp(5, d2);
			pst.execute();
			
			pst.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public void deleteyouhui(BeanYouHui c) throws DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from youhui where youhui_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, c.getYouhui_no());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public void modifyyouhui(BeanYouHui by, int p1, double p2, String string, String string2) throws BusinessException, ParseException, DbException {
		// TODO 自动生成的方法存根
		if(p1>=10)throw new BusinessException("需求订单不得高于10单");
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Timestamp d1=new java.sql.Timestamp(f.parse(string).getTime());
			java.sql.Timestamp d2=new java.sql.Timestamp(f.parse(string2).getTime());
			if(f.parse(string2).getTime()<f.parse(string).getTime())throw new BusinessException("结束日期不得早于开始日期");
			String sql="update youhui set youhui_sale=?,request=?,startday=?,endday=? where youhui_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setDouble(1,p2);
			pst.setInt(2, p1);
			pst.setTimestamp(3, d1);
			pst.setTimestamp(4, d2);
			pst.setInt(5, by.getYouhui_no());
			pst.execute();
			pst.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	*/
	

}
