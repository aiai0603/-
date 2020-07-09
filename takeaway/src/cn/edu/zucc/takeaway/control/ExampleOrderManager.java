package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeaway.model.BeanAddresser;
import cn.edu.zucc.takeaway.model.BeanCounts;
import cn.edu.zucc.takeaway.model.BeanOrders;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanRider;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;



public class ExampleOrderManager  {
	
	
	public List<BeanOrders> loadorder (String name, int i) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanOrders> result = new ArrayList<BeanOrders>();
		try {
			conn=DBUtil.getConnection();
			String sql="select orders.order_no,orders.address_no,orders.rider_no,orders.count_no,"
					+ "orders.shop_no,orders.youhui_no,orders.money,orders.true_money,orders.order_time"
					+ ",orders.arrive,orders.site,orders.user_no,shops.shop_name  from orders,shops where shops.shop_no=orders.shop_no and shops.shop_name like ? and orders.user_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,"%"+name+"%");
			pst.setInt(2, i);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanOrders u=new BeanOrders();
				u.setOrder_no(rs.getInt(1));
				u.setAddress_no(rs.getInt(2));
				u.setRider_no(rs.getInt(3));
				u.setCount_no(rs.getInt(4));
				u.setShop_no(rs.getInt(5));
				u.setYouhui_no(rs.getInt(6));
				u.setMoney(rs.getDouble(7));
				u.setTrue_money(rs.getDouble(8));
				u.setOrder_time(rs.getTimestamp(9));
				u.setArrive(rs.getTimestamp(10));
				u.setSite(rs.getInt(11));
				u.setUser_no(rs.getInt(12));
				u.setShop_name(rs.getString(13));
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

	public int addorder(int userid,int shopid) throws DbException, BusinessException {
		// TODO 自动生成的方法存根
		
		
		java.sql.Connection conn=null;
		try {
			
			conn=DBUtil.getConnection();
			String sql="select sum(kind_sum) from kinds where shop_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, shopid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())throw new BusinessException("商家没有做好开张准备！");
			if(rs.getInt(1)==0)throw new BusinessException("商家没有做好开张准备！");
			
			sql="insert into orders(user_no,shop_no,site) values(?,?,0)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, userid);
			pst.setInt(2, shopid);;
			pst.execute();
			
		
			sql="select order_no from orders where shop_no=? and user_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(2, userid);
			pst.setInt(1, shopid);;
			rs=pst.executeQuery();
			int orderid = 0;
			while(rs.next())
			{
				orderid=rs.getInt(1);
			}
			
			rs.close();
			pst.close();
			
			return orderid;
			
			
		
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
	
	public void upload(int id, BeanAddresser ad, BeanOwnerCount by, double fina, double truesum, BeanCounts countsum, Date afterDate) throws DbException, BusinessException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			String sql=null;
			 java.sql.PreparedStatement pst=null;
			conn=DBUtil.getConnection();
			if(ad==null)
			{
				throw new BusinessException("请选择地址！");
			}
			if(by!=null&&countsum!=null)
			{
				 sql="update orders set address_no=?,count_no=?,youhui_no=?,money=?,true_money=?,order_time=?,arrive=?,site=1 where order_no=?";
				 pst=conn.prepareStatement(sql);
					pst.setInt(1, ad.getAddress_no());
					pst.setInt(2, countsum.getCount_no());
					pst.setInt(3, by.getYouhui_no());
					pst.setDouble(4, truesum);
					pst.setDouble(5, fina);
					pst.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
					pst.setTimestamp(7, new java.sql.Timestamp(afterDate.getTime()));
					pst.setInt(8, id);
			}
			else if(by==null&&countsum!=null)
			{
				sql="update orders set address_no=?,count_no=?,money=?,true_money=?,order_time=?,arrive=?,site=1 where order_no=?";
				pst=conn.prepareStatement(sql);
					pst.setInt(1, ad.getAddress_no());
					pst.setInt(2, countsum.getCount_no());
					
					pst.setDouble(3, truesum);
					pst.setDouble(4, fina);
					pst.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
					pst.setTimestamp(6, new java.sql.Timestamp(afterDate.getTime()));
					pst.setInt(7, id);
			}else if(by!=null&&countsum==null)
			{
				sql="update orders set address_no=?,youhui_no=?,money=?,true_money=?,order_time=?,arrive=?,site=1 where order_no=?";
				pst=conn.prepareStatement(sql);
					pst.setInt(1, ad.getAddress_no());
					pst.setInt(2, by.getYouhui_no());
					pst.setDouble(3, truesum);
					pst.setDouble(4, fina);
					pst.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
					pst.setTimestamp(6, new java.sql.Timestamp(afterDate.getTime()));
					pst.setInt(7, id);
			}else {
				sql="update orders set address_no=?,money=?,true_money=?,order_time=?,arrive=?,site=1 where order_no=?";
				 pst=conn.prepareStatement(sql);
					pst.setInt(1, ad.getAddress_no());
					pst.setDouble(2, truesum);
					pst.setDouble(3, fina);
					pst.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
					pst.setTimestamp(5, new java.sql.Timestamp(afterDate.getTime()));
					pst.setInt(6, id);
			}
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
	
	
/*
	public void deleteadd(BeanAddresser book) throws DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from addresses where address_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, book.getAddress_no());
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

	public void modifyaddress(int id, String sheng, String shi, String add, String call, String tele) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		if(add==null || "".equals(add)){
			throw new BusinessException("地址为空！");
		}if(call==null || "".equals(call)||call.length()>10){
			throw new BusinessException("称呼为1-10位！");
		}if(tele==null || "".equals(tele)||tele.length()!=11){
			throw new BusinessException("电话为11位！");
		}
		for(int i=0;i<tele.length();i++) {
			if(!Character.isDigit(tele.charAt(i))) {
				throw new BusinessException("电话含有非法字符");
			}
				
		}
		
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update  addresses set sheng=?,shi=?,address=?,call_user=?,address_tele=? where address_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(6,id );
			pst.setString(1, sheng);
			pst.setString(2, shi);
			pst.setString(3, add);
			pst.setString(4, call);
			pst.setString(5, tele);
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
