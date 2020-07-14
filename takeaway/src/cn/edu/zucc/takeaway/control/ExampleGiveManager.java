package cn.edu.zucc.takeaway.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeaway.model.BeanCounts;
import cn.edu.zucc.takeaway.model.BeanGive;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.model.BeanYouHui;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;




public class ExampleGiveManager {

public List<BeanGive> loadgive(int id,String string) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanGive> result = new ArrayList<BeanGive>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select a.user_no,a.shop_no,a.need,a.already,a.youhui_no,b.shop_name ,c.youhui_sale ,c.endday from give a , "
					+ "shops b,youhui c where a.shop_no=b.shop_no and c.youhui_no = a.youhui_no  and user_no = ? and shop_name like ? and c.isdelete=0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setString(2,"%"+string+"%");
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanGive u=new BeanGive();
				u.setUser_no(rs.getInt(1));
				u.setShop_no(rs.getInt(2));
				u.setYouhui_no(rs.getInt(5));
				u.setNeed(rs.getInt(3));
				u.setAlready(rs.getInt(4));
				u.setShop_name(rs.getString(6));
				u.setYouhui_sale(rs.getDouble(7));
				u.setEndtime(rs.getTimestamp(8));
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
	public void addgive(int id,int id3) throws BusinessException, DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select * from orders where order_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id3);
			java.sql.ResultSet rsshop=pst.executeQuery();
			rsshop.next();
			int id2=rsshop.getInt(5);
			
			sql="select * from give,youhui where give.user_no=? and youhui.shop_no=? and give.youhui_no=youhui.youhui_no and youhui.isdelete=0";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, id2);
			java.sql.ResultSet rs=pst.executeQuery();
			
		
			sql="select * from youhui where shop_no=? and youhui_no not in (select youhui_no from give where user_no=?) and isdelete=0";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id2);
			pst.setInt(2, id);
			java.sql.ResultSet rs22=pst.executeQuery();
			
			while(rs22.next()) {
				if(rs22.getTimestamp(5).getTime()<System.currentTimeMillis()&&rs22.getTimestamp(6).getTime()>System.currentTimeMillis())
				{
					if(rs22.getInt(4)==1)
					{
						sql="select * from owner_count where user_no=? and youhui_no=?";
						pst=conn.prepareStatement(sql);
						pst.setInt(1, id);
						pst.setInt(2, rs22.getInt(1));
						java.sql.ResultSet rs5=pst.executeQuery();
						
						if(rs5.next())
						{
							sql="update owner_count set num=num+1  where user_no=? and youhui_no=?";
							pst=conn.prepareStatement(sql);
							pst.setInt(1, id);
							pst.setInt(2, rs5.getInt(1));
							pst.execute();
						}
						else
						{
							sql="insert into owner_count values(?,?,?,1,?,?)";
							pst=conn.prepareStatement(sql);
							pst.setInt(1, rs22.getInt(1));
							pst.setInt(2, id);
							pst.setDouble(3, rs22.getDouble(3));
							pst.setTimestamp(4, rs22.getTimestamp(6));
							pst.setInt(5, rs22.getInt(2));
							pst.execute();
						}
						
						
					}else {
						sql="insert into give values(?,?,?,1,?)";
						pst=conn.prepareStatement(sql);
						pst.setInt(1, id);
						pst.setInt(2, id2);
						pst.setDouble(3, rs22.getInt(4));
						pst.setInt(4, rs22.getInt(1));
						pst.execute();
					}
				}
			}
				
			while(rs.next())
			{
				if(rs.getTimestamp(10).getTime()<System.currentTimeMillis()&&rs.getTimestamp(11).getTime()>System.currentTimeMillis())
				{
					if(rs.getInt(4)+1!=rs.getInt(3))
					{
						sql="update give set already=already+1  where user_no=? and youhui_no=?";
						pst=conn.prepareStatement(sql);
						pst.setInt(1, id);
						pst.setInt(2, rs.getInt(5));
						pst.execute();
						
					}
					else
					{
						sql="select * from owner_count where user_no=? and youhui_no=?";
						pst=conn.prepareStatement(sql);
						pst.setInt(1, id);
						pst.setInt(2, rs.getInt(5));
						java.sql.ResultSet rs2=pst.executeQuery();
						if(rs2.next())
						{
							sql="update owner_count set num=num+1  where user_no=? and youhui_no=?";
							pst=conn.prepareStatement(sql);
							pst.setInt(1, id);
							pst.setInt(2, rs.getInt(5));
							pst.execute();
						}
						else 
						{
						
							sql="select * from youhui where youhui_no=? and isdelete=0";
							pst=conn.prepareStatement(sql);
							pst.setInt(1, rs.getInt(5));
							java.sql.ResultSet rs3=pst.executeQuery();
							if(rs3.next()) {
							
							sql="insert into owner_count values(?,?,?,1,?,?)";
							pst=conn.prepareStatement(sql);
							pst.setInt(1, rs.getInt(5));
							pst.setInt(2, id);
							pst.setDouble(3, rs3.getInt(3));
							pst.setTimestamp(4, rs3.getTimestamp(6));
							pst.setInt(5, id2);
							pst.execute();
							rs3.close();
							}
						}
						sql="delete from give where user_no=? and youhui_no=?";
						pst=conn.prepareStatement(sql);
						pst.setInt(1, id);
						pst.setInt(2, rs.getInt(5));
						rs2.close();
						pst.execute();
					}
					
				}
				
			}
			rs.close();
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
