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




public class FrmChooseYouhui extends JDialog implements ActionListener {
	public static BeanUsers book;
	private JPanel toolBar = new JPanel();
	private Button btnSearch = new Button("选择");
	private Object tblTitle[]={"优惠价格","截止日期","数量"};
	private Object tblData[][];
	List<BeanOwnerCount> you=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private int orderid;
	private boolean choose;
	private void reloadTable(){
		try {
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ExampleOwnerCountManager ex=new ExampleOwnerCountManager();
			you=ex.loadowner(orderid,BeanUsers.currentLoginUser.getUser_no(),choose);
			tblData =new Object[you.size()][3];
			for(int i=0;i<you.size();i++){
				tblData[i][0]=you.get(i).getCount_money();
				tblData[i][1]=(String)f.format(you.get(i).getEnd_date());
				tblData[i][2]=you.get(i).getNum();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public FrmChooseYouhui (FrmSum frmSum, String s, boolean b ,int id, boolean c) {
		super(frmSum, s, b);
		FrmSum.by=null;
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnSearch);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		orderid=id;
		choose=c;
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
		if(e.getSource()==this.btnSearch){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择优惠","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmSum.by=this.you.get(i);
			this.setVisible(false);
			}
		}
		
	
}

