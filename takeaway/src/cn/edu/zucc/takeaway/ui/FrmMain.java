package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeaway.control.ExampleOrderManager;
import cn.edu.zucc.takeaway.control.ExampleShopManager;
import cn.edu.zucc.takeaway.control.ExampleUserManager;
import cn.edu.zucc.takeaway.model.BeanAdmin;
import cn.edu.zucc.takeaway.model.BeanCounts;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.ui.FrmLogin;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DbException;





public class FrmMain extends JFrame implements ActionListener {
	public static int uskind=0;
	private static final long serialVersionUID = 1L;
	//管理员系统
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
	
	//用户端
	private JMenuBar menubar2=new JMenuBar(); ;
	private JMenu menu_xx=new JMenu("信息管理");
	private JMenuItem  menuItem_change=new JMenuItem("我的账号");
	private JMenuItem  menuItem_vip=new JMenuItem("我的vip");
	private JMenu menu_order=new JMenu("点餐管理");
	private JMenuItem  menuItem_address=new JMenuItem("我的地址");
	private JMenuItem  menuItem_order=new JMenuItem("我的订单");
	private JPanel toolBar = new JPanel();
	private JTextField edtKeyword = new JTextField(10);
	private JButton btnSearch = new JButton("查询商家");
	private JPanel toolBar2 = new JPanel();
	private JButton btnGo = new JButton("进入商家");
	
	private Object tblShop[]=BeanShops.tableTitles2;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableShop=new JTable(tabShopModel);
	
	private Object tblCount[]=BeanCounts.tableTitles;
	private Object tblCountData[][];
	DefaultTableModel tabCountModel=new DefaultTableModel();
	private JTable dataTableCount=new JTable(tabCountModel);
	
	String sort[]= {"高星级优先","消费最低优先","消费最高优先","销量高优先"};
	JComboBox<String> cb=new JComboBox<String>(sort);
	
	
	List<BeanShops> allshops=null;
	
