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
import cn.edu.zucc.takeaway.util.BaseException;




public class FrmRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("注册");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelUser = new JLabel("用户：");
	private JLabel tele = new JLabel("电话：");
	private JLabel email = new JLabel("邮箱：");
	String city[]= {" 嘉兴"," 上海"," 杭州"," 温州"," 台州"};
	JComboBox<String> cb=new JComboBox<String>(city);
	
	
	private JLabel labelPwd = new JLabel("密码：");
	private JLabel labelPwd2 = new JLabel("密码：");
	private JLabel sex = new JLabel("性别：");
	private JLabel labelCity = new JLabel("城市(本软件仅支持部分城市试用)：");
	private JTextField edtUserId = new JTextField(20);
	private JTextField edtele = new JTextField(20);
	private JTextField edemail = new JTextField(20);
	
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JRadioButton rb1 =new JRadioButton("男");
	private JRadioButton rb2 =new JRadioButton("女");
	private JRadioButton rb3 =new JRadioButton("保密");
	ButtonGroup g=new ButtonGroup();
	
	private JCheckBox jc=new JCheckBox("同意外卖助手事务管理系统使用协议");
	
	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		g.add(rb1);
		g.add(rb2);
		g.add(rb3);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(tele);
		workPane.add(edtele);
		workPane.add(email);
		workPane.add(edemail);
		workPane.add(labelCity);
		
		workPane.add(cb);
		
		workPane.add(labelPwd);
		
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		workPane.add(sex);
		workPane.add(rb1);
		workPane.add(rb2);
		workPane.add(rb3);
		
		rb3.setSelected(true);
    	workPane.add(jc);
    
    
		
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 320);
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
			String userid=this.edtUserId.getText();
			String telenum=this.edtele.getText();
			String emailtext=this.edemail.getText();
			String cityname=(String) this.cb.getSelectedItem();
			int sexchoose=0;
			
			if(rb1.isSelected())
			{
				sexchoose=1;
			}
			else if(rb2.isSelected()){
				sexchoose=2;
			}
			Boolean agree=this.jc.isSelected();

			
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			ExampleUserManager ex=new ExampleUserManager();
			
			
			try {
				ex.reg(userid,pwd1,pwd2,telenum,emailtext,cityname,sexchoose,agree);
			} catch (BaseException e1) {
				// TODO 自动生成的 catch 块
				
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				
			}
			JOptionPane.showMessageDialog(null, "注册成功","成功",JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
		}
			
		
	}


}
