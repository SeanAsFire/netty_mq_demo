package com.lianweiq.netty;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 16:09
 * @Description
 */
public class NewClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new NewEncoder());
        pipeline.addLast("handler",new NewClientHandler());

    }
}
