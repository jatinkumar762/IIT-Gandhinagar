package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FileWin extends JPanel implements ActionListener {

	ButtonGroup gp;
	JRadioButton listbtn,uploadbtn,dloadbtn,delbtn,sharebtn,showbtn;
	JButton closegp,select,send,loadChat;
	JLabel label;
	JTextArea txtArea,mssgArea;
	JScrollPane jsp,mjsp;
	
	public FileWin()
	{
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		
		txtArea=new JTextArea();
        jsp=new JScrollPane(txtArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        txtArea.setEditable(false);
        txtArea.append("Name"+"\tDate & Time "+"\t\tMessage");
        this.add(jsp);
	        
        mssgArea=new JTextArea();
        mjsp=new JScrollPane(mssgArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(mjsp);
        
		listbtn=new JRadioButton("List Files");
		uploadbtn=new JRadioButton("Upload File");
		dloadbtn=new JRadioButton("Download File");
		delbtn=new JRadioButton("Delete File");
		sharebtn=new JRadioButton("Share File");
		showbtn=new JRadioButton("Show Log");
		
		closegp=new JButton("Close");
		select=new JButton("Select");
		send=new JButton("Send");
		loadChat=new JButton("Old Chat");
		
		
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
        this.add(send);
        this.add(loadChat);
        
        label.setBounds(5, 5, 100, 20);
        listbtn.setBounds(5, 30, 100, 20);
        uploadbtn.setBounds(5, 55, 100, 20);
        dloadbtn.setBounds(5, 80, 120, 20);
        delbtn.setBounds(5, 105, 100, 20);
        sharebtn.setBounds(5, 130, 100, 20);
        showbtn.setBounds(5,155,100,20);
        select.setBounds(30, 190, 100, 20);
        closegp.setBounds(500, 5, 100, 20);
        loadChat.setBounds(400, 5, 100, 20);
        jsp.setBounds(140, 30, 458, 220);
        mjsp.setBounds(140, 255, 300, 80);
        send.setBounds(460, 255, 70, 70);
        
        
        select.addActionListener(this);
        closegp.addActionListener(this);
        send.addActionListener(this);
        loadChat.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			
			if(e.getSource().equals(closegp))
			{
				ClientRes.cWin.jtb.remove(this);
			}
			else if(e.getSource().equals(select))
			{
							
				
			}
			else if(e.getSource().equals(send)) {
				
				String text = mssgArea.getText();
				if(!text.equals(""))
				{
					ObjectOutputStream out=new ObjectOutputStream(ClientRes.client.getOutputStream()); 
					out.writeObject("Group_Message");
					out.writeObject(ClientRes.Activegp);
					out.writeObject(ClientRes.logid);
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();  
					out.writeObject(dtf.format(now).toString());
									
					out.writeObject(text);				
				}	
				
			}
			else if(e.getSource().equals(loadChat))
			{
				int r=JOptionPane.showConfirmDialog(null, "Are you sure?");
				if(r==0)
				{
					ObjectOutputStream out=new ObjectOutputStream(ClientRes.client.getOutputStream()); 
					out.writeObject("Load_chat");
					out.writeObject(ClientRes.Activegp);
				}
			}
					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
				
	}

}
