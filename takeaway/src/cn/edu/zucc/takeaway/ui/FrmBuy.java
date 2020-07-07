package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import cn.edu.zucc.takeaway.control.ExampleGoodMoreManager;
import cn.edu.zucc.takeaway.control.ExampleKindManager;
import cn.edu.zucc.takeaway.control.ExampleShopManager;
import cn.edu.zucc.takeaway.model.BeanGoodMore;
import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DbException;


public class FrmBuy extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
   
	private FrmLogin dlgLogin=null;
	private JPanel title = new JPanel();
	private JLabel gwc =new JLabel("                ���ﳵ");
	private JLabel kind =new JLabel("                                    ��Ʒ���");
	private JLabel goods =new JLabel("                            ��Ʒ                ");
	
	private JPanel toolBar = new JPanel();
	private JButton btnBuy = new JButton("���빺�ﳵ");
	private JButton btnUnbuy = new JButton("ɾ����Ʒ");
	private JButton btnChange = new JButton("�޸�����");
	private JButton btnCount = new JButton("���㶩��");
	private JButton btnexit = new JButton("�˳��̼�");
	private JButton btnSee = new JButton("�鿴����");
	
	private Object tblbuyTitle[]=BeanGoodMore.tableTitles;
	private Object tblbuyData[][];
	DefaultTableModel tabbuyModel=new DefaultTableModel();
	private JTable dataTablebuy=new JTable(tabbuyModel);
	
	
	private Object tblKindTitle[]=BeanKind.tableTitles2;
	private Object tblKindData[][];
	DefaultTableModel tabKindModel=new DefaultTableModel();
	private JTable dataTableKind=new JTable(tabKindModel);
	
	
	private Object tblGoodTitle[]=BeanGoods.tableTitles;
	private Object tblGoodData[][];
	DefaultTableModel tabGoodModel=new DefaultTableModel();
	private JTable dataTableGood=new JTable(tabGoodModel);
	
	private BeanShops curshop=null;
	private BeanKind curkind=null;
	
	List<BeanShops> allshops=null;
	List<BeanKind> shopkind=null;
	
	List<BeanGoodMore> goodmore=null;
	List<BeanGoods> good=null;
	private int orderid;
	private void reloadbuyTabel(){
		try {
			goodmore=(new ExampleGoodMoreManager()).loadgoodmore(orderid);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblbuyData =new Object[goodmore.size()][BeanGoodMore.tableTitles.length];
		for(int i=0;i<goodmore.size();i++){
			for(int j=0;j<BeanGoodMore.tableTitles.length;j++)
				tblbuyData[i][j]=goodmore.get(i).getCell(j);
		}
		
		tabbuyModel.setDataVector(tblbuyData,tblbuyTitle);
		this.dataTablebuy.validate();
		this.dataTablebuy.repaint();
	} 
	
	private void reloadPlanStepTabel(BeanShops bs){
		if(bs==null) return;
		curshop=bs;
		try {
			shopkind=(new ExampleKindManager()).loadhavingkind(curshop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblKindData =new Object[shopkind.size()][BeanKind.tableTitles2.length];
		for(int i=0;i<shopkind.size();i++){
			for(int j=0;j<BeanKind.tableTitles2.length;j++)
				tblKindData[i][j]=shopkind.get(i).getCell(j);
		}
		
		tabKindModel.setDataVector(tblKindData,tblKindTitle);
		this.dataTableKind.validate();
		this.dataTableKind.repaint();
	} 
	
	private void reloadGoodTabel(int kindIdx){
		if(kindIdx<0) return;
		curkind=shopkind.get(kindIdx);
		try {
			good=(new ExampleGoodManager()).loadgood(curkind);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
	
	
	public FrmBuy(FrmMain frmMain, String string, boolean b, BeanShops beanShops, int id){
		super(string);
			orderid=id;
		   toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		 
		   toolBar.add(btnUnbuy);
		   toolBar.add(btnChange);
		   toolBar.add(btnCount);
		   toolBar.add(btnexit);
			this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
			
			  title.setLayout(new FlowLayout(FlowLayout.LEFT));
			  title.add(gwc);
			  gwc.setFont(new Font (Font.DIALOG, Font.BOLD, 30));
			  title.add(kind);
			  kind.setFont(new Font (Font.DIALOG, Font.BOLD, 30));
			  title.add(goods);
			  goods.setFont(new Font (Font.DIALOG, Font.BOLD, 30));
			  title.add(btnBuy);
			
			  btnBuy.setFont(new Font (Font.DIALOG, Font.BOLD, 15));
			  title.add(btnSee);
			  btnSee.setFont(new Font (Font.DIALOG, Font.BOLD, 15));
			  this.getContentPane().add(title, BorderLayout.NORTH);
	    //�˵�
	 
	    
	    JScrollPane js1=new JScrollPane(this.dataTablebuy);
	    js1.setPreferredSize(new Dimension(400, 10));
	    JScrollPane js2=new JScrollPane(this.dataTableKind);
	    js2.setPreferredSize(new Dimension(100, 10));
	    JScrollPane js3=new JScrollPane(this.dataTableGood);
	    js3.setPreferredSize(new Dimension(700, 10));
	  
	  
	   this.getContentPane().add(js1, BorderLayout.WEST);
	   this.getContentPane().add(js2, BorderLayout.CENTER);
	   this.getContentPane().add(js3, BorderLayout.EAST);
	 
	   this.btnBuy.addActionListener(this);
	   this.btnUnbuy.addActionListener(this);
	   this.btnChange.addActionListener(this);
	  
	    this.dataTableKind.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int j=FrmBuy.this.dataTableKind.getSelectedRow();
				if(j<0) {
					return;
				}
				FrmBuy.this.reloadGoodTabel(j);
			}
	    });
	    reloadPlanStepTabel(beanShops);
	    reloadGoodTabel(0);
	    reloadbuyTabel();
	    //״̬��
	    this.setExtendedState(Frame.MAXIMIZED_BOTH);
	    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnBuy)
		{
			int j=FrmBuy.this.dataTableGood.getSelectedRow();
			if(j<0) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmCountGood dlg=new FrmCountGood(this,"ѡ������",true,orderid,good.get(j));
			dlg.setVisible(true);
			
			reloadbuyTabel();
		
		}else if(e.getSource()==this.btnUnbuy){
			int j=FrmBuy.this.dataTablebuy.getSelectedRow();
			if(j<0) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			ExampleGoodMoreManager ex =new ExampleGoodMoreManager();
			try {
				ex.deleteordermore(goodmore.get(j));
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			} 
			JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "�ɹ�",JOptionPane.INFORMATION_MESSAGE);
			reloadbuyTabel();
		
		}else if(e.getSource()==this.btnChange){
			int j=FrmBuy.this.dataTablebuy.getSelectedRow();
			if(j<0) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmCountModify dlg=new FrmCountModify(this,"�޸�����",true,orderid,goodmore.get(j));
			dlg.setVisible(true);
			reloadbuyTabel();
		
		}
		
    }
}
