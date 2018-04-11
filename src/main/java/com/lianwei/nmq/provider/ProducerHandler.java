package com.lianwei.nmq.provider;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 15:42
 * @Description
 */
public class ProducerHandler extends ChannelInboundHandlerAdapter {

    // 服务端中断
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
