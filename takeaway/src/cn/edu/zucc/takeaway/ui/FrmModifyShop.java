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
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.util.BaseException;




public class FrmModifyShop extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("�޸�");
	private Button btnCancel = new Button("ȡ��");
	private int id;
	private JLabel labelUser = new JLabel("�̼�����");
	private JTextField jt=new JTextField(20);
	
	
	public FrmModifyShop(FrmShop frmShop, String s, boolean b,BeanShops curshop) {
		super(frmShop, s, b);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(jt);
		jt.setText(curshop.getShop_name());
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 160);
		id=curshop.getShop_no();
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String shopname=this.jt.getText();
			ExampleShopManager ex=new ExampleShopManager();
			try {
				ex.modifyshop(id,shopname);
			} catch (BaseException e1) {
				// TODO �Զ����ɵ� catch ��
				
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
					return;
				
			}
			JOptionPane.showMessageDialog(null, "�޸ĳɹ�","�ɹ�",JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
		}
			
		
	}


}
