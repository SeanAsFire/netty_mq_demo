package com.lianweiq.nettytest;

import java.util.Date;

import com.lianweiq.nettytest.model.UnixTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lianweiq
 * @Date 2018/4/3,
 * @Time 11:19
 * @Description
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        System.out.println("时间服务链接创建时间:" + new Date());
        ctx.writeAndFlush(new UnixTime());

        // final ByteBuf buf = ctx.alloc().buffer();
        //
        // buf.writeBytes(new Date().toString().getBytes());
        // final ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        // f.addListener(new ChannelFutureListener() {
        // @Override
        // public void operationComplete(ChannelFuture future) throws Exception {
        // assert future == f;
        // ctx.close();
        // }
        // });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("时间服务链接中断时间:" + new Date());
        ctx.fireChannelInactive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.channel().close();
    }
}
