package com.lianwei.nmq.provider;

import com.lianwei.nmq.MqMessageDecoder;
import com.lianwei.nmq.MqMessageEncoder;
import com.lianweiq.util.ByteUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 15:41
 * @Description
 */
public abstract class ProducerClient {

    private  int port;

    private  String host;

    public ProducerClient() {
    }

    public ProducerClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send() {

        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new MqMessageEncoder());
                pipeline.addLast(new MqMessageDecoder());
                pipeline.addLast(new ProducerHandler());
            }
        });
        try {
            ChannelFuture future = bootstrap.connect(host, port).sync();
            ChannelFuture future1 = future.channel().writeAndFlush(orgaMessage());
            future1.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future == future1 && future.isSuccess()) {
                        System.out.println("监听器监听到通道完成通信，关闭通道");
                        future1.channel().close().sync();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }


    abstract String getQueue();

    abstract Object getMessage();

    public MqMessage orgaMessage() {
        MqMessage message = new MqMessage(getMessage());
        message.setQueueName(getQueue());
        System.out.println(ByteUtil.bytesToObject(message.getContent()));
        return message;
    }

}
