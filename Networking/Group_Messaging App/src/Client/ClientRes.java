package Client;

import java.lang.*;
import java.net.*;
import java.io.*;

public class ClientRes
{
   public static Socket client;
   public static String logid;
   static
   {
       client=null;
       logid="";
   }
}