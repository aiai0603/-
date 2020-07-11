package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
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
import cn.edu.zucc.takeaway.model.BeanOrders;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.DbException;




public class FrmTJ extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");

	private JLabel labelUser = new JLabel("");
	private JLabel labelUser2 = new JLabel("");
	private JLabel labelUser3 = new JLabel("");
	private JLabel labelUser4 = new JLabel("");
	
	public FrmTJ(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
	
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
	
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(labelUser2);
		workPane.add(labelUser3);
		workPane.add(labelUser4);
		
		ExampleGoodManager ex=new ExampleGoodManager();
		try {
			labelUser.setText("为您推荐："+ex.tjgoods().getGood_name()+"      ");
			labelUser2.setText("惊喜价格："+ex.tjgoods().getGood_price()+"     ");
			labelUser3.setText("Vip只需："+ex.tjgoods().getGood_sale()+"     ");
			labelUser4.setText("优惠尽在："+ex.getshopname(ex.tjgoods().getGood_no())+"     ");
		} catch (DbException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(200, 170);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
	
		this.btnOk.addActionListener(this);

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource()==this.btnOk){
			this.setVisible(false);
		}
			
		
	}


}
