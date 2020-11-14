package com.mayiedu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test10 {

	public static void main(String[] args) throws IOException {
		
		System.out.println("socket tcp ·þÎñÆ÷Æô¶¯...");
		ServerSocket serverSocket = new ServerSocket(8080);
		try {
			while (true) {
				Socket accept = serverSocket.accept();
				
				new Thread(new Runnable() {
					public void run() {
						InputStream inputStream = null;
						OutputStream outputStream = null;
						try {
							inputStream = accept.getInputStream();
							
							byte[] buf = new byte[1024];
							
							int len = inputStream.read(buf);
							
							String str = new String(buf, 0, len);
							
							System.out.println(str);
							
							outputStream = accept.getOutputStream();
							
							outputStream.write("aa".getBytes());
							
							outputStream.flush();
							
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if(inputStream != null){
								try {
									inputStream.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							if(outputStream != null){
								try {
									outputStream.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							
						}
						
						
					}
				}).start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			serverSocket.close();
		}
		
		
	}
	
	
	
}
