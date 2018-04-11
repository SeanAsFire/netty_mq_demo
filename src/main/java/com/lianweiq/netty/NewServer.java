package com.lianweiq.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 15:00
 * @Description
 */
public class NewServer {

    private static final int MAX_FRAM_LENGTH = 1024*1024;
    private static final int LENGTH_FIELD_LENGTH = 4;
    private static final int LENGTH_FIELD_OFFSET = 1;
    private static final int LENGTH_ADJUSTMENT = 0;



    private int port;

    public NewServer(int port) {
        this.port = port;
    }

    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap sbs = new ServerBootstrap()
                    .group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new NewServerChannelInitializer(MAX_FRAM_LENGTH,LENGTH_FIELD_LENGTH,LENGTH_FIELD_OFFSET,LENGTH_ADJUSTMENT,0))
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture future = sbs.localAddress(port).bind().sync();

            System.out.println("Server start listen at " + port);

            future.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            bossGroup.shutdown();
            workerGroup.shutdown();

        }

    }

    public static void main(String[] args){
        NewServer server = new NewServer(7788);
        server.start();

    }
}


