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



public class ExampleAddressManager  {
	
	public List<BeanAddresser> loadaddress(String name, int i) throws DbException{
		
		java.sql.Connection conn=null;
		List<BeanAddresser> result = new ArrayList<BeanAddresser>();
		try {
			conn=DBUtil.getConnection();
		
			String sql="select * from addresses where address like ? and user_no=? and isdelete = 0";
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

	public void addaddress(int id, String sheng, String shi, String add, String call, String tele) throws DbException, BusinessException {
		// TODO �Զ����ɵķ������
		if(add==null || "".equals(add)){
			throw new BusinessException("��ַΪ�գ�");
		}if(call==null || "".equals(call)||call.length()>10){
			throw new BusinessException("�ƺ�Ϊ1-10λ��");
		}if(tele==null || "".equals(tele)||tele.length()!=11){
			throw new BusinessException("�绰Ϊ11λ��");
		}
		for(int i=0;i<tele.length();i++) {
			if(!Character.isDigit(tele.charAt(i))) {
				throw new BusinessException("�绰���зǷ��ַ�");
			}
				
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			
			String sql="insert into addresses(user_no,sheng,shi,address,call_user,address_tele,isdelete) values(?,?,?,?,?,?,0)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,id );
			pst.setString(2, sheng);
			pst.setString(3, shi);
			pst.setString(4, add);
			pst.setString(5, call);
			pst.setString(6, tele);
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

	public void deleteadd(BeanAddresser book) throws DbException {
		// TODO �Զ����ɵķ������
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update addresses set isdelete=1 where address_no=?";
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
		// TODO �Զ����ɵķ������
		if(add==null || "".equals(add)){
			throw new BusinessException("��ַΪ�գ�");
		}if(call==null || "".equals(call)||call.length()>10){
			throw new BusinessException("�ƺ�Ϊ1-10λ��");
		}if(tele==null || "".equals(tele)||tele.length()!=11){
			throw new BusinessException("�绰Ϊ11λ��");
		}
		for(int i=0;i<tele.length();i++) {
			if(!Character.isDigit(tele.charAt(i))) {
				throw new BusinessException("�绰���зǷ��ַ�");
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
	
	
	
}
