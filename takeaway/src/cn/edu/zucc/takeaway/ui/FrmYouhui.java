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

import cn.edu.zucc.takeaway.control.ExampleCountManager;
import cn.edu.zucc.takeaway.control.ExampleGoodManager;
import cn.edu.zucc.takeaway.control.ExampleKindManager;
import cn.edu.zucc.takeaway.control.ExampleShopManager;
import cn.edu.zucc.takeaway.control.ExampleYouhuiManager;
import cn.edu.zucc.takeaway.model.BeanCounts;
import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.model.BeanYouHui;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DbException;


public class FrmYouhui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
    private JPanel toolBar = new JPanel();
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("查询商家");
	private Button btnAdd = new Button("添加优惠");
	private Button btnModify = new Button("修改优惠");
	private Button btnDelete = new Button("删除优惠");
	private FrmLogin dlgLogin=null;

	
	private Object tblShop[]=BeanShops.tableTitles;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableShop=new JTable(tabShopModel);
	
	private Object tblYouhui[]=BeanYouHui.tableTitles;
	private Object tblYouhuiData[][];
	DefaultTableModel tabYouhuiModel=new DefaultTableModel();
	private JTable dataTableYouhui=new JTable(tabYouhuiModel);

	private BeanShops curshop=null;
	private BeanCounts curyouhui=null;
	
	List<BeanShops> allshops=null;
	List<BeanYouHui> allyouhui=null;

	private void reloadShopTable(String name){
		try {
			allshops=(new ExampleShopManager()).loadshop(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allshops.size()][BeanShops.tableTitles.length];
		for(int i=0;i<allshops.size();i++){
			for(int j=0;j<BeanShops.tableTitles.length;j++)
				tblShopData[i][j]=allshops.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShop);
		this.dataTableShop.validate();
		this.dataTableShop.repaint();
	}
	
	
	
	private void reloadCountTabel(int id){
		if(id<0) return;
		curshop=allshops.get(id);
		try {
			allyouhui=(new ExampleYouhuiManager()).loadyouhui(curshop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblYouhuiData =new Object[allyouhui.size()][BeanYouHui.tableTitles.length];
		for(int i=0;i<allyouhui.size();i++){
			for(int j=0;j<BeanYouHui.tableTitles.length;j++)
				tblYouhuiData[i][j]=allyouhui.get(i).getCell(j);
		}
		
		tabYouhuiModel.setDataVector(tblYouhuiData,tblYouhui);
		this.dataTableYouhui.validate();
		this.dataTableYouhui.repaint();
	} 
	
	
	public FrmYouhui(FrmMain frmMain, String string, boolean b){
		super(string);
	    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		toolBar.add(btnSearch);
		toolBar.add(edtKeyword);
		toolBar.add(btnAdd);this.btnAdd.addActionListener(this);
		toolBar.add(btnDelete);this.btnDelete.addActionListener(this);
		toolBar.add(btnModify);this.btnModify.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
	    
	    JScrollPane js1=new JScrollPane(this.dataTableShop);
	    js1.setPreferredSize(new Dimension(500, 10));
	    JScrollPane js2=new JScrollPane(this.dataTableYouhui);
	    js2.setPreferredSize(new Dimension(100, 10));
	    
	  
	   this.getContentPane().add(js1, BorderLayout.WEST);
	   this.getContentPane().add(js2, BorderLayout.CENTER);
	  
	    
	   
	   
	    this.dataTableShop.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmYouhui.this.dataTableShop.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmYouhui.this.reloadCountTabel(i);
			}
	    	
	    });
	    
	   
	    this.reloadShopTable("");
	    this.setExtendedState(Frame.MAXIMIZED_BOTH);
	    this.setVisible(true);
	}
	
	@Override
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnSearch){
			reloadShopTable(this.edtKeyword.getText());
			tblShopData=null;
			tabYouhuiModel.setDataVector(tblShopData,tblYouhui);
			dataTableShop.validate();
			dataTableShop.repaint();
		}	
		
		else if(e.getSource()==this.btnAdd){
		
			if(this.curshop==null) {
				JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmAddYouhui dlg=new FrmAddYouhui(this,"添加优惠",true,curshop);
			dlg.setVisible(true);
			int j=FrmYouhui.this.dataTableShop.getSelectedRow();
			if(j<0) {
				return;
			}
			FrmYouhui.this.reloadCountTabel(j);
		}
		else if(e.getSource()==this.btnDelete){
			int i=FrmYouhui.this.dataTableYouhui.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择优惠", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				ExampleYouhuiManager ex=new ExampleYouhuiManager();
				BeanYouHui c=this.allyouhui.get(i);
				ex.deleteyouhui(c);
				JOptionPane.showMessageDialog(null, "删除成功", "成功",JOptionPane.INFORMATION_MESSAGE);
				
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int j=FrmYouhui.this.dataTableShop.getSelectedRow();
			if(j<0) {
				return;
			}
			FrmYouhui.this.reloadCountTabel(j);
		}
		
		else if(e.getSource()==this.btnModify){
			int i=FrmYouhui.this.dataTableYouhui.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择优惠", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
				FrmModifyYouhui dlg=new FrmModifyYouhui(this,"修改商家信息",true,allyouhui.get(i));
				dlg.setVisible(true);
				int j=FrmYouhui.this.dataTableShop.getSelectedRow();
				if(j<0) {
					return;
				}
				FrmYouhui.this.reloadCountTabel(j);
			
		}
		
		
}

}
