package com.lianweiq.nettychat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author lianweiq
 * @Date 2018/4/3,
 * @Time 16:49
 * @Description
 */
public class ChatClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String m = buf.toString(CharsetUtil.UTF_8);
        System.out.println(m);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("[ChatClient]" + ctx.channel().remoteAddress() + "异常");
        cause.printStackTrace();
        ctx.close();
    }

}
