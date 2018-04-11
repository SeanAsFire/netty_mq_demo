package com.lianweiq;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 11:20
 * @Description
 */
public class ServerChannelInitializer extends ChannelInitializer<ServerSocketChannel> {
    @Override
    public void initChannel(ServerSocketChannel serverSocketChannel) throws Exception {
        ChannelPipeline pipeline = serverSocketChannel.pipeline();

        //此处注意，客户端和服务器的规范必须一致！！
        ByteBuf delimiter = Unpooled.copiedBuffer("\t".getBytes());
        //设置消息最大长度，设置消息结尾标识符号
      //  pipeline.addLast("framer",new DelimiterBasedFrameDecoder(2048,delimiter));
        //定长解析
        pipeline.addLast(new FixedLengthFrameDecoder(1024));
        pipeline.addLast("encoder",new StringEncoder());
        pipeline.addLast("decoder",new StringDecoder());

        //服务端处理handler

        pipeline.addLast("handler",new NettyTestServerHandler());
    }
}
