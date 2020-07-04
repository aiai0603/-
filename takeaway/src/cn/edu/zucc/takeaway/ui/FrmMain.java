package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import cn.edu.zucc.takeaway.ui.FrmLogin;





public class FrmMain extends JFrame implements ActionListener {
	public static int uskind=0;
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_plan=new JMenu("�ƻ�����");
    private JMenu menu_step=new JMenu("�������");
    private JMenu menu_static=new JMenu("��ѯͳ��");
    private JMenu menu_more=new JMenu("����");
    
    private JMenuItem  menuItem_AddPlan=new JMenuItem("�½��ƻ�");
    private JMenuItem  menuItem_DeletePlan=new JMenuItem("ɾ���ƻ�");
    private JMenuItem  menuItem_AddStep=new JMenuItem("��Ӳ���");
    private JMenuItem  menuItem_DeleteStep=new JMenuItem("ɾ������");
    private JMenuItem  menuItem_startStep=new JMenuItem("��ʼ����");
    private JMenuItem  menuItem_finishStep=new JMenuItem("��������");
    private JMenuItem  menuItem_moveUpStep=new JMenuItem("��������");
    private JMenuItem  menuItem_moveDownStep=new JMenuItem("��������");
    
    private JMenuItem  menuItem_modifyPwd=new JMenuItem("�����޸�");
    
    private JMenuItem  menuItem_static1=new JMenuItem("ͳ��1");
    
    
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	

	
	

	

	
	public FrmMain(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("����ϵͳ");
		FrmLoading dlgLogin = new FrmLoading(this,"��������",true);
		dlgLogin.setVisible(true);
		
	
		
				 	this.menu_plan.add(this.menuItem_AddPlan); this.menuItem_AddPlan.addActionListener(this);
				    this.menu_plan.add(this.menuItem_DeletePlan); this.menuItem_DeletePlan.addActionListener(this);
				    this.menu_step.add(this.menuItem_AddStep); this.menuItem_AddStep.addActionListener(this);
				    this.menu_step.add(this.menuItem_DeleteStep); this.menuItem_DeleteStep.addActionListener(this);
				    this.menu_step.add(this.menuItem_startStep); this.menuItem_startStep.addActionListener(this);
				    this.menu_step.add(this.menuItem_finishStep); this.menuItem_finishStep.addActionListener(this);
				    this.menu_step.add(this.menuItem_moveUpStep); this.menuItem_moveUpStep.addActionListener(this);
				    this.menu_step.add(this.menuItem_moveDownStep); this.menuItem_moveDownStep.addActionListener(this);
				    this.menu_static.add(this.menuItem_static1); this.menuItem_static1.addActionListener(this);
				    this.menu_more.add(this.menuItem_modifyPwd); this.menuItem_modifyPwd.addActionListener(this);
				    
				    menubar.add(menu_plan);
				    menubar.add(menu_step);
				    menubar.add(menu_static);
				    menubar.add(menu_more);
				    this.setJMenuBar(menubar);
				    this.setVisible(true);
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		
	}

}
