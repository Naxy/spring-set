package com.wxy.spring_boot.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {
	private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
	private static ByteBuffer receiverBuffer = ByteBuffer.allocate(1024);
	private Selector selector;
	private SocketChannel socketChannel;
	private int port = 5678;

	public NIOClient(int port) throws IOException {
		socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		this.port = port;
	}

	public void connect() throws IOException {
		socketChannel.connect(new InetSocketAddress(port));
		Set<SelectionKey> selectionKeys;
		Iterator<SelectionKey> iterator;
		SelectionKey selectionKey;
		while(true){
			selector.select();
			selectionKeys = selector.selectedKeys();
			iterator = selectionKeys.iterator();
			while(iterator.hasNext()){
				selectionKey = iterator.next();
				handleKey(selectionKey);
			}
			selectionKeys.clear();
		}
	}

	private void handleKey(SelectionKey selectionKey) throws IOException, ClosedChannelException {
		SocketChannel client;
		String receiveText;
		String sendText;
		int count;
		if(selectionKey.isConnectable()){
			System.out.println("client connect ..");
			client = (SocketChannel) selectionKey.channel();
			if(client.isConnectionPending()){
				client.finishConnect();
				System.out.println("connect finished...");
				sendBuffer.clear();
				sendBuffer.put("Hello,Server!".getBytes());
				sendBuffer.flip();
				client.write(sendBuffer);
			}
			client.register(selector, SelectionKey.OP_READ);
		}else if(selectionKey.isReadable()){
			client = (SocketChannel) selectionKey.channel();
			receiverBuffer.clear();
			count = client.read(receiverBuffer);
			if(count > 0){
				receiveText = new String(receiverBuffer.array(),0,count);
				System.out.println("client receive server's data:" + receiveText);
				client.register(selector, SelectionKey.OP_WRITE);
			}
		}else if(selectionKey.isWritable()){
			sendBuffer.clear();
			client = (SocketChannel) selectionKey.channel();
			sendText = "msg from client--";
			sendBuffer.put(sendText.getBytes());
			sendBuffer.flip();
			client.write(sendBuffer);
			System.out.println("client sending server data:" + sendText);
			client.register(selector, SelectionKey.OP_READ);
		}
	}
	public static void main(String[] args) throws IOException {
		NIOClient client = new NIOClient(5678);
		client.connect();
	}
}
