package com.lianwei.nmq.consumer;

import com.lianwei.nmq.MqMessageDecoder;
import com.lianwei.nmq.MqMessageEncoder;
import com.lianwei.nmq.provider.MqMessage;
import com.lianwei.nmq.provider.ProducerHandler;
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
public abstract class ConsumerClient {


        private  int port;

        private  String host;

        public ConsumerClient() {
        }

        public ConsumerClient(String host, int port) {
            this.host = host;
            this.port = port;
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
                ChannelFuture future = bootstrap.connect(host, port).sync();
               future.channel().writeAndFlush(orgaMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
        }


        abstract String getQueue();

        public MqMessage orgaMessage() {
            MqMessage message = new MqMessage();
            message.setQueueName(getQueue());
            return message;
        }

}
