package com.lianweiq.nettyserializable;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

/**
 * @author lianweiq
 * @Date 2018/4/4,
 * @Time 15:41
 * @Description
 */
public class LiveMessageDecoder extends ByteToMessageDecoder {
    /**
     * Decode the from one {@link ByteBuf} to an other. This method will be called till either the input {@link ByteBuf}
     * has nothing to read when return from this method or till nothing was read from the input {@link ByteBuf}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
     * @param in the {@link ByteBuf} from which to read data
     * @param out the {@link List} to which decoded messages should be added
     * @throws Exception is thrown if an error occurs
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        LiveMessage message = new LiveMessage();
        if (in.readableBytes() < 5) {
            System.out.println("Invalid message!");
            return;
        }
        byte type = in.readByte();
        in.skipBytes(4);
        int length = in.readableBytes();
        byte[] c = new byte[length];
        in.readBytes(c);
        String content = new String(c, CharsetUtil.UTF_8.name());
        message.setType(type);
        message.setLength(length);
        message.setContent(content);
        out.add(message);
    }
}
