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
import java.text.SimpleDateFormat;
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

import cn.edu.zucc.takeaway.model.BeanAdmin;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.ui.FrmLogin;





public class FrmMain extends JFrame implements ActionListener {
	public static int uskind=0;
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_user=new JMenu("�û�����");
    private JMenu menu_step=new JMenu("�������");
    private JMenu menu_static=new JMenu("��ѯͳ��");
    private JMenu menu_more=new JMenu("����");
    
    private JMenuItem  menuItem_Deleteuser=new JMenuItem("ɾ���û�");
    private JMenuItem  menuItem_listuser=new JMenuItem("ͳ���û�");
    private JMenuItem  menuItem_AddStep=new JMenuItem("��Ӳ���");
    private JMenuItem  menuItem_DeleteStep=new JMenuItem("ɾ������");
    private JMenuItem  menuItem_startStep=new JMenuItem("��ʼ����");
    private JMenuItem  menuItem_finishStep=new JMenuItem("��������");
    private JMenuItem  menuItem_moveUpStep=new JMenuItem("��������");
    private JMenuItem  menuItem_moveDownStep=new JMenuItem("��������");
    
    private JMenuItem  menuItem_modifyPwd=new JMenuItem("�����޸�");
    private JMenuItem  menuItem_AddAdmin=new JMenuItem("��������Ա");
    
    private JMenuItem  menuItem_static1=new JMenuItem("ͳ��1");
    
    
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	private JLabel label=new JLabel();
	
	private JMenuBar menubar2=new JMenuBar(); ;
	private JMenu menu_xx=new JMenu("��Ϣ����");
	private JMenuItem  menuItem_change=new JMenuItem("�˺���Ϣ");
	private JMenuItem  menuItem_vip=new JMenuItem("vip����");
	

	
	public FrmMain(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		FrmLoading dlgLogin = new FrmLoading(this,"��������",true);
		dlgLogin.setVisible(true);
		
	
		if(uskind==1)
		{
			this.setTitle("�û�ϵͳ");
			this.menu_xx.add(this.menuItem_change); this.menuItem_change.addActionListener(this);
		    this.menu_xx.add(this.menuItem_vip); this.menuItem_vip.addActionListener(this);
			menubar2.add(menu_xx);
			this.setJMenuBar(menubar2);
		    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		    if(BeanUsers.currentLoginUser.isVip())
		    {
		    	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		    	   label.setText("����! VIP�û� "+BeanUsers.currentLoginUser.getUser_name()+"     ���Ļ�Ա����"+f.format(BeanUsers.currentLoginUser.getVip_end())+
		    			   "����!");
		    	   
				    statusBar.add(label);
				    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		    }else {
		    	
		    	 label.setText("����! �û� "+BeanUsers.currentLoginUser.getUser_name()+"        ��ͨ��Ա�������Ż�");
				 statusBar.add(label);
				 this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		    }
		 
		    this.addWindowListener(new WindowAdapter(){   
		    	public void windowClosing(WindowEvent e){ 
		    		System.exit(0);
	             }
	        });
		    this.setVisible(true);

		}else if(uskind==2)
		{
			this.setTitle("����ϵͳ");
			this.menu_user.add(this.menuItem_Deleteuser); this.menuItem_Deleteuser.addActionListener(this);
			this.menu_user.add(this.menuItem_listuser); this.menuItem_listuser.addActionListener(this);
		    this.menu_step.add(this.menuItem_AddStep); this.menuItem_AddStep.addActionListener(this);
		    this.menu_step.add(this.menuItem_DeleteStep); this.menuItem_DeleteStep.addActionListener(this);
		    this.menu_step.add(this.menuItem_startStep); this.menuItem_startStep.addActionListener(this);
		    this.menu_step.add(this.menuItem_finishStep); this.menuItem_finishStep.addActionListener(this);
		    this.menu_step.add(this.menuItem_moveUpStep); this.menuItem_moveUpStep.addActionListener(this);
		    this.menu_step.add(this.menuItem_moveDownStep); this.menuItem_moveDownStep.addActionListener(this);
		    this.menu_static.add(this.menuItem_static1); this.menuItem_static1.addActionListener(this);
		    this.menu_more.add(this.menuItem_modifyPwd); this.menuItem_modifyPwd.addActionListener(this);
		    this.menu_more.add(this.menuItem_AddAdmin);this.menuItem_AddAdmin.addActionListener(this);
		    menubar.add(menu_user);
		    menubar.add(menu_step);
		    menubar.add(menu_static);
		    menubar.add(menu_more);
		    this.setJMenuBar(menubar);
			   statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
			   label.setText("���ã�����Ա "+BeanAdmin.currentLoginAdmin.getAdmin_name());
			 statusBar.add(label);
			 this.getContentPane().add(statusBar,BorderLayout.SOUTH);
			 
			  this.addWindowListener(new WindowAdapter(){   
			    	public void windowClosing(WindowEvent e){ 
			    		System.exit(0);
		             }
		        });
			  this.setVisible(true);
		}
				 	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==this.menuItem_AddAdmin){
			FrmRegisterAdmin dlg=new FrmRegisterAdmin(this,"��ӹ���Ա",true);
			dlg.setVisible(true);
			
		}
		else if(e.getSource()==this.menuItem_modifyPwd){
			FrmModifyPwd dlg=new FrmModifyPwd(this,"�����޸�",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_change){
			FrmModify dlg=new FrmModify(this,"��Ϣ�޸�",true);
			dlg.setVisible(true);
			
			 if(BeanUsers.currentLoginUser.isVip())
			  {
			    	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			    	label.setText("����! VIP�û� "+BeanUsers.currentLoginUser.getUser_name()+"     ���Ļ�Ա����"+f.format(BeanUsers.currentLoginUser.getVip_end())+
			    			   "����!");
			    }else {
			    	
			    	label.setText("����! �û� "+BeanUsers.currentLoginUser.getUser_name()+"        ��ͨ��Ա�������Ż�");
					
			    }
		}
		else if(e.getSource()==this.menuItem_vip){
			FrmVip dlg=new FrmVip(this,"vip",true);
			dlg.setVisible(true);
			
			
			 if(BeanUsers.currentLoginUser.isVip())
			  {
			    	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			    	label.setText("����! VIP�û� "+BeanUsers.currentLoginUser.getUser_name()+"     ���Ļ�Ա����"+f.format(BeanUsers.currentLoginUser.getVip_end())+
			    			   "����!");
			    }else {
			    	
			    	label.setText("����! �û� "+BeanUsers.currentLoginUser.getUser_name()+"        ��ͨ��Ա�������Ż�");
					
			    }
		
			
			
		}
		
	}

}
