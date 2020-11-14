package com.mayiedu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test11 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		System.out.println("socket tcp 客户端启动...");
		
		Socket socket = new Socket("192.168.2.2", 8080);
		
		OutputStream outputStream = socket.getOutputStream();
		
		outputStream.write("我是蚂蚁课堂".getBytes());
		
		InputStream inputStream = socket.getInputStream();
		
		// ---
		byte[] buf = new byte[1024];
		int len;
		while((len=inputStream.read(buf)) != -1) {
			String str = new String(buf, 0, len);
			
			System.out.println(str);
		}
		
		
		
		socket.close();
		
		
		
	}
}
