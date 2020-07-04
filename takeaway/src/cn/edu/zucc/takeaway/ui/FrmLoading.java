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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;






public class FrmLoading extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	JLabel label=new JLabel("31801150-��˧  @��Ȩ����");
	 java.net.URL u2=cn.edu.zucc.takeaway.ui.FrmLoading.class.getResource("img1.jpg");
	Icon icon2=new ImageIcon(u2);
	static JLabel jl=new JLabel();
	public static int ok=0;
	private JButton btnLogin = new JButton("�û���¼");
	private JButton btnReg = new JButton("�û�ע��");
	private JButton btnAdmin = new JButton("����Ա��¼");
	


	public FrmLoading(Frame f, String s, boolean b) {
		super(f, s, b);
		jl.setIcon(icon2);
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		workPane.add(jl);
		workPane.add(label);
		
		toolBar.add(this.btnReg);
		toolBar.add(btnLogin);
		toolBar.add(btnAdmin);
		
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(600, 400);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnAdmin.addActionListener(this);
		this.btnReg.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if (e.getSource() == this.btnLogin) {
		
			FrmLogin dlg=new FrmLogin(this,"��¼",true);
			dlg.setVisible(true);
			
			if(ok==0)
			this.setVisible(false);
		}
		
			
			
		 else if (e.getSource() == this.btnAdmin) {
			 
				FrmLoginAdmin dlg=new FrmLoginAdmin(this,"����Ա��¼",true);
				dlg.setVisible(true);
				
				if(ok==0)
				this.setVisible(false);
				
		}
			
		 else if(e.getSource()==this.btnReg){
			FrmRegister dlg=new FrmRegister(this,"ע��",true);
			
			dlg.setVisible(true);
	}
	}
		
	}

	
	

