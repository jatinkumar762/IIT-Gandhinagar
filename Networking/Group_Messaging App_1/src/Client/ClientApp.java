package Client;

import java.io.*;
import java.net.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientApp extends JFrame implements ActionListener{

	 JRadioButton in,up;
	 ButtonGroup cbg;
	 JLabel logas;
	 JButton enterbtn;
	 
	public ClientApp() 
	{
		// TODO Auto-generated constructor stub	
		try {
			
			ClientRes.client=new Socket("localhost",6666);  
			System.out.println("Conected to Server");
			
	        Toolkit tool=Toolkit.getDefaultToolkit();
	        Dimension size=tool.getScreenSize();
	        final int WIDTH=300;
	        final int HEIGHT=200;
	        this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
	        this.setResizable(false);
	        this.setTitle("Client Window");
	        this.setVisible(true);
	        this.setLayout(null);
	        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);	
	        
	        logas=new JLabel("Select Your Choice: ");
	        cbg=new ButtonGroup();
	        in=new JRadioButton("Sign In");
	        up=new JRadioButton("Sign Up");
	        in.setSelected(true);
            cbg.add(in);
            cbg.add(up);
	          
            enterbtn=new JButton("Enter");
            enterbtn.addActionListener(this);
            
            this.add(logas);
            this.add(in);
            this.add(up);
            this.add(enterbtn);
            
            logas.setBounds(20,30,200,25);
            in.setBounds(40,60,70,25);
            up.setBounds(130,60,100,25);
            enterbtn.setBounds(70,100,100,25);     
            
		}
		catch(Exception ex){
			  JOptionPane.showMessageDialog(this,"Unable to connect to server","Client App",JOptionPane.ERROR_MESSAGE);
	          return;
		}
		
	}

	public void actionPerformed(ActionEvent ae)
    {
		try {
			
			String type="";
	         if(this.in.isSelected())
	            type="signin";
	         else
	            type="signup";
	         
			LoginWin win=new LoginWin(type);
			ClientRes.lWin = win;
			Toolkit tool=Toolkit.getDefaultToolkit(); 
			Dimension size=tool.getScreenSize();
	        final int WIDTH=400;
	        final int HEIGHT=400;
	        win.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
	        win.setResizable(false);
	        win.setTitle("Login Window");
	        win.setVisible(true);	        
	        this.dispose(); 
	        
		}catch(Exception ex){
			
		}
        
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientRes.cApp  = new ClientApp();	
	}

}
