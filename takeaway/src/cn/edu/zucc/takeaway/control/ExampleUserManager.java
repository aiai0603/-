package cn.edu.zucc.takeaway.control;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import cn.edu.zucc.takeaway.model.BeanAdmin;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DBUtil;
import cn.edu.zucc.takeaway.util.DbException;



public class ExampleUserManager  {

	public void reg(String userid, String pwd,String pwd2,String tele,String email,String city,
			int sex,boolean ag) throws BaseException {
		String e="\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
		if(userid==null || "".equals(userid) || userid.length()>20){
			throw new BusinessException("�˺ű�����1-20����");
		}
		if(tele.length()!=11){
			throw new BusinessException("�ֻ��ű�����11λ");
		}
		if(!email.matches(e)){
			throw new BusinessException("�����ʽ����");
		}
		if(pwd==null || "".equals(pwd) || pwd.length()>20){
			throw new BusinessException("���������1-20����");
		}
		if(!pwd.equals(pwd2)){
			throw new BusinessException("�������벻һ��");
		}if(ag==false)
		{
			throw new BusinessException("��ͬ���û�Э��!");
		}
		
		java.sql.Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			String sql="select * from users where user_no =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("�˺��Ѿ�����");
		
			
			sql="insert into users(user_name,sex,pwd,tele,email,city,sign_date,vip) values(?,?"
					+ ",?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setInt(2, sex);
			pst.setString(3,pwd);
			pst.setString(4,tele);
			pst.setString(5,email);
			pst.setString(6,city);
			pst.setTimestamp(7,new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setBoolean(8, false);
			pst.execute();
			
		
			
			rs.close();
			pst.close();
		
		
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DbException(e1);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}

	

	public BeanUsers login(String username, String pwd) throws BaseException {
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_no,user_name,pwd,vip,vip_end from users where user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,username);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BaseException("��½�˺Ų� ����");
			BeanUsers u=new BeanUsers();
			u.setUser_no(rs.getInt(1));
			u.setUser_name(rs.getString(2));
			u.setPwd(rs.getString(3));
			u.setVip(rs.getBoolean(4));
			if(u.isVip())
			u.setVip_end(rs.getTimestamp(5));
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

	
	public BeanAdmin loginAdmin(String username, String pwd) throws BaseException {
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select admin_no,admin_name,pwd from admins where admin_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,username);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BaseException("����Ա�˺Ų�����");
			BeanAdmin u=new BeanAdmin();
			u.setAdmin_no(rs.getInt(1));
			u.setAdmin_name(rs.getString(2));
			u.setPwd(rs.getString(3));
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


	public void changePwd(BeanUsers user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		
		
	}

}
