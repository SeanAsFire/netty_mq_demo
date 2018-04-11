package com.lianwei.nmq.transferbind;

import com.lianwei.nmq.MqMessageDecoder;
import com.lianwei.nmq.MqMessageEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 15:41
 * @Description
 */
public class TransferBindServer {

    private final int port;

    public TransferBindServer(int port) {
        this.port = port;
    }

    public void start() {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.channel(NioServerSocketChannel.class).group(bossGroup, workerGroup)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("encoder", new MqMessageEncoder());
                        pipeline.addLast("decoder", new MqMessageDecoder());
                        pipeline.addLast("handler", new TransferHandler());
                    }
                }).option(ChannelOption.SO_BACKLOG, 124).childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        TransferBindServer server = new TransferBindServer(9999);

        server.start();

    }
}
