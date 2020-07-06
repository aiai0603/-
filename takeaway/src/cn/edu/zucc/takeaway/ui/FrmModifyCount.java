package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import cn.edu.zucc.takeaway.control.*;
import cn.edu.zucc.takeaway.model.BeanAddresser;
import cn.edu.zucc.takeaway.model.BeanCounts;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.util.BaseException;




public class FrmModifyCount extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("�޸�");
	private Button btnCancel = new Button("ȡ��");
	
	private JLabel labelPrice = new JLabel("�������Ѷ");
	private JTextField jt1=new JTextField(15);
	
	private JLabel labelVip = new JLabel("�������Ѷ");
	private JTextField jt2=new JTextField(15);
	
	private JLabel label = new JLabel("�ܷ����Ż�ȯ���ӣ�                       ");
	String sure[]= {" �� "," �� "};
	JComboBox<String> cb=new JComboBox<String>(sure);
	
	private BeanCounts count ;
	public FrmModifyCount(FrmCount frmCount, String s, boolean b, BeanCounts beanCounts) {
		super(frmCount, s, b);
	
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPrice);
		workPane.add(jt1);
		jt1.setText(Double.toString(beanCounts.getAc_money()));
		workPane.add(labelVip);
		workPane.add(jt2);
		jt2.setText(Double.toString(beanCounts.getCount_sale()));
		workPane.add(label);
		workPane.add(cb);
		if(beanCounts.isTogether())
		{
			cb.setSelectedIndex(0);
		}else
		{
			cb.setSelectedIndex(1);
		}
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 160);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		count=beanCounts;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			if(this.jt1.getText().equals(""))
			{
				
				JOptionPane.showMessageDialog(null, "����۸�Ϊ��","����",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			if(this.jt2.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "����Ϊ��","����",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			
			ExampleCountManager ex=new ExampleCountManager();
			try {
				double p1=Double.parseDouble(this.jt1.getText());
				double p2=Double.parseDouble(this.jt2.getText());
				ex.modifycount(count,p1,p2,cb.getSelectedIndex());
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
					return;
			}
			JOptionPane.showMessageDialog(null, "�޸ĳɹ�","�ɹ�",JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
		}
			
		
	}


}