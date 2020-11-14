package com.mayiedu;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Test14 {

	public static void main(String[] args) throws IOException {
		
		System.out.println("·þÎñÆ÷¶ËÆô¶¯...");
		
		ServerSocketChannel sChannel = ServerSocketChannel.open();
		
		sChannel.configureBlocking(false);
		
		sChannel.bind(new InetSocketAddress(8080));
		
		Selector selector = Selector.open();
		
		sChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(selector.select() > 0) {
			
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			
			while(it.hasNext()) {
				
				SelectionKey sk = it.next();
				
				if(sk.isAcceptable()) {
					
					SocketChannel socketChannle = sChannel.accept();
					
					socketChannle.configureBlocking(false);
					
					System.out.println("^-^");
					
					socketChannle.register(selector, SelectionKey.OP_READ);
				} else if(sk.isReadable()) {
					
					SocketChannel socketChannel = (SocketChannel) sk.channel();
					
					ByteBuffer buf = ByteBuffer.allocate(1024);
					
					int len = 0;
					
					while((len = socketChannel.read(buf)) > 0) {
						buf.flip();
						System.out.println(new String(buf.array(), 0, len));
						buf.clear();
					}
				}
				it.remove();
			}
			
			
			
		}
		
		
	}
}
