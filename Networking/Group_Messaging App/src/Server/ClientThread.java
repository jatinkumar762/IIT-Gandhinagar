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
		        	  
		        	    BufferedReader br = new BufferedReader(new FileReader("Server\\User_Details.txt"));
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
			        	  Fout = new FileWriter("Server\\User_Details.txt");
			        	  Fout.write(logid+"="+pwd+"\n");
			        	  Fout.flush();
			        	  Fout.close();		        	  
			        	  out.writeObject("Successfully Created");   
		        	  }
		          }
		          else if(req.equals("LoginD")) {
		        	  
		        	    this.logid = in.readObject().toString();
		        	    this.pwd = in.readObject().toString();
		        	    this.loginTime=new java.util.Date().toString();
		        		
		        	    BufferedReader br = new BufferedReader(new FileReader("Server\\User_Details.txt"));
		        	    String line;
		        	    boolean flag = false;
		        	    while ((line = br.readLine()) != null) {
		        	       // process the line.
		        	    	String[] arr = line.split("=");
		        	    	if(arr[0].equals(logid))
		        	    		if(arr[1].equals(pwd)) {
		        	    			flag=true;
		        	    			break;
		        	    		}else break;
		        	    }
		        	    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
		        	    if(flag) {
		        	    	 out.writeObject("Successfully Loggedin"); 
		        	    }else {
		        	    	if(line!=null)
		        	    		out.writeObject("Wrong Password"); 
		        	    	else
		        	    		out.writeObject("User Doesn't Exist"); 
		        	    }       	       	  
		          }          
	                    
	         }
	         
	       }catch(Exception ex) {
	    	   //ex.printStackTrace();
	       }
	 }
}
