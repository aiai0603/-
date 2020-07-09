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
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.model.BeanYouHui;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;




public class ExampleYouhuiManager {

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
				u.setTogether(rs.getBoolean(7));
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
public List<BeanYouHui> loadyouhui2(BeanShops beanshop) throws DbException{
		
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
				if(rs.getTimestamp(6).getTime()>System.currentTimeMillis()&&rs.getTimestamp(5).getTime()<System.currentTimeMillis())
				{
					BeanYouHui u=new BeanYouHui();
					u.setYouhui_no(rs.getInt(1));
					u.setShop_no(rs.getInt(2));
					u.setYouhui_sale(rs.getDouble(3));
					u.setRequest(rs.getInt(4));
					u.setStartday(rs.getTimestamp(5));
					u.setEndday(rs.getTimestamp(6));
					u.setTogether(rs.getBoolean(7));
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

	public void addyouhui(BeanShops shop, int p1, double p2, String string, String string2, int i) throws BusinessException, ParseException, DbException {
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
			sql="insert into youhui(shop_no,youhui_sale,request,startday,endday,together) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, shop.getShop_no());
			pst.setDouble(2,p2);
			pst.setInt(3, p1);
			pst.setTimestamp(4, d1);
			pst.setTimestamp(5, d2);
			if(i==0)
			pst.setBoolean(6, false);
			else
			pst.setBoolean(6, true);
			
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

	public void modifyyouhui(BeanYouHui by, int p1, double p2, String string, String string2,int i) throws BusinessException, ParseException, DbException {
		// TODO 自动生成的方法存根
		if(p1>=10)throw new BusinessException("需求订单不得高于10单");
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Timestamp d1=new java.sql.Timestamp(f.parse(string).getTime());
			java.sql.Timestamp d2=new java.sql.Timestamp(f.parse(string2).getTime());
			if(f.parse(string2).getTime()<f.parse(string).getTime())throw new BusinessException("结束日期不得早于开始日期");
			String sql="update youhui set youhui_sale=?,request=?,startday=?,endday=?,together=? where youhui_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setDouble(1,p2);
			pst.setInt(2, p1);
			pst.setTimestamp(3, d1);
			pst.setTimestamp(4, d2);
			pst.setInt(6, by.getYouhui_no());
			if(i==0)
				pst.setBoolean(5, false);
				else
				pst.setBoolean(5, true);
				
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

	

}
