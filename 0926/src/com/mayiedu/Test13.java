package com.mayiedu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test13 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		System.out.println("socket tcp 客户端启动...");
		
		Socket socket = new Socket("192.168.2.2", 8080);
		
		OutputStream os = socket.getOutputStream();
		
		os.write("我是客户端".getBytes());
		
		InputStream is = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len;
		len = is.read(buf);
		String str  = new String(buf, 0, len);
		System.out.println(str);
		
		socket.close();
		
	}
}
