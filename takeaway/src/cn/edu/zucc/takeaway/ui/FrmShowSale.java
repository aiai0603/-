package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
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

import cn.edu.zucc.takeaway.model.BeanCounts;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.model.BeanYouHui;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.DbException;
import cn.edu.zucc.takeaway.control.*;




public class FrmShowSale extends JDialog implements ActionListener {
	private Object tblCount[]=BeanCounts.tableTitles;
	private Object tblCountData[][];
	DefaultTableModel tabCountModel=new DefaultTableModel();
	private JTable dataTableCount=new JTable(tabCountModel);
	
	private Object tblYouhui[]=BeanYouHui.tableTitles2;
	private Object tblYouhuiData[][];
	DefaultTableModel tabYouhuiModel=new DefaultTableModel();
	private JTable dataTableYouhui=new JTable(tabYouhuiModel);
	
	List<BeanCounts> allcount=null;
	BeanShops curshop=null;
	List<BeanYouHui> allyouhui;
	private JLabel count=new JLabel("                                    集单送券活动");
	private JLabel youhui=new JLabel("               满减活动");
	private JPanel toolBar = new JPanel();
	private void reloadYouhuiTabel(){
		
		try {
			allyouhui=(new ExampleYouhuiManager()).loadyouhui2(curshop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		tblYouhuiData =new Object[allyouhui.size()][BeanYouHui.tableTitles2.length];
		for(int i=0;i<allyouhui.size();i++){
				tblYouhuiData[i][0]=allyouhui.get(i).getRequest();
				tblYouhuiData[i][1]=allyouhui.get(i).getYouhui_sale();
				tblYouhuiData[i][2]=f.format(allyouhui.get(i).getEndday());
		}
		
		tabYouhuiModel.setDataVector(tblYouhuiData,tblYouhui);
		this.dataTableYouhui.validate();
		this.dataTableYouhui.repaint();
	} 
	
	
	
	private void reloadCountTabel(){
		
		
		
		try {
			allcount=(new ExampleCountManager()).loadcount(curshop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCountData =new Object[allcount.size()][BeanCounts.tableTitles.length];
		for(int i=0;i<allcount.size();i++){
			for(int j=0;j<BeanCounts.tableTitles.length;j++)
				tblCountData[i][j]=allcount.get(i).getCell(j);
		}
		
		tabCountModel.setDataVector(tblCountData,tblCount);
		this.dataTableCount.validate();
		this.dataTableCount.repaint();
	} 
	

	public FrmShowSale (Frame f, String s, boolean b, BeanShops curshop) {
		super(f, s, b);
		this.curshop=curshop;
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(youhui);
		youhui.setFont(new Font (Font.DIALOG, Font.CENTER_BASELINE, 24));
		toolBar.add(count);
		count.setFont(new Font (Font.DIALOG, Font.CENTER_BASELINE, 24));
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		  JScrollPane js2=new JScrollPane(this.dataTableCount);
		    js2.setPreferredSize(new Dimension(100, 10));
		    JScrollPane js3=new JScrollPane(this.dataTableYouhui);
		    js3.setPreferredSize(new Dimension(500, 10));
		   this.getContentPane().add(js2, BorderLayout.CENTER);
		   this.getContentPane().add(js3, BorderLayout.EAST);
		   reloadCountTabel();
		   reloadYouhuiTabel();
			this.setSize(800, 600);
			double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
			this.setLocation((int) (width - this.getWidth()) / 2,
					(int) (height - this.getHeight()) / 2);

			this.validate();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}


}

