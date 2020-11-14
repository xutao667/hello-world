package com.mayiedu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test12 {

	public static void main(String[] args) throws IOException {
		
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		
		System.out.println("socked tcp 服务端启动...");
		
		ServerSocket serverSocket = new ServerSocket(8080);
		
		while(true) {
			
			Socket accept = serverSocket.accept();
			
			newCachedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						InputStream in = accept.getInputStream();
						byte[] buf = new byte[1024];
						int len;
						len=in.read(buf);
						String str = new String(buf, 0, len);
						System.out.println(str);
						
						OutputStream out = accept.getOutputStream();
						
						out.write("数据已经接收到!".getBytes());
						
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			});
			
		}
	}
}
