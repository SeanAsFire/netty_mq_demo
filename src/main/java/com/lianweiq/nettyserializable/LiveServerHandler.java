package com.lianweiq.nettyserializable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * @author lianweiq
 * @Date 2018/4/4,
 * @Time 15:11
 * @Description
 */
public class LiveServerHandler extends SimpleChannelInboundHandler<LiveMessage> {

    private static Map<Integer, LiveChannelCache> channelMap = new HashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LiveMessage msg) throws Exception {
        Channel channel = ctx.channel();
        final int hashcode = channel.hashCode();

        // 新建立的连接
        if (!channelMap.containsKey(hashcode)) {
            channel.closeFuture().addListener(future -> {
                System.out.println("remove client " + channel.remoteAddress());
                channelMap.remove(hashcode);
            });
            ScheduledFuture scheduledFuture = ctx.executor().schedule(() -> {
                System.out.println("heart beat start!");
                channel.close();
            }, 10, TimeUnit.SECONDS);
            channelMap.put(hashcode, new LiveChannelCache(channel, scheduledFuture));
        }

        switch (msg.getType()) {
        case 0:
            LiveChannelCache channelCache = channelMap.get(hashcode);
            // 重置关闭连接时间
            ScheduledFuture scheduledFuture = ctx.executor().schedule(() -> channel.close(), 5, TimeUnit.SECONDS);
            channelCache.getScheduledFuture().cancel(true);
            channelCache.setScheduledFuture(scheduledFuture);
            ctx.writeAndFlush(msg);
            break;
        case 1:
            channelMap.entrySet().stream().forEach(entry -> {
                Channel ch = entry.getValue().getChannel();
                ch.writeAndFlush(msg);
            });
            break;
        default:
            throw new IllegalStateException("Invalid message type!");
        }

    }
}