	private void reloadPlanTable(String name){
		try {
			allshops=(new ExampleShopManager()).loadshop(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allshops.size()][BeanShops.tableTitles2.length];
		for(int i=0;i<allshops.size();i++){
			for(int j=0;j<BeanShops.tableTitles2.length;j++)
				tblShopData[i][j]=allshops.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShop);
		this.dataTableShop.validate();
		this.dataTableShop.repaint();
	}
	
	public FrmMain(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		//FrmLoading dlgLogin = new FrmLoading(this,"外卖助手",true);
		//dlgLogin.setVisible(true);
		try {
			BeanUsers.currentLoginUser= (new ExampleUserManager()).login("1" ,"1");
			uskind=1;
		} catch (BaseException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	
		if(uskind==1)
		{
			toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
			toolBar.add(edtKeyword);
			toolBar.add(btnSearch);
			toolBar.add(cb);
			toolBar.add(btnGo);
			
			this.btnSearch.addActionListener(this);
			this.btnGo.addActionListener(this);
			
			 cb.addItemListener(new ItemListener() {
		            @Override
		            public void itemStateChanged(ItemEvent e) {
		                if (e.getStateChange() == ItemEvent.SELECTED) {
		                   if(e.getItem().equals("高星级优先")) {
		                		try {
		                			allshops=(new ExampleShopManager()).loadshop(edtKeyword.getText());
		                		} catch (BaseException e1) {
		                			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
		                			return;
		                		}
		                		tblShopData =  new Object[allshops.size()][BeanShops.tableTitles2.length];
		                		for(int i=0;i<allshops.size();i++){
		                			for(int j=0;j<BeanShops.tableTitles2.length;j++)
		                				tblShopData[i][j]=allshops.get(i).getCell(j);
		                		}
		                		tabShopModel.setDataVector(tblShopData,tblShop);
		                		dataTableShop.validate();
		                		dataTableShop.repaint();
		                   }
		                else if(e.getItem().equals("消费最低优先")){
		                	try {
	                			allshops=(new ExampleShopManager()).loadshopbyavg1(edtKeyword.getText());
	                		} catch (BaseException e1) {
	                			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
	                			return;
	                		}
	                		tblShopData =  new Object[allshops.size()][BeanShops.tableTitles2.length];
	                		for(int i=0;i<allshops.size();i++){
	                			for(int j=0;j<BeanShops.tableTitles2.length;j++)
	                				tblShopData[i][j]=allshops.get(i).getCell(j);
	                		}
	                		tabShopModel.setDataVector(tblShopData,tblShop);
	                		dataTableShop.validate();
	                		dataTableShop.repaint();
		                   
		                }else if(e.getItem().equals("消费最高优先")){
		                	try {
	                			allshops=(new ExampleShopManager()).loadshopbyavg2(edtKeyword.getText());
	                		} catch (BaseException e1) {
	                			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
	                			return;
	                		}
	                		tblShopData =  new Object[allshops.size()][BeanShops.tableTitles2.length];
	                		for(int i=0;i<allshops.size();i++){
	                			for(int j=0;j<BeanShops.tableTitles2.length;j++)
	                				tblShopData[i][j]=allshops.get(i).getCell(j);
	                		}
	                		tabShopModel.setDataVector(tblShopData,tblShop);
	                		dataTableShop.validate();
	                		dataTableShop.repaint();
		                	
		                   }else if(e.getItem().equals("销量高优先")){
		                	   try {
		                			allshops=(new ExampleShopManager()).loadshopbysum(edtKeyword.getText());
		                		} catch (BaseException e1) {
		                			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
		                			return;
		                		}
		                		tblShopData =  new Object[allshops.size()][BeanShops.tableTitles2.length];
		                		for(int i=0;i<allshops.size();i++){
		                			for(int j=0;j<BeanShops.tableTitles2.length;j++)
		                				tblShopData[i][j]=allshops.get(i).getCell(j);
		                		}
		                		tabShopModel.setDataVector(tblShopData,tblShop);
		                		dataTableShop.validate();
		                		dataTableShop.repaint();
		                }
		            }

		            }
		        });
			 
			 
			this.getContentPane().add(toolBar, BorderLayout.NORTH);
			this.setTitle("用户系统");
			this.menu_xx.add(this.menuItem_change); this.menuItem_change.addActionListener(this);
		    this.menu_xx.add(this.menuItem_vip); this.menuItem_vip.addActionListener(this);
			menubar2.add(menu_xx);
			this.menu_order.add(this.menuItem_address); this.menuItem_address.addActionListener(this);
		    this.menu_order.add(this.menuItem_order); this.menuItem_order.addActionListener(this);
			menubar2.add(menu_order);
			this.setJMenuBar(menubar2);
		    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		    JScrollPane js1=new JScrollPane(this.dataTableShop);
		    js1.setPreferredSize(new Dimension(700, 10));
		    JScrollPane js2=new JScrollPane(this.dataTableCount);
		    js2.setPreferredSize(new Dimension(100, 10));
		   this.getContentPane().add(js1, BorderLayout.WEST);
		   this.getContentPane().add(js2, BorderLayout.CENTER);
		 
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
		    
		    
		    reloadPlanTable(edtKeyword.getText());
		    this.setVisible(true);

		}else if(uskind==2)
		{
			this.setTitle("管理系统");
			this.menu_user.add(this.menuItem_Deleteuser); this.menuItem_Deleteuser.addActionListener(this);
			this.menu_user.add(this.menuItem_listuser); this.menuItem_listuser.addActionListener(this);
		    this.menu_shop.add(this.menuItem_AddShop); this.menuItem_AddShop.addActionListener(this);
		    this.menu_shop.add(this.menuItem_AddYouhui); this.menuItem_AddYouhui.addActionListener(this);
		    this.menu_shop.add(this.menuItem_AddCount); this.menuItem_AddCount.addActionListener(this);
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
		} else if(e.getSource()==this.menuItem_address){
			FrmAddress dlg=new FrmAddress(this,"地址管理",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnSearch) {
			reloadPlanTable(edtKeyword.getText());
			cb.setSelectedItem("高星级优先");
		}else if(e.getSource()==this.btnGo) {
			int i=this.dataTableShop.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int id;
			ExampleOrderManager ex=new ExampleOrderManager();
			try {
				 id=ex.addorder(BeanUsers.currentLoginUser.getUser_no(),allshops.get(i).getShop_no());
			} catch (BaseException e1) {
				// TODO 自动生成的 catch 块
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			} 
			FrmBuy dlg=new FrmBuy(this,allshops.get(i).getShop_name()+"点餐",true,allshops.get(i),id);
			dlg.setVisible(true);
		}
		
		
	}

}
