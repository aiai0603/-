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
			String sql="select * from goods where kind_no = ?";
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
		
		if(name==null || "".equals(name) || name.length()>20){
			throw new BusinessException("商品名必须是1-20个字！");
		}
		if(p2>p1) {
			throw new BusinessException("优惠价不得高于初始价！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goods where good_name=? and kind_no = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setInt(2,kind.getKind_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("商品已经存在！");
			
			sql="insert into goods(kind_no,good_name,good_price,good_sale) values(?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,kind.getKind_no());
			pst.setString(2, name);
			pst.setDouble(3, p1);
			pst.setDouble(4, p2);
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

	public void deletegood(BeanGoods beanGoods) throws DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from goods where good_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, beanGoods.getGood_no());
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

}
