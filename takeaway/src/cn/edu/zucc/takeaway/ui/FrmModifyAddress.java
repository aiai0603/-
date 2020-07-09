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




public class FrmModifyAddress extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("修改");
	private Button btnCancel = new Button("取消");
	private JLabel labelsheng = new JLabel("选择省份：                              ");
	private JLabel labelshi = new JLabel("选择市区：                             ");
	private JLabel labeladd = new JLabel("具体地址：");
	private JLabel labelcall = new JLabel(" 联系人称呼：  ");
	private JLabel man=new JLabel("   (先生)");
	private JLabel woman=new JLabel("   (女士)");
	private JLabel cos=new JLabel("   (客户)");
	private JLabel labeltele = new JLabel("联系电话：");
	private JTextField jttele=new JTextField(18);
	private JTextField jtcall=new JTextField(12);
	private JTextField jtadd=new JTextField(18);
	String sheng[]= {"ZheJiang","ShangHai","JiangSu"};
	JComboBox<String> cb=new JComboBox<String>(sheng);
	String city1[]= {"HangZhou","TaiZhou","WenZhou","JinHua","JiaXing"};
	JComboBox<String> cb1=new JComboBox<String>(city1);
	String city2[]= {"PuDong","PuXi","FengXian"};
	String city3[]= {"SuZhou","NanJing","YangZhou"};
	private BeanAddresser book;
	public FrmModifyAddress(FrmAddress frmAddress, String s, boolean b, BeanAddresser book) {
		// TODO 自动生成的构造函数存根
		super(frmAddress, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelsheng);
		workPane.add(cb);
		cb.setSelectedItem(book.getSheng());
		workPane.add(labelshi);
		 if(book.getSheng().equals("ZheJiang")) {
      	   cb1.removeAllItems();
      	   for(int i=0;i<city1.length;i++){
      	   cb1.addItem(city1[i]);
         }
      }else if(book.getSheng().equals("ShangHai")){
      	   cb1.removeAllItems();
      	   for(int i=0;i<city2.length;i++){
      	   cb1.addItem(city2[i]);
         }
      }else if(book.getSheng().equals("JiangSu")){
      	   cb1.removeAllItems();
      	   for(int i=0;i<city3.length;i++){
      	   cb1.addItem(city3[i]);
         }
      }
		workPane.add(cb1);
		cb1.setSelectedItem(book.getShi());
		workPane.add(labeladd);
		workPane.add(jtadd);
		jtadd.setText(book.getAddress());
		workPane.add(labelcall);
		workPane.add(jtcall);
		jtcall.setText(book.getCall_user());
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
		jttele.setText(book.getAddress_tele());
		this.book=book;
		
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
				int id=this.book.getAddress_no();
				ex.modifyaddress(id,sheng,shi,add,call,tele);
			} catch (BaseException e1) {
				// TODO 自动生成的 catch 块
				
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				
			}
			JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
		}
			
		
	}


}
