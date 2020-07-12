package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeaway.model.BeanComment;
import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanRider;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;



public class ExampleCommitManager  {
	
	public List<BeanComment> loadcommet(BeanGoods good) throws DbException{
		java.sql.Connection conn=null;
		List<BeanComment> result = new ArrayList<BeanComment>();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from comment,users where comment.user_no=users.user_no and good_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, good.getGood_no());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanComment u=new BeanComment();
				u.setShop_no(rs.getInt(1));
				u.setUser_no(rs.getInt(2));
				u.setComment_word(rs.getString(3));
				u.setComment_date(rs.getTimestamp(4));
				u.setCommnet_level(rs.getInt(5));
				u.setShop_no(rs.getInt(6));
				u.setUser_name(rs.getString(8));
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

	public boolean tocomment(BeanGoods good, BeanUsers User) throws DbException {
		java.sql.Connection conn=null;
		List<BeanComment> result = new ArrayList<BeanComment>();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from good_more,orders where orders.order_no=good_more.order_no "
					+ "and good_more.good_no=? and orders.user_no=? and orders.site=5";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, good.getGood_no());
			pst.setInt(2, User.getUser_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) return true;
		
			rs.close();
			pst.close();
			return false;
		
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
	public void addcomment(BeanGoods goods, BeanUsers User, String name, int star, BeanShops shop) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		if(name==null || "".equals(name) || name.length()>25){
			throw new BusinessException("评论必须是1-25个字");
		}
		java.sql.Connection conn=null;
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from comment where good_no=? and user_no=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,goods.getGood_no());
			pst.setInt(2, User.getUser_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("你已经评价过了！");
			
			
			sql="insert into comment values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,goods.getGood_no());
			pst.setInt(2, User.getUser_no());
			pst.setString(3, name);
			pst.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setInt(5, star+1);
			pst.setInt(6, shop.getShop_no());
			pst.execute();
			pst.close();
			int shopid=shop.getShop_no();
			
			sql="select * from comment where shop_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,shopid);
			rs=pst.executeQuery();
			double sum=0;
			int count=0;
			while(rs.next()) {
				sum=sum+rs.getInt(5);
				count++;
			}
			sum+=5;
			count+=1;
			sql="update shops set level=? where shop_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(2,shopid);
			pst.setInt(1, (int)sum/count);
			
			
			rs.close();
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

	public void deletecommit(BeanGoods goods, BeanUsers User) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn=DBUtil.getConnection();
			String sql="select * from comment where good_no=? and user_no=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,goods.getGood_no());
			pst.setInt(2, User.getUser_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())throw new BusinessException("你还没有评价过该商品！");
			
			int shop=rs.getInt(6);
		
	
			sql="delete from comment where good_no=? and user_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods.getGood_no());
			pst.setInt(2, User.getUser_no());
			pst.execute();
			pst.close();
			
			sql="select * from comment where shop_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,shop);
			rs=pst.executeQuery();
			double sum=0;
			int count=0;
			while(rs.next()) {
				sum=sum+rs.getInt(5);
				count++;
			}
			
			sum+=5;
			count+=1;
			sql="update shops set level=? where shop_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(2,shop);
			pst.setInt(1, (int)sum/count);
			
			rs.close();
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
	

