package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cn.edu.zucc.takeaway.util.*;
import cn.edu.zucc.takeaway.control.ExampleUserManager;
import cn.edu.zucc.takeaway.model.BeanUsers;


public class FrmVip extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnLogin = new JButton("ȷ�Ͽ�ͨ");
	private JButton btnCancel = new JButton("�˳�");
	private JRadioButton rb4 =new JRadioButton("10Ԫ/�� �Զ�����   ");
	private JRadioButton rb1 =new JRadioButton("15Ԫ�»�Ա   ");
	private JRadioButton rb2 =new JRadioButton("55Ԫ���Ȼ�Ա   ");
	private JRadioButton rb3 =new JRadioButton("155Ԫ��ѻ�Ա    ");
	private JLabel tips=new JLabel("ɨ��֧������ͨ��Ա��");
	private ButtonGroup g=new ButtonGroup();
	java.net.URL u2=cn.edu.zucc.takeaway.ui.FrmLoading.class.getResource("img2.jpg");
	Icon icon2=new ImageIcon(u2);
	static JLabel jl=new JLabel();
	
	
	public FrmVip(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
	
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		g.add(rb1);
		g.add(rb2);
		g.add(rb3);
		g.add(rb4);
		
		workPane.add(rb4);
		workPane.add(rb1);
		workPane.add(rb2);
		workPane.add(rb3);
		jl.setIcon(icon2);
		workPane.add(jl);
		rb3.setSelected(true);
		
		workPane.add(tips);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(340, 500);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmLoading.ok=1;
				setVisible(false);
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if (e.getSource() == this.btnLogin) {
			ExampleUserManager ex=new ExampleUserManager();
			try {
				int mode=0;
				if(rb1.isSelected()||rb4.isSelected())
					mode=1;
				else if(rb2.isSelected())
					mode=3;
				else if(rb3.isSelected())
					mode=12;
					
				ex.changevip(BeanUsers.currentLoginUser, mode);
				JOptionPane.showMessageDialog(null, "��ͨ�ɹ���", "�ɹ�",JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
				
				
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
		} else if (e.getSource() == this.btnCancel) {
			
			this.setVisible(false);
			
			
		} 
		
	}

	
	
}
