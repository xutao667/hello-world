package com.mayiedu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class Test101702 { 


	public static void main(String[] args) throws IOException {
		test005();
	}

	private static void test001() {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		String str = "abcd1";
		
		buf.put(str.getBytes());
		
		// ������ģʽ
		buf.flip();
		byte[] dst = new byte[buf.limit()];
		buf.get(dst, 0, 2);
		buf.mark();
		System.out.println(new String(dst, 0, dst.length));
		System.out.println(buf.position());
		
		buf.get(dst, 2, 2);
		System.out.println(new String(dst, 2, 2));
		System.out.println(buf.position());
		
		buf.reset();
		
		System.out.println("���ûָ���markλ��...");
		System.out.println(buf.position());
		
		System.out.println(buf.isDirect());
	}
	
	private static void test002() throws IOException {
		
		long start = System.currentTimeMillis();
		
		FileChannel inChannel = FileChannel.open(Paths.get("e://1.mp4"), StandardOpenOption.READ);
		
		FileChannel outChannel = FileChannel.open(Paths.get("e://2.mp4"), StandardOpenOption.CREATE,StandardOpenOption.READ,StandardOpenOption.WRITE);
		
		// �ڴ�ӳ���ļ�
		MappedByteBuffer inMappedByteBuf = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		
		MappedByteBuffer outMappedByteBuf = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		
		// ֱ�ӶԻ������������ݵĶ�д����
		byte[] dsf = new byte[inMappedByteBuf.limit()];
		
		inMappedByteBuf.get(dsf);
		
		outMappedByteBuf.put(dsf);
		
		inChannel.close();
		
		outChannel.close();
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
	}
	
	private static void test003() throws IOException {
		
		long start = System.currentTimeMillis();
		FileInputStream fis = new FileInputStream("e:/1.mp4");
		
		FileOutputStream fos = new FileOutputStream("e:/3.mp4");
		
		// ��ȡͨ��
		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();
		
		// ����ָ����С�Ļ�����
		ByteBuffer buf = ByteBuffer.allocate(1024);
		while(inChannel.read(buf) != -1){
			buf.flip();	// �л�Ϊ��ȡģʽ	
			outChannel.write(buf);
			buf.clear();
		}
		
		outChannel.close();
		inChannel.close();
		
		fos.close();
		fis.close();
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
	}
	
	
	private static void test004() throws IOException {
		
		long start = System.currentTimeMillis();
		
		FileChannel inChannel = FileChannel.open(Paths.get("e:/1.png"), StandardOpenOption.READ);
		
		FileChannel outChannel = FileChannel.open(Paths.get("e:/2.png"), StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE);
		
		// ӳ���ļ�
		MappedByteBuffer inMappedBuff = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuff = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		
		// ֱ�ӶԻ������������ݶ�д����
		byte[] dst = new byte[inMappedBuff.limit()];
		inMappedBuff.get(dst);
		outMappedBuff.put(dst);
		
		outChannel.close();
		inChannel.close();
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
	}
	
	/**
	 * ����ͨ������ļ�����(��ֱ�ӻ�����)
	 * @throws IOException 
	 */
	private static void test005() throws IOException {
		
		long start = System.currentTimeMillis();
		
		FileInputStream fis = new FileInputStream("e:/1.png");
		FileOutputStream fos = new FileOutputStream("e:/3.png");
		
		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		while(inChannel.read(buf) != -1){
			buf.flip();
			outChannel.write(buf);
			buf.clear();
		}

		outChannel.close();
		inChannel.close();
		fos.close();
		fis.close();
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
	}
	
	@Test
	public void test006() throws IOException{	// 780ms
		long start = System.currentTimeMillis();
		
		FileChannel inChannel = FileChannel.open(Paths.get("e:/1.mp4"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("e:/5.mp4"), StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE);
		
		// ӳ���ļ�
		MappedByteBuffer inMappedBuff = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuff = outChannel.map(MapMode.READ_WRITE,  0,  inChannel.size());
		
		// ֱ�ӶԻ������������ݶ�д����
		byte[] dst = new byte[inMappedBuff.limit()]; 
		
		inMappedBuff.get(dst);
		outMappedBuff.put(dst);
		
		outChannel.close();
		inChannel.close();
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
	}
	
	@Test
	public void test007() throws IOException{	// 3544
		
		long start = System.currentTimeMillis();
		
		FileInputStream fis = new FileInputStream("e:/1.mp4");
		FileOutputStream fos = new FileOutputStream("e:/6.mp4");
		
		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();
		
		ByteBuffer buff = ByteBuffer.allocate(1024);
		
		while(inChannel.read(buff) != -1){
			buff.flip();
			outChannel.write(buff);
			buff.clear();
		}
		
		outChannel.close();
		inChannel.close();
		
		fos.close();
		fis.close();
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
	}
	
	@Test
	public void test008() throws IOException{
		
		RandomAccessFile raf1 = new RandomAccessFile("e:/test.txt", "rw");
		
		// 1.��ȡͨ��
		FileChannel channel1 = raf1.getChannel();
		
		// 2.����ָ����С��ָ��������
		ByteBuffer buf1 = ByteBuffer.allocate(100);
		ByteBuffer buf2 = ByteBuffer.allocate(1024);
		
		// 3.��ɢ��ȡ
		ByteBuffer[] bufs = {buf1, buf2};
		
		channel1.read(bufs);
		
		for (ByteBuffer byteBuffer : bufs) {
			byteBuffer.flip();	// �л�Ϊ��ȡģʽ
		}
		
		System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
		System.out.println("---------------��ȡ�ָ���-----------------");
		System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));
		
		// �ۼ�д��
		RandomAccessFile raf2 = new RandomAccessFile("e:/2.txt", "rw");
		
		FileChannel channel2 = raf2.getChannel();
		
		channel2.write(bufs);
		
		channel2.close();
		raf2.close();
		
		channel1.close();
		raf1.close();
	}
	
	@Test
	public void test009() throws CharacterCodingException{
		
		Charset cs1 = Charset.forName("GBK");
		
		// ��ȡ������
		CharsetEncoder ce = cs1.newEncoder();
		// ��ȡ������
		CharsetDecoder cd = cs1.newDecoder();
		
		CharBuffer cBuf = CharBuffer.allocate(1024);
		
		cBuf.put("���Ͽ���ţ��!");
		cBuf.flip();
		
		ByteBuffer bBuf = ce.encode(cBuf);
		
		for(int i=0; i<12; i++){
			System.out.println(bBuf.get());
		}
		
		// ����
		bBuf.flip();
		CharBuffer cBuf2 = cd.decode(bBuf);
		
		System.out.println(cBuf2.toString());
		
		System.out.println("-----------------------------------------");
		
		Charset cs2 = Charset.forName("GBK");
		bBuf.flip();
		CharBuffer cbeef = cs2.decode(bBuf);
		System.out.println(cbeef.toString());
		
		
	}
	
	
	
}
