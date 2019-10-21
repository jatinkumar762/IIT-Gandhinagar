package Client;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Server.ServerRes;

public class ClientWin extends JFrame{

	public ClientWin() {
		// TODO Auto-generated constructor stub
		Toolkit tool=Toolkit.getDefaultToolkit();
      	Dimension size=tool.getScreenSize();
     	final int WIDTH=600;
     	final int HEIGHT=600;
     	this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
     	this.setResizable(false);
     	this.setTitle("Client Window");
     	this.setVisible(true);
	}

}
