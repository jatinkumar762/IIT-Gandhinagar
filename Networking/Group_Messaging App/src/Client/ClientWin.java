package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import Server.ServerRes;

public class ClientWin extends JFrame {

	JTabbedPane jtb;
	GroupWin gw;
//	FileWin fw;
	public ClientWin(String user)
	{
		try {	
				//TODO Auto-generated constructor stub
				Toolkit tool=Toolkit.getDefaultToolkit();
		      	Dimension size=tool.getScreenSize();
		     	final int WIDTH=630;
		     	final int HEIGHT=420;
		     	this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
		     	this.setResizable(false);
		     	this.setTitle("User - "+user);
		     	this.setVisible(true);
		     	this.setLayout(null);
		     	this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		     	gw=new GroupWin();
		     	
		     	jtb=new JTabbedPane();
		        jtb.addTab("Group",gw);
		        
		        this.add(jtb);
		        jtb.setBounds(2,2,610,375);
		        	        
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
