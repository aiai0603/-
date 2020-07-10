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

import cn.edu.zucc.takeaway.model.BeanRider;
import cn.edu.zucc.takeaway.model.BeanRiderIncome;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;



public class ExampleRideIncomeManager  {
	
public List<BeanRiderIncome> loadriderincome(BeanRider br) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanRiderIncome> result = new ArrayList<BeanRiderIncome>();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from rider_income where rider_no = ? and MONTH(rider_income_date)=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,br.getRider_no());
			 Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			pst.setInt(2, month);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanRiderIncome u=new BeanRiderIncome();
				u.setOrder_no(rs.getInt(1));
				u.setRider_income_date(rs.getTimestamp(2));
				u.setRider_comment(rs.getInt(3));
				u.setIncome(rs.getDouble(4));
				u.setRider_no(rs.getInt(5));
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

	public int countrider(int rider_no) throws DbException {
	// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from rider_income where rider_no = ? and MONTH(rider_income_date)=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,rider_no);
			 Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			pst.setInt(2, month);
			java.sql.ResultSet rs=pst.executeQuery();
			int count=0;
			if(rs.next())
			{
				count=rs.getInt(1);
			}
			rs.close();
			pst.close();
			return count;
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

	public double sumrider(int rider_no) throws DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from rider_income where rider_no = ? and MONTH(rider_income_date)=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,rider_no);
			 Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			pst.setInt(2, month);
			java.sql.ResultSet rs=pst.executeQuery();
			double count=0;
			while(rs.next())
			{
				count+=rs.getDouble(4);
			}
			rs.close();
			pst.close();
			return count;
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
	
	public void addride(String name) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		if(name==null || "".equals(name) || name.length()>10){
			throw new BusinessException("骑手必须是1-10个字");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from rider where rider_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("骑手已经存在！");
			
			sql="insert into rider(rider_name,rider_start,rider_level,rider_site) values(?,?,1,0)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
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


	public void modifyrider(int id, String name) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		if(name==null || "".equals(name) || name.length()>10){
			throw new BusinessException("骑手名必须是1-10个字");
		}
		try {
			conn=DBUtil.getConnection();
			String sql="select * from rider where rider_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("骑手已经存在！");
			
			sql="update rider set rider_name=? where rider_no=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setLong(2,id);
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
	*/
	
}
