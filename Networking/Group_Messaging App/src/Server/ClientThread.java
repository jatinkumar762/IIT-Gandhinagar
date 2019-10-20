package Server;

import java.io.*;
import java.net.*;
import java.util.Properties;

public class ClientThread extends Thread{

	  private Socket client;
	  private String logid;
	  private String pwd;
	  private String loginTime;
	  private String dirPath;
	  private FileReader  Fin = null;
      private FileWriter Fout = null;
      
      
	public ClientThread(Socket client) {
		// TODO Auto-generated constructor stub		
		 this.client=client;
		 this.start();	
	}

	 public void run() {
		 
		  try
	       {
	         while(true)
	         { 
		          ObjectInputStream in=new ObjectInputStream(this.client.getInputStream());
		          String req=in.readObject().toString();
	          
		          if(req.equals("NewUser")) {
		        	  
		        	  this.logid = in.readObject().toString();
		        	  this.pwd = in.readObject().toString();
		        	  this.loginTime=new java.util.Date().toString();	        	  
		        	  Fout = new FileWriter("Server\\User_Details.txt");
		        	  Fout.write(logid+"="+pwd);
		        	  ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
		        	  out.writeObject("Successfully Created");        	 	        	  
		          }
		          else if(req.equals("LoginD")) {
		        	  
		        	    this.logid = in.readObject().toString();
		        	    this.pwd = in.readObject().toString();
		        	    this.loginTime=new java.util.Date().toString();
		        		
		        	    Properties p = new Properties();
		        	    p.load(new FileInputStream("Server\\User_Details.txt"));
		        	    
		        	    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
		        	    
		        	    if(p.getProperty(logid).equals("pwd")) {
		        	    	 out.writeObject("Successfully Created"); 
		        	    }else {
		        	    	out.writeObject("User Doesn't Exist"); 
		        	    }
	        	            	       	  
		          }          
	                    
	         }
	         
	       }catch(Exception ex) {
	    	   
	       }
	 }
}
