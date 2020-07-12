package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;




public class ExampleKindManager {

	public List<BeanKind> loadkind(BeanShops beanshop) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanKind> result = new ArrayList<BeanKind>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from kinds where shop_no = ? and isdelete =0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,beanshop.getShop_no());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanKind u=new BeanKind();
				u.setKind_no(rs.getInt(1));
				u.setKind_name(rs.getString(3));
				u.setShop_no(rs.getInt(2));
				u.setKind_sum(rs.getInt(4));
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
public void addkind(BeanShops bean ,String name) throws DbException, BusinessException {
		
		if(name==null || "".equals(name) || name.length()>20){
			throw new BusinessException("种类必须是1-20个字");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from kinds where kind_name=? and shop_no=? and isdelete =0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setLong(2, bean.getShop_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("类别已经存在！");
			
			sql="insert into kinds(shop_no,kind_name,kind_sum,isdelete) values(?,?,0,0)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, bean.getShop_no());
			pst.setString(2,name);
			
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
    public void deletekind(BeanKind curkind) throws BusinessException, DbException {
	// TODO 自动生成的方法存根
    	java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from goods where kind_no=? and isdelete=0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,curkind.getKind_no());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)!=0)
			{
				throw new BusinessException("该类别仍有设置商品，无法删除！");
			}
			sql="update kinds set isdelete=1 where kind_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, curkind.getKind_no());
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
	public void modifykind(BeanKind kind, String name) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		if(name==null || "".equals(name) || name.length()>20){
			throw new BusinessException("种类必须是1-20个字");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from kinds where kind_name=? and shop_no=? and isdelete =0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setLong(2,kind.getShop_no());
			
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("类别已经存在！");
			
			sql="update kinds set kind_name=? where kind_no=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2,kind.getKind_no());
			
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
	public List<BeanKind> loadhavingkind(BeanShops beanshop) throws DbException {
		java.sql.Connection conn=null;
		List<BeanKind> result = new ArrayList<BeanKind>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from kinds where shop_no = ? and kind_sum!=0 and isdelete=0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,beanshop.getShop_no());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanKind u=new BeanKind();
				u.setKind_no(rs.getInt(1));
				u.setKind_name(rs.getString(3));
				u.setShop_no(rs.getInt(2));
				u.setKind_sum(rs.getInt(4));
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

}
