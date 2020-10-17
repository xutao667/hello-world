package com.mayiedu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Test01 {

	public static void main(String[] args) throws IOException {
		System.out.println("udp����������������...");
		
		DatagramSocket ds = new DatagramSocket(8080);
		
		byte[] bytes = new byte[1024];
		
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
		// ����, �ȴ����ܿͻ��˷�������
		ds.receive(dp);
		System.out.println("��Դ:" + dp.getAddress() + ", "+dp.getPort());
		
		String str = new String(dp.getData(), 0, dp.getLength());
		
		System.out.println(str);
		
		ds.close();
	}
	
}

class UdpClient {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("udp �ͻ�����������...");
		
		DatagramSocket ds = new DatagramSocket();
		
		byte[] bytes = new byte[1024];
		
		bytes[1] = 65;
		
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 8080);
		
		ds.send(dp);
		
		ds.close();
	}

}
