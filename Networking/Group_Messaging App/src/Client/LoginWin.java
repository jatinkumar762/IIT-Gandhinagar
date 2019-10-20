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

	JLabel idlbl,pwdlbl,cnfpwdlbl;
    JTextField logid;
    JPasswordField pwd,cnfpwd;
    JButton logbtn,SignUpbtn;
	  
	public LoginWin(String type) 
	{
		// TODO Auto-generated constructor stub
		   this.setLayout(null);
		   this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		if(type=="signin") {
			
	       idlbl=new JLabel("User Name:");
	       pwdlbl=new JLabel("Password :");
	       logid=new JTextField();
	       pwd=new JPasswordField();
	       logbtn=new JButton("Sign In");
	       logbtn.addActionListener(this);

	       this.add(idlbl);
	       this.add(logid);
	       this.add(pwdlbl);
	       this.add(pwd);
	       this.add(logbtn);
	       this.add(SignUpbtn);
	       
	       idlbl.setBounds(40,110,100,25);
	       logid.setBounds(120,110,150,25);
	       pwdlbl.setBounds(40,150,100,25);
	       pwd.setBounds(120,150,150,25);
	       logbtn.setBounds(100,190,100,25);
	       SignUpbtn.setBounds(100,190,100,25);
	       
		}else {
			
			   idlbl=new JLabel("Login ID :");
		       pwdlbl=new JLabel("Password :");
		       cnfpwdlbl=new JLabel("Re-enter Password :");
		       
		       logid=new JTextField();
		       pwd=new JPasswordField();
               cnfpwd=new JPasswordField();
		       
		       SignUpbtn=new JButton("Sign Up");
		       SignUpbtn.addActionListener(this);
		       
		       this.add(idlbl);
		       this.add(logid);
		       this.add(pwdlbl);
		       this.add(pwd);
		       this.add(cnfpwdlbl);
		       this.add(cnfpwd);
		       this.add(SignUpbtn);
		       
		       idlbl.setBounds(40,110,100,25);
		       logid.setBounds(220,110,150,25);
		       pwdlbl.setBounds(40,150,100,25);
		       pwd.setBounds(220,150,150,25);
		       cnfpwdlbl.setBounds(40,185,200,25);
		       cnfpwd.setBounds(220,185,150,25);
		       SignUpbtn.setBounds(100,220,100,25);
			
		}
	       
	}

	public void actionPerformed(ActionEvent ae)
    {
	 
    }
}
