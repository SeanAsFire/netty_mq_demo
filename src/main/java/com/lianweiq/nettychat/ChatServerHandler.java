package com.lianweiq.nettychat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author lianweiq
 * @Date 2018/4/3,
 * @Time 15:27
 * @Description
 */
public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * 新加入聊天室的人，记录下来
     * 
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("[SERVER] - " + channel.remoteAddress() + "加入\n");
        channels.add(channel);
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * @param ctx
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channels.writeAndFlush("[SERVER] - " + channel.remoteAddress() + "离开\n");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel income = ctx.channel();
        ByteBuf buf = (ByteBuf) msg;
        String m = buf.toString(CharsetUtil.UTF_8);
        for (Channel channel : channels) {
            if (channel != income) {
                channel.writeAndFlush("[" + income.remoteAddress() + "]" + m + "\n");
            } else {
                channel.writeAndFlush("[you]" + m + "\n");
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("ChatClient" + channel.remoteAddress() + "在线");
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[ChatClient]" + channel.remoteAddress() + "不在线");
        ctx.fireChannelInactive();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("[ChatClient]" + ctx.channel().remoteAddress() + "异常");
        ctx.close();
    }
}
