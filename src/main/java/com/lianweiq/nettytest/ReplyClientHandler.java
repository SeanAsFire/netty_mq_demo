package com.lianweiq.nettytest;

import java.util.Date;

import com.lianweiq.nettytest.model.UnixTime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lianweiq
 * @Date 2018/4/3,
 * @Time 11:55
 * @Description
 */
public class ReplyClientHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf buf;

    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        // 前置处理，将字节数据放到一个4个字节的缓冲区后再进行处理
        // 实际上这是一种懒创建，初始化一个容量避免内存的浪费。当写入字节数目大于初始容量是
        // 会自动分配更大的内存空间
        buf = ctx.alloc().buffer(4);
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * 清空缓冲区数据，主动gc
     * 
     * @param ctx
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        buf.release();
        buf = null;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("应答客户端链接创建时间:" + new Date());
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("应答客户端链接中断时间:" + new Date());
        ctx.fireChannelInactive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        UnixTime time = (UnixTime) msg;

        System.out.println(time);

        ctx.close();
        // ByteBuf m = (ByteBuf) msg;
        // System.out.println("receive message length = " + m.readableBytes());
        // // 此处是一个累计的过程，一直等到该缓冲区被填充完成才会继续处理
        // buf.writeBytes(m);
        // m.release();
        // if (buf.readableBytes() >= 4) {
        // System.out.println("client receive message : " + buf.toString(CharsetUtil.UTF_8));
        // ctx.close();
        // }
        // ByteBuf buf = (ByteBuf) msg;
        // System.out.println("client receive message : " + buf.toString(CharsetUtil.UTF_8));
        // ctx.close();
        // buf.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
