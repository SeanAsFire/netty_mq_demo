package com.lianwei.nmq;

import java.util.List;

import com.lianwei.nmq.provider.MqMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 16:10
 * @Description
 */
public class MqMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 72) {
            return;
        }
        int readIndex = in.readerIndex();
        int writeIndex = in.writerIndex();

        int version = in.readInt();

        String uuid = in.readBytes(64).toString(CharsetUtil.UTF_8);

        int queueLen = in.readInt();

        if (in.readableBytes() < queueLen){
            in.readerIndex(readIndex);
            in.writerIndex(writeIndex);
            return;
        }
        String queueName  = in.readBytes(queueLen).toString(CharsetUtil.UTF_8);

        int contentLen = in.readInt();

        if (in.readableBytes() < contentLen) {
            in.readerIndex(readIndex);
            in.writerIndex(writeIndex);
            return;
        }
        MqMessage message = new MqMessage();
        message.setVersion(version);
        message.setUuid(uuid);
        message.setContentLen(contentLen);
        byte[] content = new byte[contentLen];
        in.readBytes(content);
        message.setContent(content);
        message.setQueueName(queueName);
        out.add(message);
    }
}
