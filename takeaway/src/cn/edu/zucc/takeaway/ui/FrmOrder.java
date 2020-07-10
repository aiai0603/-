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

import cn.edu.zucc.takeaway.model.BeanGive;
import cn.edu.zucc.takeaway.model.BeanOrders;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.DbException;
import cn.edu.zucc.takeaway.control.*;




public class FrmOrder extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JTextField edtKeyword = new JTextField(10);
	public static BeanUsers book;
	private Button btnSearch = new Button("����Ҳ�ѯ");
	private Button btndelete = new Button("ȡ���õ�");
	private Button btnpj = new Button("���۸õ�");
	
	private Object tblTitle[]={"���","�ܽ��","�µ�ʱ��","Ԥ���ʹ�ʱ��","״̬"};
	private Object tblData[][];
	List<BeanOrders> you=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private FrmMain f;
	private void reloadTable(){
		try {
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ExampleOrderManager ex=new ExampleOrderManager();
			you=ex.loadorder(edtKeyword.getText(), BeanUsers.currentLoginUser.getUser_no());
			tblData =new Object[you.size()][5];
			for(int i=0;i<you.size();i++){
				tblData[i][0]=you.get(i).getShop_name();
				tblData[i][1]=you.get(i).getTrue_money();
				tblData[i][2]=f.format(you.get(i).getOrder_time());
				tblData[i][3]=f.format(you.get(i).getArrive());
				if(you.get(i).getSite()==0)
					tblData[i][4]="ȡ��";
				else if(you.get(i).getSite()==1)
					tblData[i][4]="�ȴ��ӵ�";
				else if(you.get(i).getSite()==2)
					tblData[i][4]="������";
				else if(you.get(i).getSite()==3)
					tblData[i][4]="��ʱδ�ӵ�";
				else if(you.get(i).getSite()==4)
					tblData[i][4]="���ͳ�ʱ";
				else if(you.get(i).getSite()==5)
					tblData[i][4]="�ʹ�";
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmOrder (Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		toolBar.add(btndelete);
		this.btndelete.addActionListener(this);
		toolBar.add(btnpj);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//��ȡ��������
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnSearch.addActionListener(this);
		this.btnpj.addActionListener(this);
		this.f=(FrmMain) f;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnSearch){
			this.reloadTable();
		}else if(e.getSource()==this.btndelete) {
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ�񶩵�", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			ExampleOrderManager ex=new ExampleOrderManager();
			try {
				ex.deleteorder(you.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.reloadTable();
			JOptionPane.showMessageDialog(null, "�Ѿ�ȡ�����Ѿ���ϵ���", "�ɹ�",JOptionPane.INFORMATION_MESSAGE);
			f.reloadPlanTable(f.edtKeyword.getText());
		}else if(e.getSource()==this.btnpj) {
			
		}
		
	}
}

