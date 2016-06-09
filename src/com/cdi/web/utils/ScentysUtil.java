package com.cdi.web.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ScentysUtil {
	static final int SCENTYS_PORT_NUMBER = 23448;
	
	public static boolean sendCommand(String command,String host) {
		Socket mSocket = null; 
		PrintWriter out = null; 
		boolean flag = false;
		try { 
			InetAddress serverAddr = InetAddress.getByName(host); 
			mSocket = new Socket(serverAddr, 23448); 
			out = new PrintWriter(mSocket.getOutputStream(), true);
			out.write(command); 
			out.close(); 
			mSocket.close(); 
			flag = true;
		} catch (IOException e) { 
			e.printStackTrace(); 
			flag = false;
		} finally { 
			try { 
				out.close(); 
				mSocket.close(); 
			} catch (Exception e) {} 
		}
		return flag;
	}

}
