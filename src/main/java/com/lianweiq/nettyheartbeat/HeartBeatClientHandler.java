package com.lianweiq.nettyheartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author lianweiq
 * @Date 2018/4/3,
 * @Time 18:43
 * @Description 服务端心跳处理器
 */
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Receive message from server,msg = " + buf.toString(CharsetUtil.UTF_8));
    }
}
