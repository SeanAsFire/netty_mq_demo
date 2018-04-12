package com.lianwei.nmq.transferbind;


import com.lianwei.nmq.MqMessageDecoder;
import com.lianwei.nmq.MqMessageEncoder;
import com.lianwei.nmq.consumer.ConsumerHandler;
import com.lianwei.nmq.provider.MqMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.List;

/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 18:46
 * @Description 传输数据客户端
 */
public class TransferClient implements Runnable {

    private String host;

    private List<MqMessage> mqMessageList;

    @Override
    public void run() {
        consumer();
    }

    public void consumer() {

        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new MqMessageEncoder());
                pipeline.addLast(new MqMessageDecoder());
                pipeline.addLast(new ConsumerHandler());
            }
        });
        try {
            ChannelFuture future = bootstrap.connect(host, 7777).sync();
            for (MqMessage message:mqMessageList) {
                future.channel().writeAndFlush(message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public TransferClient(String host, List<MqMessage> mqMessageList) {
        this.host = host;
        this.mqMessageList = mqMessageList;
    }
}
