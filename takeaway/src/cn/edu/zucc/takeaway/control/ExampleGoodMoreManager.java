package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeaway.model.BeanAddresser;
import cn.edu.zucc.takeaway.model.BeanGoodMore;
import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;




public class ExampleGoodMoreManager {

	public List<BeanGoodMore> loadgoodmore(int orderid) throws DbException {
		java.sql.Connection conn=null;
		List<BeanGoodMore> result = new ArrayList<BeanGoodMore>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from good_more where  order_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,orderid);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanGoodMore u=new BeanGoodMore();
				u.setOrder_no(rs.getInt(1));
				u.setGood_no(rs.getInt(2));
				u.setGood_count(rs.getInt(3));
				u.setGood_price(rs.getDouble(4));
				u.setGood_sale(rs.getDouble(5));
				u.setGood_name(rs.getString(6));
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

	public void addordermore(BeanGoods beanGoods, int orderid, int count) throws BusinessException, DbException {
		// TODO �Զ����ɵķ������
		if(count<=0)throw new BusinessException("��������С��1");
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from good_more where good_no =? and order_no = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,beanGoods.getGood_no());
			pst.setInt(2,orderid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("��Ʒ�Ѿ����ڣ�");
			sql="insert into good_more(order_no,good_no,good_count,good_price,good_sale,good_name) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,orderid);
			pst.setInt(2,beanGoods.getGood_no() );
			pst.setInt(3, count);
			pst.setDouble(4,beanGoods.getGood_price()*count);
			if(BeanUsers.currentLoginUser.isVip())
			pst.setDouble(5, beanGoods.getGood_sale()*count);
			else
			pst.setDouble(5, beanGoods.getGood_price()*count);
			pst.setString(6, beanGoods.getGood_name());
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

	public void deleteordermore(BeanGoodMore beanGoodMore) throws DbException {
		// TODO �Զ����ɵķ������
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from good_more where good_no=? and order_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, beanGoodMore.getGood_no());
			pst.setInt(2, beanGoodMore.getOrder_no());
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

	public void deleteordermore(BeanGoodMore bean, int count) throws DbException {
		// TODO �Զ����ɵķ������
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select good_price,good_sale from goods where good_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,bean.getGood_no());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			double price1=rs.getDouble(1);
			double price2=rs.getDouble(2);
			sql="update good_more set good_count=?,good_price=?,good_sale=? where good_no=? and order_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,count);
			pst.setDouble(2,price1*count);
			if(BeanUsers.currentLoginUser.isVip())
			pst.setDouble(3,price2*count);
			else
			pst.setDouble(3,price1*count);
			pst.setInt(4, bean.getGood_no());
			pst.setInt(5, bean.getOrder_no());
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

	public double sumall(int orderid, int user_no) throws DbException {
		// TODO �Զ����ɵķ������
		java.sql.Connection conn=null;
		double result = 0;
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from good_more where  order_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,orderid);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				result+=rs.getDouble(5);
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

	public double sumall2(int orderid, int user_no) throws DbException {
		// TODO �Զ����ɵķ������
		java.sql.Connection conn=null;
		double result = 0;
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from good_more where  order_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,orderid);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				result+=rs.getDouble(4);
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

	public double sumall3(int id, int user_no, BeanOwnerCount by, boolean b) throws DbException {
		// TODO �Զ����ɵķ������
		java.sql.Connection conn=null;
		double result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from good_more where  order_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				result+=rs.getDouble(5);
			}
			if(by!=null)
			{
				sql="select count_money from owner_count where youhui_no=? and user_no=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, by.getYouhui_no());
				pst.setInt(2, by.getUser_no());
				rs=pst.executeQuery();
				while(rs.next())
				{
					result-=rs.getDouble(1);
				}
				if(result<0)
					result=0;
			}
			if(b==true)
			{
				ExampleCountManager ex2=new ExampleCountManager();
				double count=ex2.offercount(id,this.sumall2(id,BeanUsers.currentLoginUser.getUser_no())).getCount_sale();
				result-=count;
				if(result<0)
					result=0;
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
		return result;
	}

	public void isempty(int id) throws BusinessException, DbException {
		java.sql.Connection conn=null;
		double result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from good_more where  order_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())
			{
				throw new BusinessException("����Ϊ��");
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

	public void deleteall(int id) throws DbException {
		// TODO �Զ����ɵķ������
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="delete from good_more where order_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.execute();
			sql="delete from orders where order_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.execute();
			pst.close();
		conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	


}
