package cn.edu.zucc.takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeaway.model.BeanComment;
import cn.edu.zucc.takeaway.model.BeanCounts;
import cn.edu.zucc.takeaway.model.BeanGoods;
import cn.edu.zucc.takeaway.model.BeanRider;
import cn.edu.zucc.takeaway.model.BeanShops;
import cn.edu.zucc.takeaway.model.BeanUsers;
import cn.edu.zucc.takeaway.util.BaseException;
import cn.edu.zucc.takeaway.util.BusinessException;
import cn.edu.zucc.takeaway.util.DbException;
import cn.edu.zucc.takeaway.control.*;




public class FrmShowCommit extends JDialog implements ActionListener {
	public static BeanRider book;
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("发表我的评价");
	private Button btnDelete = new Button("删除我的评价");
	private Object tblTitle[]={"发表人","评价时间","等级","内容"};
	private Object tblData[][];
	private BeanGoods good;
	private BeanShops shop;
	List<BeanComment> comment=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		ExampleCommitManager ex=new ExampleCommitManager();
		try {
			comment=ex.loadcommet(good);
		} catch (DbException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		tblData =new Object[comment.size()][4];
		for(int i=0;i<comment.size();i++){
			tblData[i][0]=comment.get(i).getUser_name();
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tblData[i][1]=f.format(comment.get(i).getComment_date());
			tblData[i][2]=comment.get(i).getCommnet_level();
			tblData[i][3]=comment.get(i).getComment_word();
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	
	

	public FrmShowCommit (Frame f, String s, boolean b, BeanGoods beanGoods,BeanShops shops) {
		super(f, s, b);
		good=beanGoods;
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnAdd.addActionListener(this);
		this.btnDelete.addActionListener(this);
		shop=shops;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAdd){
			
			ExampleCommitManager ex=new ExampleCommitManager();
			try {
				if(ex.tocomment(good,BeanUsers.currentLoginUser))
				{
					FrmAddComment dlg=new FrmAddComment(this, "添加评论", true,good,shop);
					dlg.setVisible(true);
					reloadTable();
				}else {
					JOptionPane.showMessageDialog(null, "你还没有购买过该商品！不能评论！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (BaseException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			
		}else if(e.getSource()==this.btnDelete) {
			ExampleCommitManager ex=new ExampleCommitManager();
			try {
				ex.deletecommit(good,BeanUsers.currentLoginUser);
				JOptionPane.showMessageDialog(null, "删除成功", "成功 ",JOptionPane.INFORMATION_MESSAGE);
				reloadTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	
		
	}
}

