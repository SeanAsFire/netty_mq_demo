package com.lianweiq.nettyserializable;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author lianweiq
 * @Date 2018/4/4,
 * @Time 15:39
 * @Description
 */
public class LiveMessageEncoder extends MessageToByteEncoder<LiveMessage> {
    /**
     * Encode a message into a {@link ByteBuf}. This method will be called for each written message that can be handled
     * by this encoder.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link MessageToByteEncoder} belongs to
     * @param msg the message to encode
     * @param out the {@link ByteBuf} into which the encoded message will be written
     * @throws Exception is thrown if an error occurs
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, LiveMessage msg, ByteBuf out) throws Exception {

        if (msg == null) {
            System.out.println("Null message!");
        }

        out.writeByte(msg.getType());
        out.writeInt(msg.getLength());
        if (msg.getType() == 1) {
            out.writeBytes(msg.getContent().getBytes());
        }
    }
}
