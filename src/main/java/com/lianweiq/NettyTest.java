package com.lianweiq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 10:44
 * @Description netty测试
 */
public class NettyTest {

    private int port;
    private String address;

    public NettyTest(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInboundHandlerAdapter());

        try {
            Channel channel = bootstrap.remoteAddress(address, port).connect().sync().channel();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // 等待控制台输入
            for (;;) {
                String msg = reader.readLine();

                if (msg == null) {
                    continue;
                }
                channel.write(msg + "\r\n");
                channel.flush();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            group.shutdown();
        }
    }

    public static void main(String[] args) {
        NettyTest nettyTest = new NettyTest(7788, "127.0.0.1");
        nettyTest.start();
    }

}
