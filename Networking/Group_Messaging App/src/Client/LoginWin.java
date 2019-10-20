package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LoginWin extends JFrame implements ActionListener {

	JLabel idlbl,pwdlbl;
    JTextField logid;
    JPasswordField pwd;
    JButton logbtn;
	    
	public LoginWin() 
	{
		// TODO Auto-generated constructor stub
		   this.setLayout(null);
		   this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
	       idlbl=new JLabel("Login ID :");
	       pwdlbl=new JLabel("Password :");
	       logid=new JTextField();
	       pwd=new JPasswordField();
	       logbtn=new JButton("Login");
	       logbtn.addActionListener(this);

	       this.add(idlbl);
	       this.add(logid);
	       this.add(pwdlbl);
	       this.add(pwd);
	       this.add(logbtn);
		
	       idlbl.setBounds(40,110,100,25);
	       logid.setBounds(120,110,150,25);
	       pwdlbl.setBounds(40,150,100,25);
	       pwd.setBounds(120,150,150,25);
	       logbtn.setBounds(100,190,100,25);
	}

	public void actionPerformed(ActionEvent ae)
    {
	 
    }
}
