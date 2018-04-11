package com.lianweiq;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 11:11
 * @Description
 */
public class NettyTestServer {

    private int port;

    public NettyTestServer(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap().group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer());

        try {

            ChannelFuture future = server.localAddress(port).bind().sync();

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdown();
            workGroup.shutdown();
        }
    }

    public static void main(String[] args) {
        NettyTestServer nettyTestServer = new NettyTestServer(7788);
        nettyTestServer.start();
    }
}
