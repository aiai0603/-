package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
				if(rs.getTimestamp(10).getTime()<System.currentTimeMillis()&&rs.getInt(11)==1)
				{
					sql="update orders set site=3 where order_no=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, rs.getInt(1));
					pst.execute();
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
					u.setSite(3);
					u.setUser_no(rs.getInt(12));
					u.setShop_name(rs.getString(13));
					result.add(u);
				}else if(rs.getTimestamp(10).getTime()<System.currentTimeMillis()&&rs.getInt(11)==2)
				{
					sql="update orders set site=4 where order_no=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, rs.getInt(1));
					pst.execute();
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
					u.setSite(4);
					u.setUser_no(rs.getInt(12));
					u.setShop_name(rs.getString(13));
					result.add(u);
				}
				else
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
	
public List<BeanOrders> loadallorder (String name) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanOrders> result = new ArrayList<BeanOrders>();
		try {
			conn=DBUtil.getConnection();
			String sql="select orders.order_no,orders.address_no,orders.rider_no,orders.count_no,"
					+ "orders.shop_no,orders.youhui_no,orders.money,orders.true_money,orders.order_time"
					+ ",orders.arrive,orders.site,orders.user_no,shops.shop_name  ,users.user_name,addresses.address from orders,shops,users,addresses "
					+ " where shops.shop_no=orders.shop_no and users.user_no=orders.user_no and addresses.address_no=orders.address_no and shops.shop_name like ? and "
					+ " orders.site!=5  order by orders.arrive desc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,"%"+name+"%");
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				if(rs.getTimestamp(10).getTime()<System.currentTimeMillis()&&rs.getInt(11)==1)
				{
					sql="update orders set site=3 where order_no=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, rs.getInt(1));
					pst.execute();
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
					u.setSite(3);
					u.setUser_no(rs.getInt(12));
					u.setShop_name(rs.getString(13));
					u.setUser_name(rs.getString(14));
					u.setAddress_name(rs.getString(15));
					result.add(u);
				}
				if(rs.getTimestamp(10).getTime()<System.currentTimeMillis()&&rs.getInt(11)==2)
				{
					sql="update orders set site=4 where order_no=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, rs.getInt(1));
					pst.execute();
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
					u.setSite(4);
					u.setUser_no(rs.getInt(12));
					u.setShop_name(rs.getString(13));
					u.setUser_name(rs.getString(14));
					u.setAddress_name(rs.getString(15));
					result.add(u);
				}
				else
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
					u.setUser_name(rs.getString(14));
					u.setAddress_name(rs.getString(15));
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
					pst.execute();
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
					pst.execute();
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
					pst.execute();
			}else {
				sql="update orders set address_no=?,money=?,true_money=?,order_time=?,arrive=?,site=1 where order_no=?";
				 pst=conn.prepareStatement(sql);
					pst.setInt(1, ad.getAddress_no());
					pst.setDouble(2, truesum);
					pst.setDouble(3, fina);
					pst.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
					pst.setTimestamp(5, new java.sql.Timestamp(afterDate.getTime()));
					pst.setInt(6, id);
					pst.execute();
			}	
			
			
			sql="select * from orders where site!=0 and shop_no = (select shop_no from orders where order_no=?)";
			 pst=conn.prepareStatement(sql);
			 pst.setInt(1,id );
			java.sql.ResultSet rs=pst.executeQuery();
			double sum=0;
			int count=0;
			while(rs.next())
			{
				sum+=rs.getDouble(7);
				count++;
			}
			if(count==0)
			{
				sum=0;
				count=1;
			}
			sql="update shops set avg_consume=?,sum_sale=? where shop_no = (select shop_no from orders where order_no=?)";
			 pst=conn.prepareStatement(sql);
			 pst.setDouble(1, sum/count);
			 pst.setDouble(2, sum);
			 pst.setInt(3,id );
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

	public void offerorder(BeanOrders beanOrders, BeanRider beanRider) throws DbException, BusinessException {
		// TODO 自动生成的方法存根
		Connection conn=null;
		try {
			if(beanOrders.getSite()!=1&&beanOrders.getSite()!=3)
				throw new BusinessException("请选择未分配的订单！");
			
			if(beanRider.isRide_site()!=false)
				throw new BusinessException("请选择空闲骑手！");
			
			conn=DBUtil.getConnection();
			
				String sql="update orders set rider_no=?,site=2 ,arrive=? where order_no=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, beanRider.getRider_no());
				pst.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()+30*60*1000));
				pst.setInt(3, beanOrders.getOrder_no());
				pst.execute();
				sql="update rider set rider_site=1 where rider_no=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, beanRider.getRider_no());
				pst.execute();
			
			
			
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

	@SuppressWarnings("deprecation")
	public void arrive(BeanOrders beanOrders) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		Connection conn=null;
		try {
			if(beanOrders.getSite()!=4&&beanOrders.getSite()!=2)
				throw new BusinessException("请选择配送中的订单！");
				conn=DBUtil.getConnection();
			
				String sql="update orders set site=5 where order_no=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, beanOrders.getOrder_no());
				pst.execute();
				sql="update rider set rider_site=0 where rider_no=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, beanOrders.getRider_no());
				pst.execute();
			
				
				sql="select count(*) from rider_income where rider_no=? and MONTH(rider_income_date)=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, beanOrders.getRider_no());
				 Calendar cal = Calendar.getInstance();
				   int month = cal.get(Calendar.MONTH) + 1;
				pst.setInt(2, month);
				java.sql.ResultSet rs=pst.executeQuery();
				int count=0;
				if(rs.next())
				{
					count=rs.getInt(1);
				}
				sql="select rider_level from rider where rider_no=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, beanOrders.getRider_no());
				rs=pst.executeQuery();
				int level=1;
				if(rs.next())
				{
					level=rs.getInt(1);
				}
				
				sql="insert into rider_income values(?,?,0,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, beanOrders.getOrder_no());
				pst.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
				pst.setInt(4, beanOrders.getRider_no());
				double income=0;
				if(count<100)
				{
					income=2;
				}else if(count<300){
					
					income=3;
				}else if(count<450) {
					
					income=5;
				}else if(count<550) {
					income=6;
				}else if(count<650) {
					income=7;
				}else { 
					income=8;
					
				}
				if(level==1) {
					income+=0.5;
					if(count>=500)
					income+=0.5;
				}
				pst.setDouble(3, income);
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

	public void deleteorder(BeanOrders beanOrders) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		Connection conn=null;
		try {
			if(beanOrders.getSite()!=3&&beanOrders.getSite()!=1&&beanOrders.getSite()!=4)
				throw new BusinessException("请选择还未配送或者超时的订单");
			conn=DBUtil.getConnection();
			
				String sql="update orders set site=0 where order_no=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, beanOrders.getOrder_no());
				pst.execute();
				if(beanOrders.getRider_no()!=0)
				{
					sql="update rider set rider_site=0 where rider_no=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, beanOrders.getRider_no());
					pst.execute();
				}
				
				
				sql="select * from orders where site!=0 and shop_no = (select shop_no from orders where order_no=?)";
				 pst=conn.prepareStatement(sql);
				 pst.setInt(1,beanOrders.getOrder_no());
				java.sql.ResultSet rs=pst.executeQuery();
				double sum=0;
				int count=0;
				while(rs.next())
				{
					sum+=rs.getDouble(7);
					count++;
				}
				if(count==0) {
					count=1;
					sum=0;
				}
				sql="update shops set avg_consume=?,sum_sale=? where shop_no = (select shop_no from orders where order_no=?)";
				 pst=conn.prepareStatement(sql);
				 pst.setDouble(1, sum/count);
				 pst.setDouble(2, sum);
				 pst.setInt(3,beanOrders.getOrder_no() );
				 pst.execute();
				 
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
