package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;



public class ExampleShopManager  {
	public void addshop(String name) throws DbException, BusinessException {
		
		if(name==null || "".equals(name) || name.length()>20){
			throw new BusinessException("商家名必须是1-20个字");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from shops where shop_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("商家已经存在！");
			
			sql="insert into shops(shop_name,level,avg_consume,sum_sale) values(?,5,0,0)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,name);
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
	public List<BeanShops> loadshop(String name) throws DbException{
	
		java.sql.Connection conn=null;
		List<BeanShops> result = new ArrayList<BeanShops>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from shops where shop_name like ? order by level desc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			String str="%"+name+"%";
			pst.setString(1,str);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanShops u=new BeanShops();
				u.setShop_no(rs.getInt(1));
				u.setShop_name(rs.getString(2));
				u.setLevel(rs.getInt(3));
				u.setAvg_consume(rs.getDouble(4));
				u.setSum_sale(rs.getDouble(5));
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
	public List<BeanShops> loadshopbyavg2(String name) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanShops> result = new ArrayList<BeanShops>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from shops where shop_name like ? order by avg_consume desc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			String str="%"+name+"%";
			pst.setString(1,str);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanShops u=new BeanShops();
				u.setShop_no(rs.getInt(1));
				u.setShop_name(rs.getString(2));
				u.setLevel(rs.getInt(3));
				u.setAvg_consume(rs.getDouble(4));
				u.setSum_sale(rs.getDouble(5));
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
public List<BeanShops> loadshopbyavg1(String name) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanShops> result = new ArrayList<BeanShops>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from shops where shop_name like ? order by avg_consume asc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			String str="%"+name+"%";
			pst.setString(1,str);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanShops u=new BeanShops();
				u.setShop_no(rs.getInt(1));
				u.setShop_name(rs.getString(2));
				u.setLevel(rs.getInt(3));
				u.setAvg_consume(rs.getDouble(4));
				u.setSum_sale(rs.getDouble(5));
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
public List<BeanShops> loadshopbysum(String name) throws DbException{
	
	java.sql.Connection conn=null;
	List<BeanShops> result = new ArrayList<BeanShops>();
	try {
		
		conn=DBUtil.getConnection();
		String sql="select * from shops where shop_name like ? order by sum_sale desc";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		String str="%"+name+"%";
		pst.setString(1,str);
		java.sql.ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			BeanShops u=new BeanShops();
			u.setShop_no(rs.getInt(1));
			u.setShop_name(rs.getString(2));
			u.setLevel(rs.getInt(3));
			u.setAvg_consume(rs.getDouble(4));
			u.setSum_sale(rs.getDouble(5));
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
	public void deleteshop(BeanShops curshop) throws DbException, BusinessException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from kinds where shop_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,curshop.getShop_no());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)!=0)
			{
				throw new BusinessException("该商家仍有设置商品类别，无法删除！");
			}
			sql="delete from shops where shop_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, curshop.getShop_no());
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
	public void modifyshop(int id,String name) throws DbException, BusinessException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		if(name==null || "".equals(name) || name.length()>20){
			throw new BusinessException("商家名必须是1-20个字");
		}
		try {
			conn=DBUtil.getConnection();
			String sql="select * from shops where shop_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("商家已经存在！");
			
			
			sql="select * from shops where shop_no = ?";
			pst=conn.prepareStatement(sql);
			pst.setLong(1,id);
			rs=pst.executeQuery();
			if(!rs.next())
			{
				throw new BusinessException("没有此商家!");
			}
			sql="update shops set shop_name=? where shop_no=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setLong(2,id);
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
