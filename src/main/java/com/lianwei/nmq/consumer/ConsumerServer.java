package com.lianwei.nmq.consumer;


import com.lianwei.nmq.MqMessageDecoder;
import com.lianwei.nmq.MqMessageEncoder;
import com.lianwei.nmq.transferbind.TransferHandler;
import com.lianweiq.nettyheartbeat.HeartBeatClientHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 18:07
 * @Description
 */
public class ConsumerServer {

    private final int port;

    public ConsumerServer(int port) {
        this.port = port;
    }


    public void start(){

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
                            pipeline.addLast("handler", new ConsumerServerHandler());
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

    public static void main(String[] args){
        ConsumerServer server = new ConsumerServer(7777);
        server.start();

    }
}
