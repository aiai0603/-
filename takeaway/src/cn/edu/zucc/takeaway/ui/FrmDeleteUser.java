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

import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.DbException;
import cn.edu.zucc.takeaway.control.*;




public class FrmDeleteUser extends JDialog implements ActionListener {
	public static BeanUsers book;
	private JPanel toolBar = new JPanel();
	private Button btnModify = new Button("修改");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private Object tblTitle[]={"用户名","手机","城市","邮箱","VIP状态"};
	private Object tblData[][];
	List<BeanUsers> user=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			ExampleUserManager ex=new ExampleUserManager();
			user=ex.searchUser(this.edtKeyword.getText());
			tblData =new Object[user.size()][5];
			for(int i=0;i<user.size();i++){
				tblData[i][0]=user.get(i).getUser_name();
				tblData[i][1]=user.get(i).getTele();
				tblData[i][2]=user.get(i).getCity();
				tblData[i][3]=user.get(i).getEmail();
				tblData[i][4]=user.get(i).isVip();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public FrmDeleteUser (Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		toolBar.add(btnModify);
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

		
		this.btnModify.addActionListener(this);
	
		this.btnSearch.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnModify){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择用户","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			book=this.user.get(i);
			FrmModify dlg=new FrmModify(this,"信息修改",true);
			dlg.setVisible(true);
			this.reloadTable();
			}
		
		
		else if(e.getSource()==this.btnSearch){
			this.reloadTable();
		}
		
	}
}

