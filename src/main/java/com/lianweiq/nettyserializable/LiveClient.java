package com.lianweiq.nettyserializable;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lianweiq
 * @Date 2018/4/4,
 * @Time 16:27
 * @Description
 */
public class LiveClient {

    private String address;

    private int port;

    public LiveClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {

                ChannelPipeline pipeline = ch.pipeline();

                pipeline.addLast("decoder", new LiveMessageDecoder());
                pipeline.addLast("encoder", new LiveMessageEncoder());

                pipeline.addLast(new LiveClientHandler());
            }
        });

        try {
            Channel channel = bootstrap.connect(address, port).sync().channel();
            LiveMessage message = new LiveMessage();
            message.setType((byte) 0);
            // message.setContent("你好，我是客户端！");
            message.setLength(0);
            channel.writeAndFlush(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        LiveClient liveClient = new LiveClient("127.0.0.1", 9999);
        liveClient.start();

    }
}
