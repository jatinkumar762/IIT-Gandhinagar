package Client;

import java.lang.*;
import java.net.*;
import java.util.Map;
import java.io.*;

public class ClientRes
{
   public static ClientApp cApp;
   public static ClientWin cWin;
   public static LoginWin lWin;
   public static Socket client;
   public static String logid;
   public static Map<String,String> gpM;
   static
   {
	   cApp = null;
       client=null;
       logid="";
       gpM=null;
   }
}