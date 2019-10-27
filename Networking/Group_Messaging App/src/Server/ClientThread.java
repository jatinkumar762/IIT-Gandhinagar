package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import Client.ClientRes;

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
			if (file.isDirectory()&&file.getName().equals("GP_"+gpName)) 
				return false;
		}
		File file = new File("Server_Data\\GP_"+gpName+"\\Member_Details.txt");
     	
     	if(file.getParentFile().mkdir()) {
         	if(file.createNewFile())
         		System.out.println("Success!");
     	}else {
     		if(file.createNewFile())
         		System.out.println("Success!");
     	}
		
     	file = new File("Server_Data\\GP_"+gpName+"\\Log_Details.txt");
     	if(file.createNewFile())
     		System.out.println("Success!");
     	
     	file = new File("Server_Data\\GP_"+gpName+"\\Chat_Details.txt");
     	if(file.createNewFile())
     		System.out.println("Success!");
     	
    	file = new File("Server_Data\\GP_"+gpName+"\\File_Details.txt");
     	if(file.createNewFile())
     		System.out.println("Success!");
     	
     	Fout = new FileWriter("Server_Data\\GP_"+gpName+"\\Member_Details.txt");
     	Fout.write(ClientRes.logid+"\n");
  	    Fout.close();	
  	  
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
			        	  Fout = new FileWriter("Server_Data\\User_Details.txt",true);
			        	  Fout.write(logid+"="+pwd+"\n");
			        	  Fout.flush();
			        	  Fout.close();		
			        	  um = GroupMember();
			        	  ClientRes.logid=this.logid;
			        	  out.writeObject("Successfully Created");  
			        	  out.writeObject(um);
			        	  
			        	  ServerRes.loggedIn.add(this.client);
			        	  
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
		        	    	}
		        	    }
		        	    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
		        	    if(flag) {
		        	    	 um = GroupMember();
		        	    	 ClientRes.logid=this.logid;
		        	    	 out.writeObject("Successfully Loggedin"); 
		        	    	 out.writeObject(um);
		        	    	 ServerRes.loggedIn.add(this.client);
		        	    }else {
		        	    	if(line!=null)
		        	    		out.writeObject("Wrong UserName/Password"); 
		        	    	else
		        	    		out.writeObject("User Doesn't Exist"); 
		        	    	out.writeObject(null);
		        	    }       	       	  
		          } 
		          else if(req.equals("Create Group")) {
		        	  
		        	  String gpName=in.readObject().toString();
		        	  ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
		        	  out.writeObject("Create Group"); 
		        	  if(CreateGroup(gpName))
		        		  out.writeObject("Successfully Created"); 
		        	  else
		        		  out.writeObject("Group Already Exist"); 
		        	  
		          }
		          else if(req.equals("Join Group")) {
		        	  
		        	  String gpName=in.readObject().toString();
		        	  
		        	  //check group exist or not
		        	    File folder = new File("Server_Data");
			      		File[] listOfFiles = folder.listFiles();
			      		boolean gexist = false;
			      		for (File file : listOfFiles) 
			      		{
			      			if (file.isDirectory()&&file.getName().equals("GP_"+gpName)) {
			      				gexist=true;
			      				break;
			      			}
			      		}
		        	  	        	  
			      		if(gexist)
			      		{		      			
				        	  BufferedReader br = new BufferedReader(new FileReader("Server_Data\\GP_"+ gpName +"\\Member_Details.txt"));
				        	  String line;
				        	  while ((line = br.readLine()) != null) {
				        	       // process the line.
				        	    	if(line.equals(ClientRes.logid))
				        	    		 break;
				        	  }
				        	  ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
				        	  out.writeObject("Join Group"); 
				        	  if(line!=null)
				        		  out.writeObject("User Already Member"); 
				        	  else {
				        		  Fout = new FileWriter("Server_Data\\GP_"+ gpName +"\\Member_Details.txt",true);
					        	  Fout.write(logid+"\n");
					        	  Fout.flush();
					        	  Fout.close();
					        	  out.writeObject("User Added Sucessfully"); 
				        	  }
			      		}
			      		else {
			      			ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
			      			out.writeObject("Join Group"); 
			      			out.writeObject("Group Not Exist"); 		      			
			      		}	        	  
		        	  		        	  
		          }
		          else if(req.equals("Leave Group")) {
  	          	  		        	  
  		        	  Vector<String> members=new Vector<String>();
  		        	  String gpName=in.readObject().toString();
  		        	  
  		        	  
		        	  //check group exist or not
		        	   File folder = new File("Server_Data");
			      	   File[] listOfFiles = folder.listFiles();
			      	   boolean gexist = false;
			      	   for (File file : listOfFiles) 
			      	   {
		      			if (file.isDirectory()&&file.getName().equals("GP_"+gpName)) {
		      				gexist=true;
		      				break;
		      			}
			      	   }
			      		
			      	   if(gexist) {
				        	  BufferedReader br = new BufferedReader(new FileReader("Server_Data\\GP_"+ gpName +"\\Member_Details.txt"));
				        	  String line;
				        	  Boolean flag= false;
				        	  while ((line = br.readLine()) != null) {
				        		  if(line.equals(ClientRes.logid))
				        			  flag=true;
				        	       members.addElement(line);
				        	  }
				        	  if(flag) {
					        	  Fout = new FileWriter("Server_Data\\GP_"+ gpName +"\\Member_Details.txt");
					        	  Fout.write("");
					        	  Fout.close();
					        	  Fout = new FileWriter("Server_Data\\GP_"+ gpName +"\\Member_Details.txt",true);
					        	  for(int i=0;i<members.size();i++)
					        		  Fout.write(members.get(i).toString());
					        	  Fout.close();
					        	  members = null;
					        	  ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
					        	  out.writeObject("Leave Group"); 
					        	  out.writeObject("User Removed Sucessfully"); 
				        	  }else {
				        		  ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
				        		  out.writeObject("Leave Group"); 
					        	  out.writeObject("Already not member"); 
				        	  }
			      	   }
			      	   else{
			      		  ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
			      		  out.writeObject("Leave Group"); 
			        	  out.writeObject("Group Not Exist");
			      	   }
		        	  
		          }
		          else if(req.equals("Enter Group")) {
		        	  
		        	  String gpName=in.readObject().toString();
		        	  		        	  
		        	  //check group exist or not
		        	   File folder = new File("Server_Data");
			      	   File[] listOfFiles = folder.listFiles();
			      	   boolean gexist = false;
			      	   for (File file : listOfFiles) 
			      	   {
		      			if (file.isDirectory()&&file.getName().equals("GP_"+gpName)) {
		      				gexist=true;
		      				break;
		      			}
			      	   }
			      	   
			      	  if(gexist) 
			      	  {
			        	  BufferedReader br = new BufferedReader(new FileReader("Server_Data\\GP_"+ gpName +"\\Member_Details.txt"));
			        	  String line;
			        	  Boolean flag= false;
			        	  while ((line = br.readLine()) != null) {
			        		  if(line.equals(ClientRes.logid))
			        			  flag=true;
			        	  }
			        	  ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
			        	  out.writeObject("Enter Group"); 
			        	  if(flag) {
			        		  out.writeObject("yes"); 
			        		  out.writeObject(gpName); 
			        	  }
			        	  else
			        		  out.writeObject("no"); 
			      	  }
			      	  else {
			      		 ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
			      		 out.writeObject("Enter Group"); 
			      		 out.writeObject("nexist");
			      	  }
			      	  
		          }
		          else if(req.equals("Group_Message"))
		          {
		        	  String gpName=in.readObject().toString();
		        	  String userName=in.readObject().toString();
		        	  String time=in.readObject().toString();
		        	  String chat=in.readObject().toString();
		        	  
		        	  for(Integer i=0;i<ServerRes.loggedIn.size();i++) 
		        	  {
			        	  ObjectOutputStream out=new ObjectOutputStream(((Socket)ServerRes.loggedIn.get(i)).getOutputStream());
				          out.writeObject("Group_Message");	 
				          out.writeObject(gpName);
				          out.writeObject(userName);
				          out.writeObject(time);
				          out.writeObject(chat);
		        	  }  
		        	  
		        	  FileWriter Fout = new FileWriter("Server_Data\\GP_"+gpName+"\\Chat_Details.txt",true);
		              Fout.write(userName+"$$"+time+"$$"+chat+"\n");
		          	  Fout.close();	        	  
		          }
		          else if(req.equals("Load_chat"))
		          {
		        	  
		          }
	                    
	         }
	         
	       }catch(Exception ex) {
	    	  ex.printStackTrace();
	       }
	 }
}
