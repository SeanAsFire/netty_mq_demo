package com.lianweiq.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 16:04
 * @Description
 */
public class NewClient {

    private String address;

    private int port;

    public NewClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void start(){

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new NewClientChannelInitializer());

        try {
            ChannelFuture future = bootstrap.remoteAddress(address,port).connect().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdown();
        }

    }

    public static void main(String[] args){
        NewClient client = new NewClient("127.0.0.1",7788);

        client.start();


    }
}
