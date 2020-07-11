package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.ComboBoxUI;

import cn.edu.zucc.takeaway.control.*;
import cn.edu.zucc.takeaway.model.BeanAddresser;
import cn.edu.zucc.takeaway.model.BeanCounts;
import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.model.BeanYouHui;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.DbException;




public class FrmSum extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("�ύ����");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelPrice = new JLabel("ԭ��: ");
	private JLabel lb=new JLabel();
	private JLabel labelPrice2 = new JLabel("����: ");
	private JLabel lb2=new JLabel();
	private JLabel labelCount = new JLabel("����(��vipǰ�۸����):");
	private JCheckBox cb=new JCheckBox("");
	private JLabel labelYouhui = new JLabel("ѡ���Ż�ȯ");
	private JLabel labeltime = new JLabel("Ԥ���ʹ�ʱ��");
	private JLabel time = new JLabel();
	private Button youhui=new Button("ѡ���ҵ��Ż�ȯ");
	private JLabel labeladd = new JLabel("ѡ���ջ���ַ");
	private Button address=new Button("ѡ���ҵ��ջ���ַ");
	private int id;
	public static BeanOwnerCount by=null;
	public static BeanAddresser ad=null;
	private double fina=0;
	private double truesum=0;
	private BeanCounts countsum=null;
	private Date afterDate;
	private void resum(){
		ExampleGoodMoreManager ex=new ExampleGoodMoreManager();
		try {
			fina=ex.sumall3(id,BeanUsers.currentLoginUser.getUser_no(),by,cb.isSelected());
			lb2.setText(Double.toString(fina)+"Ԫ  ");
		} catch (DbException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	public FrmSum(FrmBuy frmBuy, String s, boolean b,int orderid) {
		super(frmBuy, s, b);
		ExampleGoodMoreManager ex=new ExampleGoodMoreManager();
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPrice);
		workPane.add(lb);
		try {
			truesum=ex.sumall(orderid,BeanUsers.currentLoginUser.getUser_no());
			lb.setText(Double.toString(truesum)+"Ԫ   ");
		} catch (DbException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		labelPrice.setFont(new Font (Font.DIALOG, Font.BOLD, 24));
		lb.setFont(new Font (Font.DIALOG, Font.BOLD, 24));
		workPane.add(labelCount);
		workPane.add(cb);
		
		
		ExampleCountManager ex2=new ExampleCountManager();
		try {
			if(ex2.offercount(orderid,ex.sumall2(orderid,BeanUsers.currentLoginUser.getUser_no()))!=null){
				countsum=ex2.offercount(orderid,ex.sumall2(orderid,BeanUsers.currentLoginUser.getUser_no()));
				cb.setText("��"+Double.toString(ex2.offercount(orderid,ex.sumall2(orderid,BeanUsers.currentLoginUser.getUser_no())).getAc_money())+"��"+Double.toString(countsum.getCount_sale()));
				cb.setSelected(true);
			}
			else
			{
				cb.setText("��������");
				cb.setEnabled(false);
			}
			
		} catch (DbException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		 cb.addItemListener(new ItemListener() {
			 public void itemStateChanged(ItemEvent e) {
				 JCheckBox jcb = (JCheckBox) e.getItem();
				 if (jcb.isSelected()) {
					by=null;
					youhui.setLabel("ѡ���ҵ��Ż�ȯ");
					resum();
					countsum=null;
				 } else {
					by=null;
					youhui.setLabel("ѡ���ҵ��Ż�ȯ");
					resum();
					countsum=null;
				 }
			 }
		   });
		 
		workPane.add(labelYouhui);
		labelYouhui.setFont(new Font (Font.DIALOG, Font.BOLD, 20));
		workPane.add(youhui);
		workPane.add(labeladd);
		labeladd.setFont(new Font (Font.DIALOG, Font.BOLD, 20));
		workPane.add(address);
		workPane.add(labeltime);
		labeltime.setFont(new Font (Font.DIALOG, Font.BOLD, 16));
		workPane.add(time);
		SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm");
		 Date now = new Date();
		 afterDate = new Date(now .getTime() + 30*60*1000);
		time.setText(sdf.format(afterDate));
		time.setFont(new Font (Font.DIALOG, Font.BOLD, 16));
		workPane.add(labelPrice2);
		workPane.add(lb2);
		labelPrice2.setFont(new Font (Font.DIALOG, Font.BOLD, 24));
		lb2.setFont(new Font (Font.DIALOG, Font.BOLD, 24));
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 300);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.youhui.addActionListener(this);
		this.address.addActionListener(this);
		id=orderid;
		resum();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				by=null;
				ad=null;
				resum();
			
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
		{
			this. by=null;
			this. ad=null;
			resum();
			this.setVisible(false);
		}
			
		else if(e.getSource()==this.btnOk){
		
			ExampleOrderManager ex=new ExampleOrderManager();
			ExampleOwnerCountManager ex2=new ExampleOwnerCountManager();
			ExampleGiveManager ex3=new ExampleGiveManager();
			try {
			ex.upload(id,ad,by,fina,truesum,countsum,afterDate);
			if(by!=null)
			ex2.deleteowner(by);
			ex3.addgive(BeanUsers.currentLoginUser.getUser_no(), id);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			} 
			by=null;
			ad=null;
			JOptionPane.showMessageDialog(null, "�µ��ɹ�������������·�ϣ�", "�ɹ�",JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			FrmBuy.ok=1;
		}
		else if(e.getSource()==this.youhui){
			FrmChooseYouhui dlg=new FrmChooseYouhui(this,"ѡ���Ż�ȯ",true,id,cb.isSelected());
			dlg.setVisible(true);
			if(by!=null)
				this.youhui.setLabel("�Ż�ȯ�Ѽ�"+by.getCount_money()+"Ԫ");
			resum();
			
		}else if(e.getSource()==this.address){
			FrmAddress dlg=new FrmAddress(this,"ѡ���ջ���ַ",true);
			dlg.setVisible(true);
			if(ad!=null)
				this.address.setLabel(ad.getAddress());
		}
			
		
	}


}
