package Client;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GroupWin extends JPanel{

	JLabel choicelbl,tbllbl;
	JRadioButton create_btn,enter_btn,join_btn,leave_btn;
	ButtonGroup gp;
	JButton enter;
	JTable gpTable;
	JScrollPane sp;
	
	public GroupWin() {
		// TODO Auto-generated constructor stub
		// Column Names 
		this.setLayout(null);
		
        String[] columnNames = { "Group Name", "Member" }; 
        String[][] data = { 
                { "NLP", "YES" }, 
                { "OS", "NO"} 
            };
        
        gpTable = new JTable(data,columnNames);	   	      
       
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
//        gpTable.setDefaultRenderer(String.class, centerRenderer);
        
        this.add(gpTable);
        sp = new JScrollPane(gpTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        		        
     	choicelbl=new JLabel("Select Option:");
     	tbllbl=new JLabel("Group Table:");
	    create_btn=new JRadioButton("Create Group");
	    enter_btn=new JRadioButton("Enter Group");
	    join_btn=new JRadioButton("Join Group");
	    leave_btn=new JRadioButton("Leave Group");
	    enter=new JButton("Select");
	    
	    gp=new ButtonGroup();	     	
	    gp.add(create_btn);
        gp.add(enter_btn);
        gp.add(join_btn);
        gp.add(leave_btn);
        create_btn.setSelected(true);
        		        	        
        this.add(choicelbl);
        this.add(tbllbl);
        this.add(create_btn);
        this.add(enter_btn);
        this.add(join_btn);
        this.add(leave_btn);
        this.add(enter);
        //this.add(gpTable);
        this.add(sp); 
        
        choicelbl.setBounds(40,10,200,30);
        create_btn.setBounds(40,40,200,25);
        enter_btn.setBounds(40,70,200,25);
        join_btn.setBounds(40,100,200,25);
        leave_btn.setBounds(40,130,200,25);
        enter.setBounds(70,170,100,25); 
        tbllbl.setBounds(250, 10, 200, 25);
        sp.setBounds(250, 40, 250, 200);
	}

}
