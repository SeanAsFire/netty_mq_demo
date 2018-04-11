package com.lianweiq.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 16:11
 * @Description
 */
public class NewEncoder extends MessageToByteEncoder<Message> {
    @Override
    public void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        if (msg == null){
            throw new Exception("未获取到消息内容");
        }

        String msgBody = msg.getMsgBody();
        byte[] b = msgBody.getBytes(Charset.forName("UTF-8"));
        out.writeByte(msg.getType());
        out.writeByte(b.length);
        out.writeBytes(b);
    }
}
