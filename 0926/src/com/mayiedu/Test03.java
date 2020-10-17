package com.mayiedu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test03 {

	public static void main(String[] args) throws IOException {
		
		System.out.println("socket tcp ������������...");
		
		ServerSocket serverSocket = new ServerSocket(8080);
		
		while(true){
				Socket accept = serverSocket.accept();
				System.out.println("begin...");
				new Thread(new Runnable() {
					public void run() {
						try {
							InputStream inputStream = accept.getInputStream();
							
							byte[] buf = new byte[1024];
							
							int len = inputStream.read(buf);
							
							String str = new String(buf, 0, len);
							
							System.out.println("���������ܿͻ�������:" + str);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				}).start();
		}
	}
}


class TcpClient2 {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("socket tcp �ͻ�������...");
		
		Socket socket = new Socket("127.0.0.1", 8080);
		
		OutputStream outputStream = socket.getOutputStream();
		
		outputStream.write("�������Ͽ���11".getBytes());
		
		socket.close();
	}
	
	
}










