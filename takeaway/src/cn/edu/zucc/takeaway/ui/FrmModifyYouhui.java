package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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
import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanKind;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanYouHui;
import cn.edu.zucc.takeaway.util.BaseException;




public class FrmModifyYouhui extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("修改");
	private Button btnCancel = new Button("取消");
	private JLabel labelRe = new JLabel("需要订单： ");
	private JTextField jt=new JTextField(20);
	private JLabel labelPrice = new JLabel("折扣价格： ");
	private JTextField jt1=new JTextField(20);
	private JLabel labels = new JLabel("开始日期： ");
	private JTextField jt2=new JTextField(20);
	private JLabel labele = new JLabel("结束日期： ");
	private JTextField jt3=new JTextField(20);
	private BeanYouHui By=null;
	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	private JLabel label = new JLabel("能否与优惠券叠加：                       ");
	String sure[]= {" 否 ", " 是 "};
	JComboBox<String> cb=new JComboBox<String>(sure);
	
	public FrmModifyYouhui(FrmYouhui frmYouhui, String s, boolean b, BeanYouHui beanYouHui) {
		super(frmYouhui, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelRe);
		workPane.add(jt);
		jt.setText(Integer.toString(beanYouHui.getRequest()));
		workPane.add(labelPrice);
		workPane.add(jt1);
		jt1.setText(Double.toString(beanYouHui.getYouhui_sale()));
		workPane.add(labels);
		workPane.add(jt2);
		jt2.setText(f.format(beanYouHui.getStartday()));
		workPane.add(labele);
		workPane.add(jt3);
		jt3.setText(f.format(beanYouHui.getEndday()));
		workPane.add(label);
		workPane.add(cb);
		if(beanYouHui.isTogether())
		cb.setSelectedIndex(1);
		else
		cb.setSelectedIndex(0);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 240);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		By=beanYouHui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String name=this.jt.getText();
			if(this.jt.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "需求订单为空","错误",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			if(this.jt1.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "折扣减免为空","错误",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			
			ExampleYouhuiManager ex=new ExampleYouhuiManager();
			try {
				int p1=Integer.parseInt(this.jt.getText());
				double p2=Double.parseDouble(this.jt1.getText());
				ex.modifyyouhui(By,p1,p2,jt2.getText(),jt3.getText(),cb.getSelectedIndex());
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				
			}
			JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
		}
			
		
	}


}
