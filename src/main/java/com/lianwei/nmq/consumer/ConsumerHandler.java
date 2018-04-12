package com.lianwei.nmq.consumer;


import com.lianwei.nmq.provider.MqMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 17:13
 * @Description
 */
public  class ConsumerHandler extends SimpleChannelInboundHandler<MqMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MqMessage mqMessage) throws Exception {
        System.out.println("receive message " + mqMessage.getContent());
    }

}
