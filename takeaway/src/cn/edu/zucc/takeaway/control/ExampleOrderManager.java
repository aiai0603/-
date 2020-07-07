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
import cn.edu.zucc.takeaway.model.BeanRider;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;



public class ExampleOrderManager  {
	
	/*
	public List<BeanAddresser> loadaddress(String name, int i) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanAddresser> result = new ArrayList<BeanAddresser>();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from addresses where address like ? and user_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,"%"+name+"%");
			pst.setInt(2, i);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanAddresser u=new BeanAddresser();
				u.setAddress_no(rs.getInt(1));
				u.setUser_no(rs.getInt(2));
				u.setSheng(rs.getString(3));
				u.setShi(rs.getString(4));
				u.setAddress(rs.getString(5));
				u.setCall_user(rs.getString(6));
				u.setAddress_tele(rs.getString(7));
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
*/
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
