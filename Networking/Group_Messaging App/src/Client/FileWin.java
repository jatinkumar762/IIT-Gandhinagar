package Client;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class FileWin extends JPanel {

	ButtonGroup gp;
	JRadioButton listbtn,uploadbtn,dloadbtn,delbtn,sharebtn,showbtn;
	JButton closegp,select;
	JLabel label;
	
	public FileWin() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		
		listbtn=new JRadioButton("List Files");
		uploadbtn=new JRadioButton("Upload File");
		dloadbtn=new JRadioButton("Download File");
		delbtn=new JRadioButton("Delete File");
		sharebtn=new JRadioButton("Share File");
		showbtn=new JRadioButton("Show Log");
		
		closegp=new JButton("Close");
		select=new JButton("Select");
		
		label=new JLabel("Select Choice:");
		
		gp=new ButtonGroup();
	    gp.add(listbtn);
        gp.add(uploadbtn);
        gp.add(dloadbtn);
        gp.add(delbtn);
        gp.add(sharebtn);
        gp.add(showbtn);
        listbtn.setSelected(true);
		
        this.add(listbtn);
        this.add(uploadbtn);
        this.add(dloadbtn);
        this.add(delbtn);
        this.add(sharebtn);
        this.add(showbtn);	
        this.add(closegp);
        this.add(select);
        this.add(label);
        
        label.setBounds(5, 5, 100, 20);
        listbtn.setBounds(5, 30, 100, 20);
        uploadbtn.setBounds(5, 55, 100, 20);
        dloadbtn.setBounds(5, 80, 150, 20);
        delbtn.setBounds(5, 105, 100, 20);
        sharebtn.setBounds(5, 130, 100, 20);
        showbtn.setBounds(5,155,100,20);
        select.setBounds(30, 190, 100, 20);
        
        
	}

}
