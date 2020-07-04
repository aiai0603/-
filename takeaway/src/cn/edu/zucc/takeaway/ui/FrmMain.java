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

import cn.edu.zucc.takeaway.model.BeanAdmin;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.ui.FrmLogin;





public class FrmMain extends JFrame implements ActionListener {
	public static int uskind=0;
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_user=new JMenu("用户管理");
    private JMenu menu_step=new JMenu("步骤管理");
    private JMenu menu_static=new JMenu("查询统计");
    private JMenu menu_more=new JMenu("更多");
    
    private JMenuItem  menuItem_Deleteuser=new JMenuItem("删除用户");
    private JMenuItem  menuItem_listuser=new JMenuItem("统计用户");
    private JMenuItem  menuItem_AddStep=new JMenuItem("添加步骤");
    private JMenuItem  menuItem_DeleteStep=new JMenuItem("删除步骤");
    private JMenuItem  menuItem_startStep=new JMenuItem("开始步骤");
    private JMenuItem  menuItem_finishStep=new JMenuItem("结束步骤");
    private JMenuItem  menuItem_moveUpStep=new JMenuItem("步骤上移");
    private JMenuItem  menuItem_moveDownStep=new JMenuItem("步骤下移");
    
    private JMenuItem  menuItem_modifyPwd=new JMenuItem("密码修改");
    private JMenuItem  menuItem_AddAdmin=new JMenuItem("新增管理员");
    
    private JMenuItem  menuItem_static1=new JMenuItem("统计1");
    
    
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	private JLabel label=new JLabel();
	
	private JMenuBar menubar2=new JMenuBar(); ;
	private JMenu menu_xx=new JMenu("信息管理");
	private JMenuItem  menuItem_change=new JMenuItem("账号信息");
	private JMenuItem  menuItem_vip=new JMenuItem("vip管理");
	

	
	public FrmMain(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		FrmLoading dlgLogin = new FrmLoading(this,"外卖助手",true);
		dlgLogin.setVisible(true);
		
	
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
		    this.menu_step.add(this.menuItem_AddStep); this.menuItem_AddStep.addActionListener(this);
		    this.menu_step.add(this.menuItem_DeleteStep); this.menuItem_DeleteStep.addActionListener(this);
		    this.menu_step.add(this.menuItem_startStep); this.menuItem_startStep.addActionListener(this);
		    this.menu_step.add(this.menuItem_finishStep); this.menuItem_finishStep.addActionListener(this);
		    this.menu_step.add(this.menuItem_moveUpStep); this.menuItem_moveUpStep.addActionListener(this);
		    this.menu_step.add(this.menuItem_moveDownStep); this.menuItem_moveDownStep.addActionListener(this);
		    this.menu_static.add(this.menuItem_static1); this.menuItem_static1.addActionListener(this);
		    this.menu_more.add(this.menuItem_modifyPwd); this.menuItem_modifyPwd.addActionListener(this);
		    this.menu_more.add(this.menuItem_AddAdmin);this.menuItem_AddAdmin.addActionListener(this);
		    menubar.add(menu_user);
		    menubar.add(menu_step);
		    menubar.add(menu_static);
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
		
			
			
		}
		
	}

}
