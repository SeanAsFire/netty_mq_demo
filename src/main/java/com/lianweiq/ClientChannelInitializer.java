package com.lianweiq;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;



/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 11:01
 * @Description
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        ByteBuf delimiter = Unpooled.copiedBuffer("/t".getBytes());

        //pipeline.addLast("framer",new DelimiterBasedFrameDecoder(2048,delimiter));
        pipeline.addLast(new FixedLengthFrameDecoder(1024));
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());

        //注册自己的handler
        pipeline.addLast("handler",new NettyTestHandler());
    }
}
