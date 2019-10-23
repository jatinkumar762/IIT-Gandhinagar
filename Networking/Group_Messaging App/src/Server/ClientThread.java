package Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientThread extends Thread{

	  private Socket client;
	  private String logid;
	  private String pwd;
	  private String loginTime;
	  private String dirPath;
	  private FileReader  Fin = null;
	  private FileWriter Fout = null;
      private Map um=null;
      
	public ClientThread(Socket client) {
		// TODO Auto-generated constructor stub		
		 this.client=client;
		 this.start();	
	}

	public Map GroupMember() throws Exception
	{
		Map<String,String> map=new HashMap<String,String>();
		File folder = new File("Server_Data");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) 
		{
	            if (file.isDirectory()&&file.getName().split("_")[0].equals("GP")) 
	            {
	                String gpN= file.getName().split("_")[1];
	                BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()+"\\Member_Details.txt"));
	                String line;
	        	    while ((line = br.readLine()) != null) {
	        	    	if(line.equals(logid))
	        	    	   break;
	        	    }
	        	    if(line!=null)
	        	    	map.put(gpN, "Yes");
	        	    else
	        	    	map.put(gpN, "No");
	            }
	    }
		return map;	
	}
	
	public Boolean CreateGroup(String gpName) throws Exception {
		
		File folder = new File("Server_Data");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) 
		{
			if (file.isDirectory()&&file.getName().split("_")[0].equals("GP")&&file.getName().split("_")[0].equals(gpName)) 
				return false;
		}
		File file = new File("Server_Data\\GP_"+gpName+"\\Member_Details.txt");
     	
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
		
		return true;
	}
	
	public void run() {
		 
		  try
	       {
	         while(true)
	         { 
		          ObjectInputStream in=new ObjectInputStream(this.client.getInputStream());
		          String req=in.readObject().toString();
	          
		          if(req.equals("NewUser"))
		          {
		        	  
		        	  this.logid = in.readObject().toString();
		        	  this.pwd = in.readObject().toString();
		        	  this.loginTime=new java.util.Date().toString();
		        	  
		        	    BufferedReader br = new BufferedReader(new FileReader("Server_Data\\User_Details.txt"));
		        	    String line;
		        	    while ((line = br.readLine()) != null) {
		        	       // process the line.
		        	    	String[] arr = line.split("=");
		        	    	if(arr[0].equals(logid))
		        	    	   break;
		        	    }
		        	    
		        	  ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream()); 
		        	  if(line!=null) {
		        		  out.writeObject("User Already Exist");  
		        	  }else {
			        	  Fout = new FileWriter("Server_Data\\User_Details.txt");
			        	  Fout.write(logid+"="+pwd+"\n");
			        	  Fout.flush();
			        	  Fout.close();		
			        	  um = GroupMember();
			        	  out.writeObject("Successfully Created");  
			        	  out.writeObject(um);
		        	  }
		          }
		          else if(req.equals("LoginD")) {
		        	  
		        	    this.logid = in.readObject().toString();
		        	    this.pwd = in.readObject().toString();
		        	    this.loginTime=new java.util.Date().toString();
		        		
		        	    BufferedReader br = new BufferedReader(new FileReader("Server_Data\\User_Details.txt"));
		        	    String line;
		        	    boolean flag = false;
		        	    while ((line = br.readLine()) != null) {
		        	       // process the line.
		        	    	String[] arr = line.split("=");
		        	    	if(arr[0].equals(logid) && arr[1].equals(pwd)) {
		        	    		flag=true; break;
		        	    	}else break;
		        	    }
		        	    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
		        	    if(flag) {
		        	    	 um = GroupMember();
		        	    	 out.writeObject("Successfully Loggedin"); 
		        	    	 out.writeObject(um);
		        	    }else {
		        	    	if(line!=null)
		        	    		out.writeObject("Wrong UserName/Password"); 
		        	    	else
		        	    		out.writeObject("User Doesn't Exist"); 
		        	    }       	       	  
		          } 
		          else if(req.equals("Create Group")) {
		        	  String gpName=in.readObject().toString();
		        	  ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
		        	  if(CreateGroup(gpName))
		        		  out.writeObject("Successfully Created"); 
		        	  else
		        		  out.writeObject("Group Already Exist"); 
		          }
	                    
	         }
	         
	       }catch(Exception ex) {
	    	   //ex.printStackTrace();
	       }
	 }
}
