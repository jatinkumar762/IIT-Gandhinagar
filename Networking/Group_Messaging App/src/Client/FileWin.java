package Client;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class FileWin extends JPanel {

	ButtonGroup gp;
	JRadioButton listbtn,uploadbtn,dloadbtn,delbtn,sharebtn,showbtn;
	public FileWin() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		
		listbtn=new JRadioButton("List Files");
		uploadbtn=new JRadioButton("Upload File");
		dloadbtn=new JRadioButton("Download File");
		delbtn=new JRadioButton("Delete File");
		sharebtn=new JRadioButton("Share File");
		showbtn=new JRadioButton("Show Log");
		
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
		
	}

}
