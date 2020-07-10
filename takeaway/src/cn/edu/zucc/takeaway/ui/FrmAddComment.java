package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.edu.zucc.takeaway.control.*;
import cn.edu.zucc.takeaway.model.BeanAddresser;
import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.BusinessException;




public class FrmAddComment extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("����");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelsheng = new JLabel("ѡ���Ǽ���                             ");
	private JLabel ds = new JLabel("����һ�仰����һ�������Ʒ�أ���:");
	private JTextArea jtms=new JTextArea(4,25);
	String sheng[]= {" 1�� "," 2�� "," 3�� "," 4�� "," 5�� "};
	JComboBox<String> cb=new JComboBox<String>(sheng);
	private  BeanGoods goods;
	private  BeanShops shop;
	
	
	public FrmAddComment(FrmShowCommit frmShowCommit, String s, boolean b, BeanGoods good,BeanShops shop) {
		super(frmShowCommit, s, b);
	
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(ds);
		workPane.add(jtms);
		workPane.add(labelsheng);
		workPane.add(cb);
		cb.setSelectedIndex(4);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 260);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.goods=good;
		this.shop=shop;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			ExampleCommitManager ex=new ExampleCommitManager();
			try {
				ex.addcomment(goods,BeanUsers.currentLoginUser,jtms.getText(),cb.getSelectedIndex(),shop);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(null, "���۳ɹ�", "�ɹ�",JOptionPane.INFORMATION_MESSAGE);
		
	}
	}


}
