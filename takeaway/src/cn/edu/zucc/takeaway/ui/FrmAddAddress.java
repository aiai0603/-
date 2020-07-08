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
import javax.swing.JTextField;

import cn.edu.zucc.takeaway.control.*;
import cn.edu.zucc.takeaway.model.BeanAddresser;
import cn.edu.zucc.takeaway.model.BeanOwnerCount;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;




public class FrmAddAddress extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("���");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelsheng = new JLabel("ѡ��ʡ�ݣ�                              ");
	private JLabel labelshi = new JLabel("ѡ��������                             ");
	private JLabel labeladd = new JLabel("�����ַ��");
	private JLabel labelcall = new JLabel(" ��ϵ�˳ƺ���  ");
	private JLabel man=new JLabel("   (����)");
	private JLabel woman=new JLabel("   (Ůʿ)");
	private JLabel cos=new JLabel("   (�ͻ�)");
	private JLabel labeltele = new JLabel("��ϵ�绰��");
	private JTextField jttele=new JTextField(18);
	private JTextField jtcall=new JTextField(12);
	private JTextField jtadd=new JTextField(18);
	String sheng[]= {"ZheJiang","ShangHai","JiangSu"};
	JComboBox<String> cb=new JComboBox<String>(sheng);
	String city1[]= {"HangZhou","TaiZhou","WenZhou","JinHua","JiaXing"};
	JComboBox<String> cb1=new JComboBox<String>(city1);
	String city2[]= {"PuDong","PuXi","FengXian"};
	String city3[]= {"SuZhou","NanJing","YangZhou"};
	
	
	public FrmAddAddress(FrmAddress frmAddress, String s, boolean b) {
		super(frmAddress, s, b);
	
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelsheng);
		workPane.add(cb);
		workPane.add(labelshi);
		workPane.add(cb1);
		workPane.add(labeladd);
		workPane.add(jtadd);
		workPane.add(labelcall);
		workPane.add(jtcall);
		if(BeanUsers.currentLoginUser.getSex()==1)
		{
			workPane.add(man);
		}
		else if(BeanUsers.currentLoginUser.getSex()==2)
		{
			workPane.add(woman);
		}else
		{
			workPane.add(cos);
		}
		workPane.add(labeltele);
		workPane.add(jttele);
		
		
		 cb.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                   if(e.getItem().equals("ZheJiang")) {
	                	   cb1.removeAllItems();
	                	   for(int i=0;i<city1.length;i++){
	                	   cb1.addItem(city1[i]);
	                   }
	                }else if(e.getItem().equals("ShangHai")){
	                	   cb1.removeAllItems();
	                	   for(int i=0;i<city2.length;i++){
	                	   cb1.addItem(city2[i]);
	                   }
	                }else if(e.getItem().equals("JiangSu")){
	                	   cb1.removeAllItems();
	                	   for(int i=0;i<city3.length;i++){
	                	   cb1.addItem(city3[i]);
	                   }
	                }
	            }

	            }
	        });
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 260);
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
			String sheng=(String) cb.getSelectedItem();
			String shi=(String) cb1.getSelectedItem();
			String add=jtadd.getText();
			String call=jtcall.getText();
			String tele=jttele.getText();
		ExampleAddressManager ex=new ExampleAddressManager();
			try {
				int id=BeanUsers.currentLoginUser.getUser_no();
				ex.addaddress(id,sheng,shi,add,call,tele);
			} catch (BaseException e1) {
				// TODO �Զ����ɵ� catch ��
				
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
					return;
				
			}
			JOptionPane.showMessageDialog(null, "��ӳɹ�","�ɹ�",JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
		}
			
		
	}


}
