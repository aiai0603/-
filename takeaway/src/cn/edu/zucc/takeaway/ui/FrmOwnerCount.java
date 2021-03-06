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

import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.DbException;
import cn.edu.zucc.takeaway.control.*;




public class FrmOwnerCount extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JTextField edtKeyword = new JTextField(10);
	public static BeanUsers book;
	private Button btnSearch = new Button("按店家查询");
	private Object tblTitle[]={"店家","优惠价格","截止日期","数量","能否与满减叠加"};
	private Object tblData[][];
	List<BeanOwnerCount> you=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			ExampleOwnerCountManager ex=new ExampleOwnerCountManager();
			you=ex.loadallowner(BeanUsers.currentLoginUser.getUser_no(),edtKeyword.getText());
			tblData =new Object[you.size()][5];
			for(int i=0;i<you.size();i++){
				tblData[i][0]=you.get(i).getShop_name();
				tblData[i][1]=you.get(i).getCount_money();
				if((you.get(i).getEnd_date().getTime())>(System.currentTimeMillis()))
				tblData[i][2]=(String)f.format(you.get(i).getEnd_date());
				else
				tblData[i][2]="已经过期";
				tblData[i][3]=you.get(i).getNum();
				tblData[i][4]=you.get(i).getTogether();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FrmOwnerCount (Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
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
		this.btnSearch.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnSearch){
			this.reloadTable();
		}
		
	}
}

