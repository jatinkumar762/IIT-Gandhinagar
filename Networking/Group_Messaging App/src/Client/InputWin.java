package Client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InputWin extends JFrame implements ActionListener{
    
	JLabel input;
	JTextField name;
	JButton okbtn;
	String groupName,activity;
	
	public InputWin(String activity) {
		
		this.activity=activity;
		// TODO Auto-generated constructor stub
		Toolkit tool=Toolkit.getDefaultToolkit();
      	Dimension size=tool.getScreenSize();
     	final int WIDTH=320;
     	final int HEIGHT=200;
     	this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
     	this.setResizable(false);
     	this.setTitle("Input Window");
     	this.setVisible(true);
     	this.setLayout(null);
     	this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE); 
     	
     	input =new JLabel("Enter Group Name:");
     	name =new JTextField();
     	okbtn =new JButton("OK");
     	
     	this.add(input);
     	this.add(name);
     	this.add(okbtn);
     	
     	input.setBounds(20, 30, 150, 30);
     	name.setBounds(135, 35, 130, 23);
     	okbtn.setBounds(100, 80, 80, 30);
     	
    	okbtn.addActionListener(this);
     	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			
			if(!name.getText().toString().trim().equals(""))
			{
				groupName = name.getText().toString().trim();
				
				if(activity.equals("Create")) {
			    	ObjectOutputStream out=new ObjectOutputStream(ClientRes.client.getOutputStream());  
			    	out.writeObject("Create Group");
			    	out.writeObject(groupName);
			    	
			    	ObjectInputStream in=new ObjectInputStream(ClientRes.client.getInputStream());  		    	
			    	JOptionPane.showMessageDialog(this,in.readObject().toString(),"Group Creation",JOptionPane.INFORMATION_MESSAGE); 
				}
				else if(activity.equals("Join")) {
					ObjectOutputStream out=new ObjectOutputStream(ClientRes.client.getOutputStream());  
			    	out.writeObject("Join Group");
			    	out.writeObject(groupName);
			    	
			    	ObjectInputStream in=new ObjectInputStream(ClientRes.client.getInputStream());  		    	
			    	JOptionPane.showMessageDialog(this,in.readObject().toString(),"Group Creation",JOptionPane.INFORMATION_MESSAGE); 
			    	
				}
				else if(activity.equals("Leave")) {
					ObjectOutputStream out=new ObjectOutputStream(ClientRes.client.getOutputStream());  
			    	out.writeObject("Leave Group");
			    	out.writeObject(groupName);
			    	
			    	ObjectInputStream in=new ObjectInputStream(ClientRes.client.getInputStream());  		    	
			    	JOptionPane.showMessageDialog(this,in.readObject().toString(),"Group Creation",JOptionPane.INFORMATION_MESSAGE); 
			    	
				}
				else if(activity.equals("Enter")) {				 
					FileWin fw = new FileWin();
					ClientRes.cWin.jtb.addTab(groupName,fw);
				}
		    	this.dispose();
			}
			else
			JOptionPane.showMessageDialog(this,"Enter Name First","Group Operation",JOptionPane.ERROR_MESSAGE); 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
