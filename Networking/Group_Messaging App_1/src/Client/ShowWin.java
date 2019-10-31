package Client;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShowWin extends JFrame  implements ActionListener{

	JTextArea chatArea,fileArea,logArea;
	JScrollPane ca;
	JTextField name,gName;
	JButton okbtn;
	String Operation;
	JLabel gp,fl;
	
	public ShowWin(String opr)
	{
		// TODO Auto-generated constructor stub
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	 	
		Toolkit tool=Toolkit.getDefaultToolkit();
     	Dimension size=tool.getScreenSize();
    	this.setResizable(false);
     	this.setVisible(true);
     	this.setLayout(null);

     	Operation = opr;
		if(opr.equals("loadchat"))	
		{		
	     	
	     	final int WIDTH=420;
	     	final int HEIGHT=420;
	     	this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
			chatArea=new JTextArea();
			ca=new JScrollPane(chatArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			chatArea.setEditable(false);
			this.setTitle("OLD Chat");
			this.add(ca);
			ca.setBounds(10, 10, 380, 365);
			chatArea.setText("Name"+"\tDate & Time "+"\t\tMessage");	
		}
		else if(opr.equals("List_File"))
		{		
	     	
	     	final int WIDTH=420;
	     	final int HEIGHT=420;
	     	this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
	     	
			fileArea=new JTextArea();
			ca=new JScrollPane(fileArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			fileArea.setEditable(false);
			this.setTitle("List of Files");
			this.add(ca);
			ca.setBounds(10, 10, 380, 365);
			fileArea.setText("File Name"+"\t\tDate & Time"+"\t\tUser");
		}
		else if(opr.equals("Log_File"))
		{	
	     	
	     	final int WIDTH=420;
	     	final int HEIGHT=420;
	     	this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
	     	
			logArea=new JTextArea();
			ca=new JScrollPane(logArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			logArea.setEditable(false);
			this.setTitle("List of Files");
			this.add(ca);
			ca.setBounds(10, 10, 380, 365);
			logArea.setText("File Name"+"\tUser"+"\tAction"+"\tHost Address"+"\t\tDate & Time");
		}
		else if(opr.equals("Del_File") || opr.equals("Dow_File"))
		{
			final int WIDTH=320;
	     	final int HEIGHT=200;
	     	this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
	     	this.setTitle("Enter File Name");
	     	
	    	name =new JTextField();
	     	okbtn =new JButton("OK");
	     	
	     	this.add(name);
	     	this.add(okbtn);
	     	
	     	name.setBounds(50, 35, 200, 23);
	     	okbtn.setBounds(100, 80, 80, 30);	     	
	    	okbtn.addActionListener(this);
		}
		else if(opr.equals("Share_File"))
		{
			final int WIDTH=320;
	     	final int HEIGHT=250;
	     	this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
	     	this.setTitle("Enter Group and File Name");
	     	
	     	gName=new JTextField();
	    	name =new JTextField();
	     	okbtn =new JButton("OK");
	     	
	     	gp=new JLabel("Enter Group Name:");
	     	fl=new JLabel("Enter File Name:");
	     	
	     	this.add(gName);
	     	this.add(name);
	     	this.add(okbtn);
	     	this.add(gp);
	     	this.add(fl);
	     	
	     	gp.setBounds(50, 10, 200, 30);
	     	gName.setBounds(50, 45, 200, 25);
	     	fl.setBounds(50, 80, 200, 25);
	     	name.setBounds(50, 110, 200, 25);
	     	okbtn.setBounds(100, 165, 80, 30);
	     	okbtn.addActionListener(this);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			
			if(!name.getText().toString().trim().equals(""))
			{
				String fileName = name.getText().toString().trim();
						
				if(Operation.equals("Del_File"))
				{
					 ObjectOutputStream out=new ObjectOutputStream(ClientRes.client.getOutputStream());
					 out.writeObject("Del_File");
					 out.writeObject(ClientRes.Activegp);
					 out.writeObject(fileName);
					 this.dispose();
				}
				else if(Operation.equals("Dow_File")) {
					 ObjectOutputStream out=new ObjectOutputStream(ClientRes.client.getOutputStream());
					 out.writeObject("Dow_File");
					 out.writeObject(ClientRes.Activegp);
					 out.writeObject(fileName);
					 this.dispose();
				}
				else if(Operation.equals("Share_File")) {
					
					String gpName = gName.getText().trim();
					String fName = name.getText().trim();
					
					if(gpName.equals("")) {
						JOptionPane.showMessageDialog(this,"Enter Group Name","File Share",JOptionPane.INFORMATION_MESSAGE); 
						return;
					}
					if(fName.equals("")) {
						JOptionPane.showMessageDialog(this,"Enter File Name","File Share",JOptionPane.INFORMATION_MESSAGE); 
						return;
					}
					
					 ObjectOutputStream out=new ObjectOutputStream(ClientRes.client.getOutputStream());
					 out.writeObject("Share_File");
					 out.writeObject(ClientRes.Activegp);
					 out.writeObject(fName);
					 out.writeObject(gpName);
					 this.dispose();

									
				}
					
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
