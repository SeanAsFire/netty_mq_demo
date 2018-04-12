package com.lianwei.nmq.consumer;


import com.lianwei.nmq.provider.MqMessage;
import com.lianweiq.util.ByteUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 20:19
 * @Description
 */
public class ConsumerServerHandler extends SimpleChannelInboundHandler<MqMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MqMessage message) throws Exception {
        System.out.println("receive message : "+ ByteUtil.bytesToObject(message.getContent()));
    }
}
