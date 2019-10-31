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
   public static String Activegp;
   public static Map<String,String> gpM;
   public static InputWin iw;
   static
   {
	   cApp = null;
       client=null;
       logid="";
       gpM=null;
       Activegp=null;
   }
}