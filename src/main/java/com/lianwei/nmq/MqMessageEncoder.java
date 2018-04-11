package com.lianwei.nmq;

import com.lianwei.nmq.provider.MqMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 16:10
 * @Description
 */
public class MqMessageEncoder extends MessageToByteEncoder<MqMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MqMessage msg, ByteBuf out) throws Exception {

        if (msg.getContent().length == 0) {
            return;
        }
        out.writeInt(msg.getVersion());
        out.writeBytes(msg.getUuid().getBytes());
        out.writeInt(msg.getQueueLen());
        out.writeBytes(msg.getQueueName().getBytes());
        out.writeInt(msg.getContentLen());
        out.writeBytes(msg.getContent());
    }
}
