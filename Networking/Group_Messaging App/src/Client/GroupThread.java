package Client;

import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

public class GroupThread extends Thread {

	public GroupThread()
	{
		// TODO Auto-generated constructor stub
		this.start();
	}

    public void run()
    {
        try
        {
        	while(true)
        	{
   		          ObjectInputStream in=new ObjectInputStream(ClientRes.client.getInputStream());
   		          String req=in.readObject().toString();
        		
   		          if(req.equals("Create Group")) {  		        	    		    	
			    	JOptionPane.showMessageDialog(ClientRes.iw,in.readObject().toString(),"Group Creation",JOptionPane.INFORMATION_MESSAGE); 
   		          }
   		          else if(req.equals("Join Group")) {   		        	  
   		        	JOptionPane.showMessageDialog(ClientRes.iw,in.readObject().toString(),"Group Creation",JOptionPane.INFORMATION_MESSAGE); 
   		          }
   		          else if(req.equals("Leave Group")) {	        	  
   		        	JOptionPane.showMessageDialog(ClientRes.iw,in.readObject().toString(),"Group Creation",JOptionPane.INFORMATION_MESSAGE); 
   		          }
   		          else if(req.equals("Enter Group")) {
   		        	  
  			    	String resp =in.readObject().toString();
  			    	if(resp.equals("yes"))
  			    	{
  			    		String groupName =in.readObject().toString();
  			    		if(ClientRes.cWin.jtb.getTabCount()>1)
  			    			ClientRes.cWin.jtb.remove(1);;
  						FileWin fw = new FileWin();
  						ClientRes.cWin.fw = fw;
  						ClientRes.cWin.jtb.addTab(groupName,fw);
  						ClientRes.Activegp=groupName;
  			    	}
  			    	else if(resp.equals("no"))
  			    	{
  			    	    JOptionPane.showMessageDialog(ClientRes.iw,"Not Member of Group","Group Operation",JOptionPane.ERROR_MESSAGE); 
  			    	}
  			    	else {
  			    	    JOptionPane.showMessageDialog(ClientRes.iw,"Group Not Exist","Group Operation",JOptionPane.ERROR_MESSAGE); 
  			    	}
		        	  
  			    	
		          } 
   		          else if(req.equals("Group_Message"))
		          {
			          String gpName= in.readObject().toString();
			          String userName= in.readObject().toString();
			          String time= in.readObject().toString();
			          String chat= in.readObject().toString();
			          if(ClientRes.Activegp!=null && gpName.equals(ClientRes.Activegp))
			          {
			        	 ClientRes.cWin.fw.txtArea.append("\n"+userName+"\t"+time+"\t"+chat);			        	  
			        	  		        	  
			          }
   		        	  
		          }    		
        		
        	}   	
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }
    }
}
