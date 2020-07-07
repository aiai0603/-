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
	//����Աϵͳ
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_user=new JMenu("�û�����");
    private JMenu menu_shop=new JMenu("�̼ҹ���");
    private JMenu menu_ride=new JMenu("���ֹ���");
    private JMenu menu_more=new JMenu("����");
    
    private JMenuItem  menuItem_Deleteuser=new JMenuItem("������Ϣ");
    private JMenuItem  menuItem_listuser=new JMenuItem("ͳ������");
    private JMenuItem  menuItem_AddShop=new JMenuItem("�̼���Ϣ");
    private JMenuItem  menuItem_AddYouhui=new JMenuItem("�̼��Ż�");
    private JMenuItem  menuItem_AddCount=new JMenuItem("�̼�����");
    private JMenuItem  menuItem_RideAdd=new JMenuItem("���ֲ���");
    private JMenuItem  menuItem_Ridelist=new JMenuItem("ͳ������");
    private JMenuItem  menuItem_modifyPwd=new JMenuItem("�����޸�");
    private JMenuItem  menuItem_AddAdmin=new JMenuItem("��������Ա");
  
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	private JLabel label=new JLabel();
	
	//�û���
	private JMenuBar menubar2=new JMenuBar(); ;
	private JMenu menu_xx=new JMenu("��Ϣ����");
	private JMenuItem  menuItem_change=new JMenuItem("�ҵ��˺�");
	private JMenuItem  menuItem_vip=new JMenuItem("�ҵ�vip");
	private JMenu menu_order=new JMenu("��͹���");
	private JMenuItem  menuItem_address=new JMenuItem("�ҵĵ�ַ");
	private JMenuItem  menuItem_order=new JMenuItem("�ҵĶ���");
	private JPanel toolBar = new JPanel();
	private JTextField edtKeyword = new JTextField(10);
	private JButton btnSearch = new JButton("��ѯ�̼�");
	private JPanel toolBar2 = new JPanel();
	private JButton btnGo = new JButton("�����̼�");
	
	private Object tblShop[]=BeanShops.tableTitles2;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableShop=new JTable(tabShopModel);
	
	private Object tblCount[]=BeanCounts.tableTitles;
	private Object tblCountData[][];
	DefaultTableModel tabCountModel=new DefaultTableModel();
	private JTable dataTableCount=new JTable(tabCountModel);
	
	String sort[]= {"���Ǽ�����","�����������","�����������","����������"};
	JComboBox<String> cb=new JComboBox<String>(sort);
	
	
	List<BeanShops> allshops=null;
	
	private void reloadPlanTable(String name){
		try {
			allshops=(new ExampleShopManager()).loadshop(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
		
		//FrmLoading dlgLogin = new FrmLoading(this,"��������",true);
		//dlgLogin.setVisible(true);
		try {
			BeanUsers.currentLoginUser= (new ExampleUserManager()).login("1" ,"1");
			uskind=1;
		} catch (BaseException e1) {
			// TODO �Զ����ɵ� catch ��
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
		                   if(e.getItem().equals("���Ǽ�����")) {
		                		try {
		                			allshops=(new ExampleShopManager()).loadshop(edtKeyword.getText());
		                		} catch (BaseException e1) {
		                			JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
		                else if(e.getItem().equals("�����������")){
		                	try {
	                			allshops=(new ExampleShopManager()).loadshopbyavg1(edtKeyword.getText());
	                		} catch (BaseException e1) {
	                			JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
		                   
		                }else if(e.getItem().equals("�����������")){
		                	try {
	                			allshops=(new ExampleShopManager()).loadshopbyavg2(edtKeyword.getText());
	                		} catch (BaseException e1) {
	                			JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
		                	
		                   }else if(e.getItem().equals("����������")){
		                	   try {
		                			allshops=(new ExampleShopManager()).loadshopbysum(edtKeyword.getText());
		                		} catch (BaseException e1) {
		                			JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
			this.setTitle("�û�ϵͳ");
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
		    	   label.setText("����! VIP�û� "+BeanUsers.currentLoginUser.getUser_name()+"     ���Ļ�Ա����"+f.format(BeanUsers.currentLoginUser.getVip_end())+
		    			   "����!");
				    statusBar.add(label);
				    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		    }else {
		    	 label.setText("����! �û� "+BeanUsers.currentLoginUser.getUser_name()+"        ��ͨ��Ա�������Ż�");
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
			this.setTitle("����ϵͳ");
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
			   label.setText("���ã�����Ա "+BeanAdmin.currentLoginAdmin.getAdmin_name());
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
		// TODO �Զ����ɵķ������
		if(e.getSource()==this.menuItem_AddAdmin){
			FrmRegisterAdmin dlg=new FrmRegisterAdmin(this,"��ӹ���Ա",true);
			dlg.setVisible(true);
			
		}
		else if(e.getSource()==this.menuItem_modifyPwd){
			FrmModifyPwd dlg=new FrmModifyPwd(this,"�����޸�",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_change){
			FrmModify dlg=new FrmModify(this,"��Ϣ�޸�",true);
			dlg.setVisible(true);
			
			 if(BeanUsers.currentLoginUser.isVip())
			  {
			    	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			    	label.setText("����! VIP�û� "+BeanUsers.currentLoginUser.getUser_name()+"     ���Ļ�Ա����"+f.format(BeanUsers.currentLoginUser.getVip_end())+
			    			   "����!");
			    }else {
			    	
			    	label.setText("����! �û� "+BeanUsers.currentLoginUser.getUser_name()+"        ��ͨ��Ա�������Ż�");
					
			    }
		}
		else if(e.getSource()==this.menuItem_vip){
			FrmVip dlg=new FrmVip(this,"vip",true);
			dlg.setVisible(true);
			
			
			 if(BeanUsers.currentLoginUser.isVip())
			  {
			    	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			    	label.setText("����! VIP�û� "+BeanUsers.currentLoginUser.getUser_name()+"     ���Ļ�Ա����"+f.format(BeanUsers.currentLoginUser.getVip_end())+
			    			   "����!");
			    }else {
			    	
			    	label.setText("����! �û� "+BeanUsers.currentLoginUser.getUser_name()+"        ��ͨ��Ա�������Ż�");
					
			    }
		}	else if(e.getSource()==this.menuItem_Deleteuser){
			FrmDeleteUser dlg=new FrmDeleteUser (this,"�û�����",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_AddShop) {
			FrmShop dlg=new FrmShop(this,"�̼ҹ���",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_AddCount) {
			FrmCount dlg=new FrmCount(this,"��������",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_AddYouhui) {
			FrmYouhui dlg=new FrmYouhui(this,"�Żݹ���",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_RideAdd) {
			FrmRide dlg=new FrmRide(this,"���ֹ���",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.menuItem_address){
			FrmAddress dlg=new FrmAddress(this,"��ַ����",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnSearch) {
			reloadPlanTable(edtKeyword.getText());
			cb.setSelectedItem("���Ǽ�����");
		}else if(e.getSource()==this.btnGo) {
			int i=this.dataTableShop.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ���̼�", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int id;
			ExampleOrderManager ex=new ExampleOrderManager();
			try {
				 id=ex.addorder(BeanUsers.currentLoginUser.getUser_no(),allshops.get(i).getShop_no());
			} catch (BaseException e1) {
				// TODO �Զ����ɵ� catch ��
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			} 
			FrmBuy dlg=new FrmBuy(this,allshops.get(i).getShop_name()+"���",true,allshops.get(i),id);
			dlg.setVisible(true);
		}
		
		
	}

}
