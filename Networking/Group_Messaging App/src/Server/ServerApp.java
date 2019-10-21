package Server;

import java.net.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;

public class ServerApp extends JFrame {

	public ServerApp() 
	{
		// TODO Auto-generated constructor stub
		try {	
			    ServerSocket ss=new ServerSocket(6666); 
			    System.out.println("Server Started.......");
			 	ServerRes.win=new ServerWin();
	          	Toolkit tool=Toolkit.getDefaultToolkit();
	          	Dimension size=tool.getScreenSize();
	         	final int WIDTH=400;
	         	final int HEIGHT=400;
	         	ServerRes.win.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
	         	ServerRes.win.setResizable(false);
	         	ServerRes.win.setTitle("Server Window");
	         	ServerRes.win.setVisible(true);
	         	
	         	File file = new File("Server\\User_Details.txt");
	         	
	         	if(file.getParentFile().mkdir()) {
		         	if(file.createNewFile())
		         		System.out.println("Success!");
		            else 
		            	System.out.println ("Error, file already exists.");
	         	}else {
	         		if(file.createNewFile())
		         		System.out.println("Success!");
		            else 
		            	System.out.println ("Error, file already exists.");
	         	}
		         		         	
				  while(true)
		          {
			           System.out.println("Waiting for client...");
			           Socket client=ss.accept();
			           System.out.println("Client Connected Successfully");
			           //invokes thread
			           new ClientThread(client);
		          }			  
		}
		catch(Exception ex){
			  JOptionPane.showMessageDialog(this,ex.getMessage(),"Server App",JOptionPane.ERROR_MESSAGE);
	          return;
		}	
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		ServerApp  ServerObj = new ServerApp();	
	}

}
