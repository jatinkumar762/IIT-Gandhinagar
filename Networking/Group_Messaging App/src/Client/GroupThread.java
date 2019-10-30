package Client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
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
   		          else if(req.equals("Load_chat"))
		          {
   		        	 ShowWin sw=new ShowWin("loadchat");
   		        	 
   		        	 String property = "java.io.tmpdir";
   	   		         String tempDir = System.getProperty(property);
   		        	 int size=(int)in.readObject();
   		        	 byte[] mybytearray = new byte[size];
   		             FileOutputStream fos = new FileOutputStream(tempDir+"chat.txt");
	   		         BufferedOutputStream bos = new BufferedOutputStream(fos);
	   		         int bytesRead = in.read(mybytearray, 0, mybytearray.length);
	   		         bos.write(mybytearray, 0, bytesRead);
	   		         bos.close();
	   		         
	   		         BufferedReader br = new BufferedReader(new FileReader(tempDir+"chat.txt"));
	        	     String line;
	        	     while ((line = br.readLine()) != null)
	        	     {
		        		sw.chatArea.append("\n"+line);	        	    	 	        	    	 
		        	 }   		         
		          }
   		          else if(req.equals("Upload_File"))
		          {
   		        	JOptionPane.showMessageDialog(ClientRes.cWin,in.readObject().toString(),"File Upload",JOptionPane.INFORMATION_MESSAGE);
		          }
   		          else if(req.equals("List_File"))
		          {
   		        	 ShowWin sw=new ShowWin("List_File");
  		        	 
  		        	 String property = "java.io.tmpdir";
  	   		         String tempDir = System.getProperty(property);
  		        	 int size=(int)in.readObject();
  		        	 byte[] mybytearray = new byte[size];
  		             FileOutputStream fos = new FileOutputStream(tempDir+"temp.txt");
	   		         BufferedOutputStream bos = new BufferedOutputStream(fos);
	   		         int bytesRead = in.read(mybytearray, 0, mybytearray.length);
	   		         bos.write(mybytearray, 0, bytesRead);
	   		         bos.close();
	   		         
	   		         BufferedReader br = new BufferedReader(new FileReader(tempDir+"temp.txt"));
	        	     String line;
	        	     while ((line = br.readLine()) != null)
	        	     {
		        		sw.fileArea.append("\n"+line);	        	    	 	        	    	 
		        	 }  
		          }
   		          else if(req.equals("Log_File"))
		          {
		        	 ShowWin sw=new ShowWin("Log_File");
		        	 
		        	 String property = "java.io.tmpdir";
	   		         String tempDir = System.getProperty(property);
		        	 int size=(int)in.readObject();
		        	 byte[] mybytearray = new byte[size];
		             FileOutputStream fos = new FileOutputStream(tempDir+"temp.txt");
	   		         BufferedOutputStream bos = new BufferedOutputStream(fos);
	   		         int bytesRead = in.read(mybytearray, 0, mybytearray.length);
	   		         bos.write(mybytearray, 0, bytesRead);
	   		         bos.close();
	   		         
	   		         BufferedReader br = new BufferedReader(new FileReader(tempDir+"temp.txt"));
	        	     String line;
	        	     while ((line = br.readLine()) != null)
	        	     {
		        		sw.logArea.append("\n"+line);	        	    	 	        	    	 
		        	 } 
	        	     
		          }
   		          else if(req.equals("Del_File"))
		          {		        	  
   		        	JOptionPane.showMessageDialog(ClientRes.cWin,in.readObject().toString(),"File Delete Operation",JOptionPane.ERROR_MESSAGE);         	  
		          }
   		          else if(req.equals("Dow_File"))
		          {		
   		        	 String fName=in.readObject().toString();
   		        	 if(!fName.equals("File not exist")) 
   		        	 {
		   		        	 int size=(int)in.readObject();
				        	 byte[] mybytearray = new byte[size];
				        	 int bytesRead = in.read(mybytearray, 0, mybytearray.length);   
			   		         
					   		 JFileChooser fileChooser = new JFileChooser();
					   		 fileChooser.setDialogTitle("Save File");   
					   		 fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					   		 int userSelection = fileChooser.showSaveDialog(ClientRes.cWin);
					   		    
					   		 if (userSelection == JFileChooser.APPROVE_OPTION) {
					   			   String path=fileChooser.getSelectedFile().getAbsolutePath();
					   			   FileOutputStream fos = new FileOutputStream(path+fName);
					   		       BufferedOutputStream bos = new BufferedOutputStream(fos);			   		        
					   		       bos.write(mybytearray, 0, bytesRead);
					   		       bos.close();
					   		 }
					   		JOptionPane.showMessageDialog(ClientRes.cWin,"File Successfully Downloaded","File Download Operation",JOptionPane.INFORMATION_MESSAGE);
   		        	 }
   		        	 else {
   		        		JOptionPane.showMessageDialog(ClientRes.cWin,fName,"File Download Operation",JOptionPane.ERROR_MESSAGE);
   		        	 }
	   		         
		          }
   		          else if(req.equals("Share_File"))
		          {	
   		        	    String status=in.readObject().toString();
   		        	    JOptionPane.showMessageDialog(ClientRes.cWin,status,"File Share Operation",JOptionPane.INFORMATION_MESSAGE); 		        	    		        	  
		          }
        		
        	}   	
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }
    }
}
