package com.mayiedu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test02 {

	public static void main(String[] args) throws IOException {
		System.out.println("socket tcp 服务端启动...");
		
		ServerSocket serverSocket = new ServerSocket(8080);
		
		Socket accept = serverSocket.accept();
		
		InputStream inputStream = accept.getInputStream();
		
		byte[] buf = new byte[1024];
		
		int len = inputStream.read(buf);
		
		String str = new String(buf, 0, buf.length);
		
		System.out.println("服务器接受客户端内容:" + str);
		
		serverSocket.close();
		
	}
}

class TcpClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("socket tcp 客户端启动...");
		
		Socket socket = new Socket("127.0.0.1", 8080);
		
		OutputStream outputStream = socket.getOutputStream();
		
		outputStream.write("我是蚂蚁课堂".getBytes("gbk"));
		
		socket.close();
	}
	
	
	
}




