package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
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

import cn.edu.zucc.takeaway.control.ExampleGoodManager;
import cn.edu.zucc.takeaway.control.ExampleGoodMoreManager;
import cn.edu.zucc.takeaway.control.ExampleKindManager;
import cn.edu.zucc.takeaway.control.ExampleOrderManager;
import cn.edu.zucc.takeaway.control.ExampleRideIncomeManager;
import cn.edu.zucc.takeaway.control.ExampleRideManager;
import cn.edu.zucc.takeaway.control.ExampleShopManager;
import cn.edu.zucc.takeaway.control.ExampleUserManager;
import cn.edu.zucc.takeaway.model.BeanGoodMore;
import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanOrders;
import cn.edu.zucc.takeaway.model.BeanRider;
import cn.edu.zucc.takeaway.model.BeanRiderIncome;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DbException;


public class FrmListRider extends JFrame implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JTextField edtKeyword = new JTextField(10);
	private JButton btnSearch = new JButton("查询骑手");
		
	private Object tblUserTitle[]= {"骑手名","接单数","总收入"};
	private Object tblUserData[][];
	DefaultTableModel tabUserModel=new DefaultTableModel();
	private JTable dataTableUser=new JTable(tabUserModel);
	
	private Object tblOrderTitle[]= {"送达时间","用户评价","该单收入"};
	private Object tblOrderData[][];
	DefaultTableModel tabOrderModel=new DefaultTableModel();
	private JTable dataTableOrder=new JTable(tabOrderModel);

	
	List<BeanRider> alluser=null;
	List<BeanRiderIncome> allorder=null;
	BeanRider user;
	
	private void reloadUserTable(){//这是测试数据，需要用实际数替换
		try {
			alluser=(new ExampleRideManager()).loadrider(edtKeyword.getText());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblUserData =  new Object[alluser.size()][tblUserTitle.length];
		for(int i=0;i<alluser.size();i++){
			for(int j=0;j<tblUserTitle.length;j++)
			{
				tblUserData[i][0]=alluser.get(i).getRider_name();
				try {
					tblUserData[i][1]=(new ExampleRideIncomeManager()).countrider(alluser.get(i).getRider_no());
				} catch (DbException e) {
					// TODO 自动生成的 catch 块4
					e.printStackTrace();
				}
				try {
					tblUserData[i][2]=(new ExampleRideIncomeManager()).sumrider(alluser.get(i).getRider_no());
				} catch (DbException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
		tabUserModel.setDataVector(tblUserData,tblUserTitle);
		this.dataTableUser.validate();
		this.dataTableUser.repaint();
	}
	
	private void reloadOrderTabel(int planIdx){
		if(planIdx<0) return;
		user=alluser.get(planIdx);
		try {
			allorder=(new ExampleRideIncomeManager()).loadriderincome(user);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblOrderData =new Object[allorder.size()][tblOrderTitle.length];
		for(int i=0;i<allorder.size();i++){
			for(int j=0;j<tblOrderTitle.length;j++)
				{
				SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					tblOrderData[i][0]=f.format(allorder.get(i).getRider_income_date());
					if(allorder.get(i).getRider_comment()==0)
					tblOrderData[i][1]="暂未评价";
					else if(allorder.get(i).getRider_comment()==1)
					tblOrderData[i][1]="好评";
					else if(allorder.get(i).getRider_comment()==2)
					tblOrderData[i][1]="差评";
					tblOrderData[i][2]=allorder.get(i).getIncome();
				}
		}
		tabOrderModel.setDataVector(tblOrderData,tblOrderTitle);
		this.dataTableOrder.validate();
		this.dataTableOrder.repaint();
	} 
	
	
	public FrmListRider(FrmMain frmMain, String string, boolean b){
		super(string);
		  toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
			toolBar.add(edtKeyword);
			toolBar.add(btnSearch);
			this.btnSearch.addActionListener(this);
			this.getContentPane().add(toolBar, BorderLayout.NORTH);
	    //菜单
	    JScrollPane js1=new JScrollPane(this.dataTableUser);
	    js1.setPreferredSize(new Dimension(700, 10));
	    JScrollPane js2=new JScrollPane(this.dataTableOrder);
	    js2.setPreferredSize(new Dimension(500, 10));
	  
	    
	   this.getContentPane().add(js1, BorderLayout.WEST);
	   this.getContentPane().add(js2, BorderLayout.CENTER);
	
	    
	    this.dataTableUser.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmListRider.this.dataTableUser.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmListRider.this.reloadOrderTabel(i);
			}
	    });
	  
	    this.reloadUserTable();
	    //状态栏
	    this.setExtendedState(Frame.MAXIMIZED_BOTH);
	    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnSearch)
		{
			  this.reloadUserTable();
		}
}
}
