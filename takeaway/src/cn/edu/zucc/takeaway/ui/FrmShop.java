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
import java.util.List;

import javax.swing.JButton;
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
import cn.edu.zucc.takeaway.control.ExampleKindManager;
import cn.edu.zucc.takeaway.control.ExampleShopManager;
import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DbException;


public class FrmShop extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_shop=new JMenu("商家管理");
    private JMenu menu_kind=new JMenu("类别管理");
    private JMenu menu_good=new JMenu("商品管理");
    private JPanel toolBar = new JPanel();
	private JTextField edtKeyword = new JTextField(10);
	private JButton btnSearch = new JButton("查询商家");
    
    private JMenuItem  menuItem_AddShop=new JMenuItem("新建商家");
    private JMenuItem  menuItem_ModifyShop=new JMenuItem("修改商家");
    private JMenuItem  menuItem_DeleteShop=new JMenuItem("删除商家");
    private JMenuItem  menuItem_AddKind=new JMenuItem("添加类别");
    private JMenuItem  menuItem_ModifyKind=new JMenuItem("修改类别");
    private JMenuItem  menuItem_DeleteKind=new JMenuItem("删除类别");
    private JMenuItem  menuItem_AddGood=new JMenuItem("添加商品");
    private JMenuItem  menuItem_ModifyGood=new JMenuItem("修改商品");
    private JMenuItem  menuItem_DeleteGood=new JMenuItem("删除商品");
   
	private FrmLogin dlgLogin=null;

	
	private Object tblPlanTitle[]=BeanShops.tableTitles;
	private Object tblPlanData[][];
	DefaultTableModel tabPlanModel=new DefaultTableModel();
	private JTable dataTablePlan=new JTable(tabPlanModel);
	
	
	private Object tblStepTitle[]=BeanKind.tableTitles;
	private Object tblStepData[][];
	DefaultTableModel tabStepModel=new DefaultTableModel();
	private JTable dataTableStep=new JTable(tabStepModel);
	
	
	private Object tblGoodTitle[]=BeanGoods.tableTitles;
	private Object tblGoodData[][];
	DefaultTableModel tabGoodModel=new DefaultTableModel();
	private JTable dataTableGood=new JTable(tabGoodModel);
	
	private BeanShops curshop=null;
	private BeanKind curkind=null;
	
	List<BeanShops> allshops=null;
	List<BeanKind> shopkind=null;
	public static List<BeanGoods> good=null;
	
	private void reloadPlanTable(String name){//这是测试数据，需要用实际数替换
		try {
			allshops=(new ExampleShopManager()).loadshop(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPlanData =  new Object[allshops.size()][BeanShops.tableTitles.length];
		for(int i=0;i<allshops.size();i++){
			for(int j=0;j<BeanShops.tableTitles.length;j++)
				tblPlanData[i][j]=allshops.get(i).getCell(j);
		}
		tabPlanModel.setDataVector(tblPlanData,tblPlanTitle);
		this.dataTablePlan.validate();
		this.dataTablePlan.repaint();
	}
	
	private void reloadPlanStepTabel(int planIdx){
		if(planIdx<0) return;
		curshop=allshops.get(planIdx);
		try {
			shopkind=(new ExampleKindManager()).loadkind(curshop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblStepData =new Object[shopkind.size()][BeanKind.tableTitles.length];
		for(int i=0;i<shopkind.size();i++){
			for(int j=0;j<BeanKind.tableTitles.length;j++)
				tblStepData[i][j]=shopkind.get(i).getCell(j);
		}
		
		tabStepModel.setDataVector(tblStepData,tblStepTitle);
		this.dataTableStep.validate();
		this.dataTableStep.repaint();
	} 
	
	private void reloadGoodTabel(int kindIdx){
		if(kindIdx<0) return;
		curkind=shopkind.get(kindIdx);
		try {
			good=(new ExampleGoodManager()).loadgood(curkind);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblGoodData =new Object[good.size()][BeanGoods.tableTitles.length];
		for(int i=0;i<good.size();i++){
			for(int j=0;j<BeanGoods.tableTitles.length;j++)
				tblGoodData[i][j]=good.get(i).getCell(j);
		}
		
		tabGoodModel.setDataVector(tblGoodData,tblGoodTitle);
		this.dataTableGood.validate();
		this.dataTableGood.repaint();
	} 
	
	
	public FrmShop(FrmMain frmMain, String string, boolean b){
		super(string);
	
		
	    //菜单
	    this.menu_shop.add(this.menuItem_AddShop); this.menuItem_AddShop.addActionListener(this);
	    this.menu_shop.add(this.menuItem_ModifyShop); this.menuItem_ModifyShop.addActionListener(this);
	    this.menu_shop.add(this.menuItem_DeleteShop); this.menuItem_DeleteShop.addActionListener(this);
	    
	    this.menu_kind.add(this.menuItem_AddKind); this.menuItem_AddKind.addActionListener(this);
	    this.menu_kind.add(this.menuItem_ModifyKind); this.menuItem_ModifyKind.addActionListener(this);
	    this.menu_kind.add(this.menuItem_DeleteKind); this.menuItem_DeleteKind.addActionListener(this);
	    
	    this.menu_good.add(this.menuItem_AddGood); this.menuItem_AddGood.addActionListener(this);
	    this.menu_good.add(this.menuItem_ModifyGood); this.menuItem_ModifyGood.addActionListener(this);
	    this.menu_good.add(this.menuItem_DeleteGood); this.menuItem_DeleteGood.addActionListener(this);
	    
	    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		
		this.btnSearch.addActionListener(this);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
	
	    
	    menubar.add(menu_shop);
	    menubar.add(menu_kind);
	    menubar.add(menu_good);
	    this.setJMenuBar(menubar);
	    
	    JScrollPane js1=new JScrollPane(this.dataTablePlan);
	    js1.setPreferredSize(new Dimension(400, 10));
	    JScrollPane js2=new JScrollPane(this.dataTableStep);
	    js2.setPreferredSize(new Dimension(100, 10));
	    JScrollPane js3=new JScrollPane(this.dataTableGood);
	    js3.setPreferredSize(new Dimension(800, 10));
	  
	  
	   this.getContentPane().add(js1, BorderLayout.WEST);
	   this.getContentPane().add(js2, BorderLayout.CENTER);
	   this.getContentPane().add(js3, BorderLayout.EAST);
	    
	    this.dataTablePlan.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmShop.this.dataTablePlan.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmShop.this.reloadPlanStepTabel(i);
				good=null;
				tblGoodData=null;
				tabGoodModel.setDataVector(tblGoodData,tblGoodTitle);
				dataTableGood.validate();
				dataTableGood.repaint();
				
				curkind=null;
			}
	    	
	    });
	    
	   
	  
	    this.dataTableStep.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int j=FrmShop.this.dataTableStep.getSelectedRow();
				if(j<0) {
					return;
				}
				FrmShop.this.reloadGoodTabel(j);
			}
	    	
	    });
	   
	    this.reloadPlanTable("");
	    //状态栏
	    this.setExtendedState(Frame.MAXIMIZED_BOTH);
	    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_AddShop){
			FrmAddShop dlg=new FrmAddShop(this,"添加商家",true);
			dlg.setVisible(true);
			reloadPlanTable(this.edtKeyword.getText());
		}
		else if(e.getSource()==this.menuItem_DeleteShop){
			if(this.curshop==null) {
				JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				ExampleShopManager ex=new ExampleShopManager();
				ex.deleteshop(this.curshop);
				JOptionPane.showMessageDialog(null, "删除成功", "成功",JOptionPane.INFORMATION_MESSAGE);
				reloadPlanTable(this.edtKeyword.getText());
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_ModifyShop){
			if(this.curshop==null) {
				JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
				FrmModifyShop dlg=new FrmModifyShop(this,"修改商家信息",true,curshop);
				dlg.setVisible(true);
				reloadPlanTable(this.edtKeyword.getText());	
			
			
		}else if(e.getSource()==this.btnSearch){
			reloadPlanTable(this.edtKeyword.getText());
			
			good=null;
			curkind=null;
			tblGoodData=null;
			tblStepData=null;
			tabGoodModel.setDataVector(tblGoodData,tblGoodTitle);
			dataTableGood.validate();
			dataTableGood.repaint();
			tabStepModel.setDataVector(tblStepData,tblStepTitle);
			this.dataTableStep.validate();
			this.dataTableStep.repaint();
			
		}
		
		else if(e.getSource()==this.menuItem_AddKind){
			if(this.curshop==null) {
				
				JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmAddKind dlg=new FrmAddKind(this,"添加类别",true);
			dlg.shop=curshop;
			dlg.setVisible(true);
			int j=FrmShop.this.dataTablePlan.getSelectedRow();
			if(j<0) {
				return;
			}
			FrmShop.this.reloadPlanStepTabel(j);
			
			
		}
		else if(e.getSource()==this.menuItem_DeleteKind){
			if(this.curkind==null) {
				JOptionPane.showMessageDialog(null, "请选择类别", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				ExampleKindManager ex=new ExampleKindManager();
				ex.deletekind(curkind);
				JOptionPane.showMessageDialog(null, "删除成功", "成功",JOptionPane.INFORMATION_MESSAGE); 
				
				int j=FrmShop.this.dataTablePlan.getSelectedRow();
				if(j<0) {
					return;
				}
				FrmShop.this.reloadPlanStepTabel(j);
				
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_ModifyKind){
			if(this.curkind==null) {
				JOptionPane.showMessageDialog(null, "请选择类别", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmModifyKind dlg=new FrmModifyKind(this,"修改类别",true,curkind);
			dlg.setVisible(true);
			int j=FrmShop.this.dataTablePlan.getSelectedRow();
			if(j<0) {
				return;
			}
			FrmShop.this.reloadPlanStepTabel(j);
			
			
		}else if(e.getSource()==this.menuItem_AddGood){
			if(this.curkind==null) {
				JOptionPane.showMessageDialog(null, "请选择类别", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmAddGood dlg=new FrmAddGood(this,"新增商品",true,curkind);
			dlg.setVisible(true);
			int j=FrmShop.this.dataTableStep.getSelectedRow();
			if(j<0) {
				return;
			}
			FrmShop.this.reloadGoodTabel(j);
			
			
		}else if(e.getSource()==this.menuItem_DeleteGood){
			int i=FrmShop.this.dataTableGood.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				ExampleGoodManager ex=new ExampleGoodManager();
				BeanGoods g=good.get(i);
				ex.deletegood(g);
				JOptionPane.showMessageDialog(null, "删除成功", "成功",JOptionPane.INFORMATION_MESSAGE); 
				
				int j=FrmShop.this.dataTableStep.getSelectedRow();
				if(j<0) {
					return;
				}
				
				FrmShop.this.reloadGoodTabel(j);
				
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
		}else if(e.getSource()==this.menuItem_ModifyGood){
			int i=FrmShop.this.dataTableGood.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmModifyGood dlg=new FrmModifyGood(this,"修改商品",true,good.get(i));
			dlg.setVisible(true);
			int j=FrmShop.this.dataTableStep.getSelectedRow();
			if(j<0) {
				return;
			}
			
			FrmShop.this.reloadGoodTabel(j);
			
			
		}
		
		
}
}
