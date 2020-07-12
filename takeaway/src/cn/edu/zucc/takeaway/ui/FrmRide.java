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

import cn.edu.zucc.takeaway.model.BeanRider;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DbException;
import cn.edu.zucc.takeaway.control.*;




public class FrmRide extends JDialog implements ActionListener {
	public static BeanRider book;
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加");
	private Button btnModify = new Button("修改");
	
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private Object tblTitle[]={"骑手名","创建时间","等级","配送状态"};
	private Object tblData[][];
	List<BeanRider> ride=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			ExampleRideManager ex=new ExampleRideManager();
			ride=ex.loadrider(this.edtKeyword.getText());
			tblData =new Object[ride.size()][4];
			for(int i=0;i<ride.size();i++){
				tblData[i][0]=ride.get(i).getRider_name();
				SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				tblData[i][1]=f.format(ride.get(i).getRider_start());
				if(ride.get(i).getRider_level()==1)
				tblData[i][2]="新人";
				else if(ride.get(i).getRider_level()==2)
				tblData[i][2]="正式员工";
				else
				tblData[i][2]="单王";	
				
				tblData[i][3]=ride.get(i).isRide_site();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public FrmRide (Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnSearch.addActionListener(this);
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
			book=this.ride.get(i);
			FrmModifyRide dlg=new FrmModifyRide(this,"信息修改",true, book);
			dlg.setVisible(true);
			this.reloadTable();
			}
		else if(e.getSource()==this.btnAdd){
			FrmAddRide dlg=new FrmAddRide(this,"新增骑手",true);
			dlg.setVisible(true);
			this.reloadTable();
			
		}else if(e.getSource()==this.btnSearch){
		
			this.reloadTable();
			
		}
		
		
	}
}

