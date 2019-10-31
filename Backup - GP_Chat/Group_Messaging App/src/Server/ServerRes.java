package Server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ServerRes {

	 public static ServerWin win;
     public static Vector loggedIn;
     static
     {
	   	  win=null;
	   	  loggedIn=new Vector<Socket>();	   	  
     }

}
