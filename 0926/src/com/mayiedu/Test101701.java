package com.mayiedu;

import java.nio.ByteBuffer;

public class Test101701 {

	
	public static void main(String[] args) {
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("--------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		// 2.�򻺳������5������
		System.out.println("--------------------");
		buf.put("abcd1".getBytes());
		System.out.println("--------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		// 3.������ģʽ
		buf.flip();
		System.out.println("-----------������ģʽ------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		byte[] bytes = new byte[buf.limit()];
		buf.get(bytes);
		System.out.println(new String(bytes, 0, bytes.length));
		
		System.out.println("-----------�ظ���ģʽ--------------");
		// 4.�����ظ���ģʽ
		buf.rewind();
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		byte[] bytes2 = new byte[buf.limit()];
		buf.get(bytes2);
		System.out.println(new String(bytes2, 0, bytes2.length));
		
		// 5.clean��ջ����� ������Ȼ����, ֻ�������ݱ�����
		buf.clear();
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		System.out.println((char)buf.get());
		
		
		
		
		
		
		
		
		
		
		
	}
	
	


}
