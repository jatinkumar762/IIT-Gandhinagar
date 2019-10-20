package Client;

import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.*;

public class ClientApp extends JFrame {

	public ClientApp() {
		// TODO Auto-generated constructor stub
		String clientIpAddr;
		String clientName;
		try {
			Socket s=new Socket("localhost",6666);  
			System.out.println("Conected to Server");
			
		  	LoginWin win=new LoginWin();
	        Toolkit tool=Toolkit.getDefaultToolkit();
	        Dimension size=tool.getScreenSize();
	        final int WIDTH=600;
	        final int HEIGHT=600;
	        win.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
	        win.setResizable(false);
	        win.setTitle("Login Window");
	        win.setVisible(true);
				
			s.close();
		}
		catch(Exception ex){
			  JOptionPane.showMessageDialog(this,"Unable to connect to server","Client App",JOptionPane.ERROR_MESSAGE);
	          return;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientApp  ClientObj = new ClientApp();
		
		
	}

}
