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

	public List<BeanOwnerCount> loadallowner(int user_no, String string) throws DbException {
		java.sql.Connection conn=null;
		List<BeanOwnerCount> result = new ArrayList<BeanOwnerCount>();
		try {
			conn=DBUtil.getConnection();
			String sql="select owner_count.youhui_no,owner_count.user_no,owner_count.count_money,owner_count.num,"
						+ "owner_count.end_date,owner_count.shop_no,shops.shop_name,youhui.together from owner_count,shops,youhui"
						+ " where youhui.youhui_no=owner_count.youhui_no and shops.shop_no=owner_count.shop_no and owner_count.user_no=? and "
						+ "shops.shop_name like ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user_no);
			pst.setString(2, "%"+string+"%");
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanOwnerCount u=new BeanOwnerCount();
					u.setYouhui_no(rs.getInt(1));
					u.setUser_no(rs.getInt(2));
					u.setCount_money(rs.getDouble(3));
					u.setNum(rs.getInt(4));
					u.setEnd_date(rs.getTimestamp(5));
					u.setShop_no(rs.getInt(6));
					u.setShop_name(rs.getString(7));
					u.setTogether(rs.getBoolean(8));
				
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
	
	public void deleteowner(BeanOwnerCount c) throws DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select num from owner_count where youhui_no=? and user_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, c.getYouhui_no());
			pst.setInt(2, c.getUser_no());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)==1)
			{
				sql="delete from owner_count where youhui_no=? and user_no=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, c.getYouhui_no());
				pst.setInt(2, c.getUser_no());
				pst.execute();
				pst.close();
			}
			else {
				sql="update owner_count set num=num-1 where youhui_no=? and user_no=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, c.getYouhui_no());
				pst.setInt(2, c.getUser_no());
				pst.execute();
				pst.close();
			}
				rs.close();
				
			
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
