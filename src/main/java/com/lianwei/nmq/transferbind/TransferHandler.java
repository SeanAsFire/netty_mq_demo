package com.lianwei.nmq.transferbind;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.lianwei.nmq.provider.MqMessage;
import com.lianweiq.util.ByteUtil;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 17:18
 * @Description
 */
public class TransferHandler extends AbstractCoreTransfer<MqMessage> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接建立" + ctx.channel().remoteAddress());
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, MqMessage msg) throws Exception {
        if (messageQueue.get(msg) == null) {
            Queue<MqMessage> mqMessageQueue = new LinkedBlockingQueue<>();
            messageQueue.put(msg.getQueueName(), mqMessageQueue);

        }
        messageQueue.get(msg.getQueueName()).add(msg);
        System.out.println((ByteUtil.bytesToObject(msg.getContent())));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
