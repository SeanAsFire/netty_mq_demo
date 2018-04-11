package com.lianweiq;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author lianweiq
 * @Date 2018/3/27,
 * @Time 21:08
 * @Description
 */
public class ServerTest {

    public static void main(String[] args){

        try {
            SocketAddress address = new InetSocketAddress("127.0.0.1",8080);
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open().bind(address);

            SocketChannel socketChannel = serverSocketChannel.accept();

            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            socketChannel.read(byteBuffer);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.println(byteBuffer.get());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
