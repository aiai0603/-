package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeaway.model.BeanCounts;
import cn.edu.zucc.takeaway.model.BeanGive;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.model.BeanYouHui;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;




public class ExampleGiveManager {

public List<BeanGive> loadgive(int id,String string) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanGive> result = new ArrayList<BeanGive>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select a.user_no,a.shop_no,a.need,a.already,a.youhui_no,b.shop_name ,c.youhui_sale ,c.endday from give a , "
					+ "shops b,youhui c where a.shop_no=b.shop_no and c.youhui_no = a.youhui_no  and user_no = ? and shop_name like ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setString(2,"%"+string+"%");
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanGive u=new BeanGive();
				u.setUser_no(rs.getInt(1));
				u.setShop_no(rs.getInt(2));
				u.setYouhui_no(rs.getInt(5));
				u.setNeed(rs.getInt(3));
				u.setAlready(rs.getInt(4));
				u.setShop_name(rs.getString(6));
				u.setYouhui_sale(rs.getDouble(7));
				u.setEndtime(rs.getTimestamp(8));
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
	public void addgive(int id,int id3) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			
			conn=DBUtil.getConnection();
			
			String sql="select * from orders where order_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id3);
			java.sql.ResultSet rsshop=pst.executeQuery();
			rsshop.next();
			int id2=rsshop.getInt(5);
			
			sql="select * from give where user_no=? and shop_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, id2);
			java.sql.ResultSet rs=pst.executeQuery();
			
		
			sql="select * from youhui where shop_no=? and youhui_no not in (select youhui_no from give) ";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id2);
			java.sql.ResultSet rs22=pst.executeQuery();
			
			while(rs22.next()) {
				if(rs22.getTimestamp(5).getTime()<System.currentTimeMillis()&&rs22.getTimestamp(6).getTime()>System.currentTimeMillis())
				{
					if(rs22.getInt(4)==1)
					{
						sql="select * from owner_count where user_no=? and youhui_no=?";
						pst=conn.prepareStatement(sql);
						pst.setInt(1, id);
						pst.setInt(2, rs22.getInt(1));
						java.sql.ResultSet rs5=pst.executeQuery();
						
						if(rs5.next())
						{
							sql="update owner_count set num=num+1  where user_no=? and youhui_no=?";
							pst=conn.prepareStatement(sql);
							pst.setInt(1, id);
							pst.setInt(2, rs5.getInt(1));
							pst.execute();
						}
						else
						{
							sql="insert into owner_count values(?,?,?,1,?,?)";
							pst=conn.prepareStatement(sql);
							pst.setInt(1, rs22.getInt(1));
							pst.setInt(2, id);
							pst.setDouble(3, rs22.getDouble(3));
							pst.setTimestamp(4, rs22.getTimestamp(6));
							pst.setInt(5, rs22.getInt(2));
							pst.execute();
						}
						
						
					}else {
						sql="insert into give values(?,?,?,1,?)";
						pst=conn.prepareStatement(sql);
						pst.setInt(1, id);
						pst.setInt(2, id2);
						pst.setDouble(3, rs22.getInt(4));
						pst.setInt(4, rs22.getInt(1));
						pst.execute();
					}
				}
			}
				
			while(rs.next())
			{	
				if(rs.getInt(4)+1!=rs.getInt(3))
				{
					sql="update give set already=already+1  where user_no=? and youhui_no=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, id);
					pst.setInt(2, rs.getInt(5));
					pst.execute();
					
				}
				else
				{
					sql="select * from owner_count where user_no=? and youhui_no=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, id);
					pst.setInt(2, rs.getInt(5));
					java.sql.ResultSet rs2=pst.executeQuery();
					if(rs2.next())
					{
						sql="update owner_count set num=num+1  where user_no=? and youhui_no=?";
						pst=conn.prepareStatement(sql);
						pst.setInt(1, id);
						pst.setInt(2, rs.getInt(5));
						pst.execute();
					}
					else 
					{
					
						sql="select * from youhui where youhui_no=? ";
						pst=conn.prepareStatement(sql);
						pst.setInt(1, rs.getInt(5));
						java.sql.ResultSet rs3=pst.executeQuery();
						rs3.next();
						
						sql="insert into owner_count values(?,?,?,1,?,?)";
						pst=conn.prepareStatement(sql);
						pst.setInt(1, rs.getInt(5));
						pst.setInt(2, id);
						pst.setDouble(3, rs3.getInt(3));
						pst.setTimestamp(4, rs3.getTimestamp(6));
						pst.setInt(5, id2);
						pst.execute();
						rs3.close();
					}
					sql="delete from give where user_no=? and youhui_no=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, id);
					pst.setInt(2, rs.getInt(5));
					rs2.close();
					pst.execute();
				}
				
			}
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


	/*
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


	public void addcount(BeanShops bs, double p1, double p2) throws BusinessException, DbException {
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
		
			
			
			sql="insert into counts(shop_no,ac_money,count_sale) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, bs.getShop_no());
			pst.setDouble(2, p1);
			pst.setDouble(3, p2);
			
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

	public void modifycount(BeanCounts count, double p1, double p2) throws BusinessException, DbException {
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
			sql="update counts set ac_money=?,count_sale=? where count_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(3, count.getCount_no());
			pst.setDouble(1, p1);
			pst.setDouble(2, p2);
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


	public BeanCounts offercount(int orderid, double d) throws DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		BeanCounts u =new BeanCounts();
		try {
			conn=DBUtil.getConnection();
			String sql="select shop_no from orders where order_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,orderid);
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			int shop=rs.getInt(1);
			
			sql="select * from counts where shop_no=? and ac_money<=? order by ac_money desc limit 1";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,shop);
			pst.setDouble(2, d);
			rs=pst.executeQuery();
			if(rs.next())
			{
				u.setCount_no(rs.getInt(1));
				u.setShop_no(rs.getInt(2));
				u.setAc_money(rs.getDouble(3));
				u.setCount_sale(rs.getDouble(4));
			}
			else {
				return null;
			}
			rs.close();
			pst.close();
			return u;
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
