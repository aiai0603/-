package cn.edu.zucc.takeaway.control;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
			throw new BusinessException("账号必须是1-20个字");
		}
		if(tele.length()!=11){
			throw new BusinessException("手机号必须是11位");
		}
		if(!email.matches(e)){
			throw new BusinessException("邮箱格式错误");
		}
		if(pwd==null || "".equals(pwd) || pwd.length()>20){
			throw new BusinessException("密码必须是1-20个字");
		}
		if(!pwd.equals(pwd2)){
			throw new BusinessException("两次密码不一致");
		}if(ag==false)
		{
			throw new BusinessException("请同意用户协议!");
		}
		
		java.sql.Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			String sql="select * from users where user_name =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("账号已经存在");
		
			
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

	public void regadmin(String userid, String pwd,String pwd2) throws BaseException {
	
		if(userid==null || "".equals(userid) || userid.length()>20){
			throw new BusinessException("账号必须是1-20个字");
		}
		
		if(pwd==null || "".equals(pwd) || pwd.length()>20){
			throw new BusinessException("密码必须是1-20个字");
		}
		if(!pwd.equals(pwd2)){
			throw new BusinessException("两次密码不一致");
		}
		
		
		java.sql.Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			String sql="select * from admins where admin_name =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("账号已经存在");
			
			sql="insert into admins(admin_name,pwd) values(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd);
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
	public List<BeanUsers> searchUser(String name) throws DbException{
		java.sql.Connection conn=null;
		List<BeanUsers> result = new ArrayList<BeanUsers>();
		try {
			
			conn=DBUtil.getConnection();
			String sql="select user_no,user_name,pwd,sex,email,city,vip,vip_end,tele from users where user_name like ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			String str="%"+name+"%";
			pst.setString(1,str);
			java.sql.ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				BeanUsers u=new BeanUsers();
				u.setUser_no(rs.getInt(1));
				u.setUser_name(rs.getString(2));
				u.setPwd(rs.getString(3));
				u.setSex(rs.getInt(4));
				u.setEmail(rs.getString(5));
				u.setCity(rs.getString(6));
				u.setVip(rs.getBoolean(7));
				u.setTele(rs.getString(9));
				if(u.isVip())
				{
					u.setVip_end(rs.getTimestamp(8));
					if(u.getVip_end().getTime()<System.currentTimeMillis())
					{
						sql="update users set vip=0 where user_no=?";
						pst=conn.prepareStatement(sql);
						pst.setInt(1,u.getUser_no());
						pst.execute();
						u.setVip(false);
					}
				}
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


	public BeanUsers login(String username, String pwd) throws BaseException {
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_no,user_name,pwd,sex,email,city,vip,vip_end,tele from users where user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,username);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BaseException("登陆账号不存在");
			BeanUsers u=new BeanUsers();
			u.setUser_no(rs.getInt(1));
			u.setUser_name(rs.getString(2));
			u.setPwd(rs.getString(3));
			u.setSex(rs.getInt(4));
			u.setEmail(rs.getString(5));
			u.setCity(rs.getString(6));
			u.setVip(rs.getBoolean(7));
			u.setTele(rs.getString(9));
			if(u.isVip())
			{
				u.setVip_end(rs.getTimestamp(8));
				
				
				if(u.getVip_end().getTime()<System.currentTimeMillis())
				{
					sql="update users set vip=0 where user_name=?";
					pst=conn.prepareStatement(sql);
					pst.setString(1,username);
					pst.execute();
					u.setVip(false);
				}
			}
			
			
		
			
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
			if(!rs.next()) throw new BaseException("管理员账号不存在");
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
	public void changePwdAdmin(BeanAdmin admin, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>20) throw new BusinessException("必须为1-20个字符");
		java.sql.Connection conn=null;
		if(!newPwd.equals(newPwd2)){
			throw new BusinessException("两次密码不一致");
		}
		try {
			conn=DBUtil.getConnection();
			String sql="select pwd from admins where admin_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setInt(1,admin.getAdmin_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update admins set pwd=? where admin_no=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2, admin.getAdmin_no());
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
	public void changevip(BeanUsers beanuser,int mode) throws BaseException {
		
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select vip,vip_end from users where user_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, beanuser.getUser_no());
			java.sql.ResultSet rs= pst.executeQuery();
		 	Calendar c = Calendar.getInstance();
		 	while(rs.next())
		 	if(rs.getBoolean(1))
		 	{
		 		 c.setTime(rs.getTimestamp(2));
		 	}
		 	else
		 	{
		 		Date d=new Date(System.currentTimeMillis());
		 		 c.setTime(d);	
		 	}
		   
		    c.add(Calendar.MONTH,mode);
		    java.util.Date date= c.getTime();
		
			
			sql="update users set vip=1,vip_end=? where user_no=?";
			pst=conn.prepareStatement(sql);
			pst.setTimestamp(1,new java.sql.Timestamp(date.getTime()) );
			pst.setInt(2,beanuser.getUser_no());
			rs.close();
			pst.execute();
			pst.close();
			
			
			BeanUsers.currentLoginUser= this.login(beanuser.getUser_name(),beanuser.getPwd());
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
	
	public void modify(int userid,String username, String oldPwd,String newPwd,String newPwd2,String tele,String email,String city,
			int sex) throws BaseException {
		String ex="\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
		if(username==null || "".equals(username) || username.length()>20){
			throw new BusinessException("账号必须是1-20个字");
		}
		if(tele.length()!=11){
			throw new BusinessException("手机号必须是11位");
		}
		if(!email.matches(ex)){
			throw new BusinessException("邮箱格式错误");
		}
		
		
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd))
		{
			newPwd=oldPwd;
		}
		else
		{
			if(newPwd.length()>20) throw new BusinessException("新密码必须为1-20个字符");
			if(!newPwd.equals(newPwd2)){
				throw new BusinessException("两次密码不一致");
			}
		}
		
		java.sql.Connection conn=null;
		
		try {
			conn=DBUtil.getConnection();
			String sql="select pwd from users where user_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setInt(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update users set user_name=?,sex=?,pwd=?,tele=?,email=?,city=? where user_no=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setInt(2, sex);
			pst.setString(3,newPwd);
			pst.setString(4,tele);
			pst.setString(5,email);
			pst.setString(6,city);
			pst.setInt(7, userid);
			pst.execute();
			pst.close();
			
			
			BeanUsers.currentLoginUser= this.login(username, newPwd);
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

	public void deleteUser(BeanUsers book) throws DbException {
		// TODO 自动生成的方法存根
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from users where user_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, book.getUser_no());
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
