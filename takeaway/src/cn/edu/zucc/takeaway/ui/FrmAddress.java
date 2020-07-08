package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
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

import cn.edu.zucc.takeaway.model.BeanAddresser;
import cn.edu.zucc.takeaway.model.BeanRider;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DbException;
import cn.edu.zucc.takeaway.control.*;




public class FrmAddress extends JDialog implements ActionListener {
	public static BeanAddresser book;
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加");
	private Button btnModify = new Button("修改");
	private Button btnDelete = new Button("删除");
	private Button btnOk = new Button("选择");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("按具体地址查询");
	private Object tblTitle[]={"省","市","具体地址","联系人姓名","联系电话"};
	private Object tblData[][];
	List<BeanAddresser> ad=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			ExampleAddressManager ex=new ExampleAddressManager();
			ad=ex.loadaddress(this.edtKeyword.getText(),BeanUsers.currentLoginUser.getUser_no());
			tblData =new Object[ad.size()][5];
			for(int i=0;i<ad.size();i++){
				tblData[i][0]=ad.get(i).getSheng();
				tblData[i][1]=ad.get(i).getShi();
				tblData[i][2]=ad.get(i).getAddress();
				tblData[i][3]=ad.get(i).getCall_user();
				tblData[i][4]=ad.get(i).getAddress_tele();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FrmAddress (Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(btnDelete);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnSearch.addActionListener(this);
		
	}

	public FrmAddress(FrmSum f, String s, boolean b) {
		// TODO 自动生成的构造函数存根
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(btnDelete);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		toolBar.add(btnOk);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.btnOk.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==this.btnModify){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			book=this.ad.get(i);
			FrmModifyAddress dlg=new FrmModifyAddress(this,"信息修改",true, book);
			dlg.setVisible(true);
			this.reloadTable();
			}
			
		else if(e.getSource()==this.btnAdd){
			FrmAddAddress dlg=new FrmAddAddress(this,"添加地址",true);
			dlg.setVisible(true);
			this.reloadTable();
			
		}
		
		else if(e.getSource()==this.btnDelete){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			book=this.ad.get(i);
			ExampleAddressManager ex=new ExampleAddressManager();
			try {
				ex.deleteadd(book);
			}  catch (BaseException e1) {
				// TODO 自动生成的 catch 块
				JOptionPane.showMessageDialog(null, e1.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(null, "删除成功,","成功",JOptionPane.INFORMATION_MESSAGE);
			this.reloadTable();
			
		}
		
		else if(e.getSource()==this.btnSearch){
			this.reloadTable();
		}	
		
		else if(e.getSource()==this.btnOk){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmSum.ad=ad.get(i);
			this.setVisible(false);
			
		}
		
	}
}

