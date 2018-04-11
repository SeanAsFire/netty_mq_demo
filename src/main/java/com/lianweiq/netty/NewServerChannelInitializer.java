package com.lianweiq.netty;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;

/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 15:45
 * @Description
 */
public class NewServerChannelInitializer extends ChannelInitializer<ServerSocketChannel> {
    private  final int MAX_FRAME_LENGTH;
    private  final int LENGTH_FIELD_LENGTH;
    private  final int LENGTH_FIELD_OFFSET;
    private  final int LENGTH_ADJUSTMENT;
    private  final int INITIAL_BYTES_TO_STRIP;

    public NewServerChannelInitializer(int MAX_FRAME_LENGTH, int LENGTH_FIELD_LENGTH, int LENGTH_FIELD_OFFSET, int LENGTH_ADJUSTMENT, int INITIAL_BYTES_TO_STRIP) {
        this.MAX_FRAME_LENGTH = MAX_FRAME_LENGTH;
        this.LENGTH_FIELD_LENGTH = LENGTH_FIELD_LENGTH;
        this.LENGTH_FIELD_OFFSET = LENGTH_FIELD_OFFSET;
        this.LENGTH_ADJUSTMENT = LENGTH_ADJUSTMENT;
        this.INITIAL_BYTES_TO_STRIP = INITIAL_BYTES_TO_STRIP;
    }

    @Override
    public void initChannel(ServerSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast();

        pipeline.addLast("handler",new NewServerHandler());


    }
}
