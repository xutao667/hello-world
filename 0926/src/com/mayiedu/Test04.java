package com.mayiedu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test04 {

	public static void main(String[] args) throws IOException {
		
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		
		System.out.println("socket tcp 服务器端启动...");
		
		ServerSocket serverSocket = new ServerSocket(8080);
		
		// 等待客户端请求
		try {
			
			while(true){
				Socket accept = serverSocket.accept();
				
				newCachedThreadPool.execute(new Runnable() {
					public void run() {
						
						try {
							InputStream inputStream = accept.getInputStream();
							
							byte[] buf = new byte[1024];
							
							int len = inputStream.read(buf);
							
							String str = new String(buf, 0, len);
							
							System.out.println("str: " + str);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			serverSocket.close();
		}
	}
}



class TcpClient3 {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		System.out.println("socket tcp 客户端启动...");
		
		Socket socket = new Socket("127.0.0.1", 8080);
		
		OutputStream outputStream = socket.getOutputStream();
		
		outputStream.write("我是蚂蚁课堂".getBytes("gbk"));
	
		outputStream.close();
	}
	
	
	
	
	
	
	
}













