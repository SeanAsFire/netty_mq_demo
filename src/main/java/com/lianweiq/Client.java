package com.lianweiq;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Hello world!
 *
 */
public class Client
{
    public static void main( String[] args )
    {

        try {
            SocketAddress address = new InetSocketAddress("127.0.0.1",8080);
            SocketChannel socketChannel = SocketChannel.open(address);
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            byteBuffer.put("1237236r67teyfhshdbvhgdvbfd".getBytes());
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "Hello World!" );
    }
}
