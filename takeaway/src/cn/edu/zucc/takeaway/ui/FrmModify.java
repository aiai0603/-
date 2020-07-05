package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cn.edu.zucc.takeaway.control.ExampleUserManager;
import cn.edu.zucc.takeaway.model.BeanAdmin;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;



public class FrmModify extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("提交");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelUser = new JLabel("用户：");
	private JLabel tele = new JLabel(" 电话：");
	private JLabel email = new JLabel("邮箱：");
	String city[]= {"NewYork","HongKong","HangZhou","BeiJing","JiaXing"};
	JComboBox<String> cb=new JComboBox<String>(city);
	
	private JLabel labeloldPwd = new JLabel("原密码：");
	private JLabel labelPwd = new JLabel("新密码：");
	private JLabel labelPwd2 = new JLabel("新密码：");
	private JLabel sex = new JLabel("性别：                      ");
	private JLabel labelCity = new JLabel("城市：                                 ");
	private JTextField edtUserId = new JTextField(20);
	private JTextField edtele = new JTextField(20);
	private JTextField edemail = new JTextField(20);
	
	private JPasswordField edtoldPwd = new JPasswordField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JRadioButton rb1 =new JRadioButton("男");
	private JRadioButton rb2 =new JRadioButton("女");
	private JRadioButton rb3 =new JRadioButton("保密");
	ButtonGroup g=new ButtonGroup();
	private int uesrid;
	
	public FrmModify(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		g.add(rb1);
		g.add(rb2);
		g.add(rb3);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		edtUserId.setText(BeanUsers.currentLoginUser.getUser_name());
		workPane.add(edtUserId);
		workPane.add(tele);
		
		edtele.setText(BeanUsers.currentLoginUser.getTele());
		workPane.add(edtele);
		workPane.add(email);
		edemail.setText(BeanUsers.currentLoginUser.getEmail());
		workPane.add(edemail);
		workPane.add(labelCity);
		int i;
		for( i=0;i<city.length;i++)
		{
			if(city[i].equals(BeanUsers.currentLoginUser.getCity()))
				break;
		}
		
		cb.setSelectedIndex(i);
		workPane.add(cb);
		
		workPane.add(labeloldPwd);
		
		workPane.add(edtoldPwd);
		workPane.add(labelPwd);
		
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		
		workPane.add(sex);
		
		
		workPane.add(rb1);
		workPane.add(rb2);
		workPane.add(rb3);
		if(BeanUsers.currentLoginUser.getSex()==1)
		rb1.setSelected(true);
		else if(BeanUsers.currentLoginUser.getSex()==2)
		rb2.setSelected(true);
		else 
    	rb3.setSelected(true);
    
    
		
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 300);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		
		 uesrid=BeanUsers.currentLoginUser.getUser_no();
	}
	public FrmModify(FrmDeleteUser frmDeleteUser, String s, boolean b) {
		super(frmDeleteUser, s, b);
		g.add(rb1);
		g.add(rb2);
		g.add(rb3);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		edtUserId.setText(FrmDeleteUser.book.getUser_name());
		workPane.add(edtUserId);
		workPane.add(tele);
		
		edtele.setText(FrmDeleteUser.book.getTele());
		workPane.add(edtele);
		workPane.add(email);
		edemail.setText(FrmDeleteUser.book.getEmail());
		workPane.add(edemail);
		workPane.add(labelCity);
		int i;
		for( i=0;i<city.length;i++)
		{
			if(city[i].equals(FrmDeleteUser.book.getCity()))
				break;
		}
		
		cb.setSelectedIndex(i);
		workPane.add(cb);
		
		workPane.add(labeloldPwd);
		
		workPane.add(edtoldPwd);
		edtoldPwd.setText(FrmDeleteUser.book.getPwd());
		workPane.add(labelPwd);
		
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		
		workPane.add(sex);
		
		
		workPane.add(rb1);
		workPane.add(rb2);
		workPane.add(rb3);
		if(FrmDeleteUser.book.getSex()==1)
		rb1.setSelected(true);
		else if(FrmDeleteUser.book.getSex()==2)
		rb2.setSelected(true);
		else 
    	rb3.setSelected(true);
    
    
		
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 290);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		
		uesrid=FrmDeleteUser.book.getUser_no();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			
			String username=this.edtUserId.getText();
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
			

			String oldpwd=new String(this.edtoldPwd.getPassword());
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			ExampleUserManager ex=new ExampleUserManager();
			
			
			try {
				ex.modify(uesrid,username,oldpwd,pwd1,pwd2,telenum,emailtext,cityname,sexchoose);
			} catch (BaseException e1) {
				// TODO 自动生成的 catch 块
				
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				
			}
			JOptionPane.showMessageDialog(null, "修改成功,","成功",JOptionPane.INFORMATION_MESSAGE);
			
			this.setVisible(false);
		}
			
		
	}


}
