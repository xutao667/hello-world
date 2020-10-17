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
		System.out.println("socket tcp ���������...");
		
		ServerSocket serverSocket = new ServerSocket(8080);
		
		Socket accept = serverSocket.accept();
		
		InputStream inputStream = accept.getInputStream();
		
		byte[] buf = new byte[1024];
		
		int len = inputStream.read(buf);
		
		String str = new String(buf, 0, buf.length);
		
		System.out.println("���������ܿͻ�������:" + str);
		
		serverSocket.close();
		
	}
}

class TcpClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("socket tcp �ͻ�������...");
		
		Socket socket = new Socket("127.0.0.1", 8080);
		
		OutputStream outputStream = socket.getOutputStream();
		
		outputStream.write("�������Ͽ���".getBytes("gbk"));
		
		socket.close();
	}
	
	
	
}




