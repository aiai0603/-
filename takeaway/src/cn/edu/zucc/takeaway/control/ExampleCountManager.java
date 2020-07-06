package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeaway.model.BeanCounts;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;




public class ExampleCountManager {

	public List<BeanCounts> loadcount(BeanShops beanshop) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanCounts> result = new ArrayList<BeanCounts>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from counts where shop_no = ? order by ac_money asc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,beanshop.getShop_no());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanCounts u=new BeanCounts();
				u.setCount_no(rs.getInt(1));
				u.setShop_no(rs.getInt(2));
				u.setAc_money(rs.getDouble(3));
				u.setCount_sale(rs.getDouble(4));
				u.setTogether(rs.getBoolean(5));
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


	public void addcount(BeanShops bs, double p1, double p2, int i) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from counts where ac_money=? and shop_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setDouble(1, p1);;
			pst.setLong(2, bs.getShop_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("该金额已经存在满减！");
			
			sql="select count_sale from counts where shop_no=? and ac_money<? order by ac_money desc limit 1";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, bs.getShop_no());
			pst.setDouble(2, p1);
			rs=pst.executeQuery();
			
			if(rs.next()) 
			{
				if(rs.getInt(1)>=p2)throw new BusinessException("该金额满减不得低于上一等级！");
			}
		
			
			sql="select count_sale from counts where shop_no=? and ac_money>? order by ac_money asc limit 1";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, bs.getShop_no());
			pst.setDouble(2, p1);
			rs=pst.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)<=p2)throw new BusinessException("该金额满减不得高于下一等级！");
			}
		
			
			
			sql="insert into counts(shop_no,ac_money,count_sale,together) values(?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, bs.getShop_no());
			pst.setDouble(2, p1);
			pst.setDouble(3, p2);
			if(i==0)
				pst.setBoolean(4, true);
			else
				pst.setBoolean(4, false);
			pst.execute();
			
			rs.close();
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

	public void deletecount(BeanCounts beanCounts) throws DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from counts where count_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, beanCounts.getCount_no());
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

	public void modifycount(BeanCounts count, double p1, double p2, int i) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from counts where ac_money=? and shop_no=? and count_no !=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setDouble(1, p1);
			pst.setInt(3, count.getCount_no());
			pst.setLong(2, count.getShop_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("该金额已经存在满减！");
			
			sql="select count_sale from counts where shop_no=? and ac_money<? and count_no !=? order by ac_money desc limit 1";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, count.getCount_no());
			pst.setDouble(2, p1);
			pst.setInt(3,count.getCount_no());
			rs=pst.executeQuery();
			
			if(rs.next()) 
			{
				if(rs.getInt(1)>=p2)throw new BusinessException("该金额满减不得低于上一等级！");
			}
		
			
			sql="select count_sale from counts where shop_no=? and ac_money>? and count_no !=? order by ac_money asc limit 1";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,count.getShop_no());
			pst.setDouble(2, p1);
			pst.setInt(3,count.getCount_no());
			rs=pst.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)<=p2)throw new BusinessException("该金额满减不得高于下一等级！");
			}
		
			
			
			sql="update counts set ac_money=?,count_sale=?,together=? where count_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(4, count.getCount_no());
			pst.setDouble(1, p1);
			pst.setDouble(2, p2);
			if(i==0)
				pst.setBoolean(3, true);
			else
				pst.setBoolean(3, false);
			pst.execute();
			
			rs.close();
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
