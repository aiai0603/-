package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeaway.control.ExampleUserManager;
import cn.edu.zucc.takeaway.model.BeanAdmin;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.ui.FrmLogin;
import cn.edu.zucc.takeaway.util.BaseException;





public class FrmMain extends JFrame implements ActionListener {
	public static int uskind=0;
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_user=new JMenu("用户管理");
    private JMenu menu_shop=new JMenu("商家管理");
    private JMenu menu_ride=new JMenu("骑手管理");
    private JMenu menu_more=new JMenu("更多");
    
    private JMenuItem  menuItem_Deleteuser=new JMenuItem("管理信息");
    private JMenuItem  menuItem_listuser=new JMenuItem("统计数据");
    private JMenuItem  menuItem_AddShop=new JMenuItem("商家信息");
    private JMenuItem  menuItem_AddYouhui=new JMenuItem("商家优惠");
    private JMenuItem  menuItem_AddCount=new JMenuItem("商家满减");
    private JMenuItem  menuItem_RideAdd=new JMenuItem("骑手操作");
    private JMenuItem  menuItem_Ridelist=new JMenuItem("统计数据");
    private JMenuItem  menuItem_modifyPwd=new JMenuItem("密码修改");
    private JMenuItem  menuItem_AddAdmin=new JMenuItem("新增管理员");
  
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	private JLabel label=new JLabel();
	
	private JMenuBar menubar2=new JMenuBar(); ;
	private JMenu menu_xx=new JMenu("信息管理");
	private JMenuItem  menuItem_change=new JMenuItem("账号信息");
	private JMenuItem  menuItem_vip=new JMenuItem("vip管理");
	

	
	public FrmMain(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		//FrmLoading dlgLogin = new FrmLoading(this,"外卖助手",true);
		//dlgLogin.setVisible(true);
		try {
			BeanAdmin.currentLoginAdmin= (new ExampleUserManager()).loginAdmin("1" ,"1");
			uskind=2;
		} catch (BaseException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	
		if(uskind==1)
		{
			this.setTitle("用户系统");
			this.menu_xx.add(this.menuItem_change); this.menuItem_change.addActionListener(this);
		    this.menu_xx.add(this.menuItem_vip); this.menuItem_vip.addActionListener(this);
			menubar2.add(menu_xx);
			this.setJMenuBar(menubar2);
		    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		    if(BeanUsers.currentLoginUser.isVip())
		    {
		    	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		    	   label.setText("您好! VIP用户 "+BeanUsers.currentLoginUser.getUser_name()+"     您的会员将在"+f.format(BeanUsers.currentLoginUser.getVip_end())+
		    			   "到期!");
		    	   
				    statusBar.add(label);
				    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		    }else {
		    	
		    	 label.setText("您好! 用户 "+BeanUsers.currentLoginUser.getUser_name()+"        开通会员，尽享优惠");
				 statusBar.add(label);
				 this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		    }
		 
		    this.addWindowListener(new WindowAdapter(){   
		    	public void windowClosing(WindowEvent e){ 
		    		System.exit(0);
	             }
	        });
		    this.setVisible(true);

		}else if(uskind==2)
		{
			this.setTitle("管理系统");
			this.menu_user.add(this.menuItem_Deleteuser); this.menuItem_Deleteuser.addActionListener(this);
			this.menu_user.add(this.menuItem_listuser); this.menuItem_listuser.addActionListener(this);
		    this.menu_shop.add(this.menuItem_AddShop); this.menuItem_AddShop.addActionListener(this);
		    this.menu_shop.add(this.menuItem_AddYouhui); this.menuItem_AddYouhui.addActionListener(this);
		    this.menu_shop.add(this.menuItem_AddCount); this.menuItem_AddCount.addActionListener(this);
		  // this.menu_shop.add(this.menuItem_finishStep); this.menuItem_finishStep.addActionListener(this);
		  //  this.menu_shop.add(this.menuItem_moveUpStep); this.menuItem_moveUpStep.addActionListener(this);
		  //  this.menu_shop.add(this.menuItem_moveDownStep); this.menuItem_moveDownStep.addActionListener(this);
		    this.menu_ride.add(this.menuItem_RideAdd); this.menuItem_RideAdd.addActionListener(this);
		    this.menu_ride.add(this.menuItem_Ridelist); this.menuItem_Ridelist.addActionListener(this);
		
		    this.menu_more.add(this.menuItem_modifyPwd); this.menuItem_modifyPwd.addActionListener(this);
		    this.menu_more.add(this.menuItem_AddAdmin);this.menuItem_AddAdmin.addActionListener(this);
		    menubar.add(menu_user);
		    menubar.add(menu_shop);
		    menubar.add(menu_ride);
		    menubar.add(menu_more);
		    this.setJMenuBar(menubar);
			   statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
			   label.setText("您好！管理员 "+BeanAdmin.currentLoginAdmin.getAdmin_name());
			 statusBar.add(label);
			 this.getContentPane().add(statusBar,BorderLayout.SOUTH);
			 
			  this.addWindowListener(new WindowAdapter(){   
			    	public void windowClosing(WindowEvent e){ 
			    		System.exit(0);
		             }
		        });
			  this.setVisible(true);
		}
				 	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==this.menuItem_AddAdmin){
			FrmRegisterAdmin dlg=new FrmRegisterAdmin(this,"添加管理员",true);
			dlg.setVisible(true);
			
		}
		else if(e.getSource()==this.menuItem_modifyPwd){
			FrmModifyPwd dlg=new FrmModifyPwd(this,"密码修改",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_change){
			FrmModify dlg=new FrmModify(this,"信息修改",true);
			dlg.setVisible(true);
			
			 if(BeanUsers.currentLoginUser.isVip())
			  {
			    	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			    	label.setText("您好! VIP用户 "+BeanUsers.currentLoginUser.getUser_name()+"     您的会员将在"+f.format(BeanUsers.currentLoginUser.getVip_end())+
			    			   "到期!");
			    }else {
			    	
			    	label.setText("您好! 用户 "+BeanUsers.currentLoginUser.getUser_name()+"        开通会员，尽享优惠");
					
			    }
		}
		else if(e.getSource()==this.menuItem_vip){
			FrmVip dlg=new FrmVip(this,"vip",true);
			dlg.setVisible(true);
			
			
			 if(BeanUsers.currentLoginUser.isVip())
			  {
			    	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			    	label.setText("您好! VIP用户 "+BeanUsers.currentLoginUser.getUser_name()+"     您的会员将在"+f.format(BeanUsers.currentLoginUser.getVip_end())+
			    			   "到期!");
			    }else {
			    	
			    	label.setText("您好! 用户 "+BeanUsers.currentLoginUser.getUser_name()+"        开通会员，尽享优惠");
					
			    }
		
			
			
		}	else if(e.getSource()==this.menuItem_Deleteuser){
			FrmDeleteUser dlg=new FrmDeleteUser (this,"用户管理",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_AddShop) {
			FrmShop dlg=new FrmShop(this,"商家管理",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_AddCount) {
			FrmCount dlg=new FrmCount(this,"满减管理",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_AddYouhui) {
			FrmYouhui dlg=new FrmYouhui(this,"优惠管理",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_RideAdd) {
			FrmRide dlg=new FrmRide(this,"骑手管理",true);
			dlg.setVisible(true);
		} 
		
		
	}

}
