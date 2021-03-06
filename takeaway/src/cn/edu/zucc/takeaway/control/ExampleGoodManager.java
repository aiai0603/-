package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;




public class ExampleGoodManager {

	public List<BeanGoods> loadgood(BeanKind beankind) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanGoods> result = new ArrayList<BeanGoods>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from goods where kind_no = ? and isdelete = 0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,beankind.getKind_no());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanGoods u=new BeanGoods();
				u.setGood_no(rs.getInt(1));
			
				u.setGood_name(rs.getString(3));
				u.setKind_no(rs.getInt(2));
				u.setGood_price(rs.getDouble(4));
				u.setGood_sale(rs.getDouble(5));
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

	public void addgood(BeanKind kind, String name, double p1, double p2) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		if(p1<0||p2<0)throw new BusinessException("金额不得为负值");
		if(name==null || "".equals(name) || name.length()>20){
			throw new BusinessException("商品名必须是1-20个字！");
		}
		if(p2>p1) {
			throw new BusinessException("优惠价不得高于初始价！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select * from goods where good_name=? and kind_no = ? and isdelete = 0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setInt(2,kind.getKind_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("商品已经存在！");
			
			sql="insert into goods(kind_no,good_name,good_price,good_sale,isdelete) values(?,?,?,?,0)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,kind.getKind_no());
			pst.setString(2, name);
			pst.setDouble(3, p1);
			pst.setDouble(4, p2);
			pst.execute();
			sql="update kinds set kind_sum=kind_sum+1 where kind_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,kind.getKind_no());
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

	public void deletegood(BeanGoods beanGoods) throws DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="update goods set isdelete=1 where good_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, beanGoods.getGood_no());
			pst.execute();
			
			sql="update kinds set kind_sum=kind_sum-1 where kind_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,beanGoods.getKind_no());
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

	public void modifygood(BeanGoods beanGoods, String name, double p1, double p2) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		if(p1<0||p2<0)throw new BusinessException("金额不得为负值");
		if(name==null || "".equals(name) || name.length()>20){
			throw new BusinessException("商品名必须是1-20个字！");
		}
		if(p2>p1) {
			throw new BusinessException("优惠价不得高于初始价！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goods where good_name=? and kind_no = ? and good_no!=? and isdelete = 0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setInt(2,beanGoods.getKind_no());
			pst.setInt(3, beanGoods.getGood_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("商品已经存在！");
			
			sql="update goods set good_name=?,good_price=?,good_sale=? where good_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(4,beanGoods.getGood_no());
			pst.setString(1, name);
			pst.setDouble(2, p1);
			pst.setDouble(3, p2);
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

	public BeanGoods tjgoods( ) throws DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			
			String sql="select goods.good_no,count(*) as count from good_more,goods where goods.good_no=good_more.good_no and"
					+ " goods.isdelete=0 group by goods.good_no order by count desc";
		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int goodno=0;
			
			if(rs.next())
			{
				goodno=rs.getInt(1);
			}
			else {
				sql="select good_no from goods where isdelete=0";
				pst=conn.prepareStatement(sql);
				java.sql.ResultSet rs2=pst.executeQuery();
				 rs2.next();
				goodno=rs2.getInt(1);
				rs2.close();
			}
		
			sql="select * from goods where good_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goodno);
			rs=pst.executeQuery();
			rs.next();
			BeanGoods u=new BeanGoods();
			u.setGood_no(rs.getInt(1));
			u.setGood_name(rs.getString(3));
			u.setKind_no(rs.getInt(2));
			u.setGood_price(rs.getDouble(4));
			u.setGood_sale(rs.getDouble(5));
			
			
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

	public String getshopname(int id) throws DbException {
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shops.shop_name from shops,kinds,goods where shops.shop_no=kinds.shop_no and kinds.kind_no=goods.kind_no and goods.good_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			return rs.getString(1);
		}catch (SQLException e) {
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
