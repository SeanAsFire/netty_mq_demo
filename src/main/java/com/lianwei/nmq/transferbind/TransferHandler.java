package com.lianwei.nmq.transferbind;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.lianwei.nmq.provider.MqMessage;
import com.lianweiq.util.ByteUtil;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 17:18
 * @Description
 */
public class TransferHandler extends AbstractCoreTransfer<MqMessage> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接建立" + ctx.channel().remoteAddress().toString());
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 断开连接");
        for (Map.Entry<String,List<String>> entry:queueIpMap.entrySet()){
            List<String> ips = entry.getValue();
            if (ips.size() > 0){
                System.out.println("QueueName :" + entry.getKey() + ips);
            }
            ips.remove(ctx.channel().remoteAddress().toString());
        }
        ctx.fireChannelInactive();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, MqMessage msg) throws Exception {
        if ("GetRequest".equals(ByteUtil.bytesToObject(msg.getContent()))){
            doConsumer(ctx,msg);
        }else {
            System.out.println(msg.getContent().toString());
            doProducer(ctx,msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public void doProducer(ChannelHandlerContext ctx, MqMessage msg){
        List<String> ipList = queueIpMap.get(msg.getQueueName());
        if (ipList == null ||ipList.size() == 0 ){
            ipList = new ArrayList<>();
            ipList.add(ctx.channel().remoteAddress().toString());
            queueIpMap.put(msg.getQueueName(),ipList);
        }else {
            ipList.add(ctx.channel().remoteAddress().toString());
        }
        Queue<MqMessage> mqMessageQueue = messageQueue.get(msg.getQueueName());
        if (mqMessageQueue == null) {
            mqMessageQueue = new LinkedBlockingQueue<>(1);
            messageQueue.put(msg.getQueueName(), mqMessageQueue);

        }
        boolean isFull = messageQueue.get(msg.getQueueName()).offer(msg);{
            if (!isFull){
                System.out.println("队列满了");
            }
        }
        System.out.println((ByteUtil.bytesToObject(msg.getContent())));
    }

    public void doConsumer(ChannelHandlerContext ctx, MqMessage msg){
        List<String> ipList = queueHostMap.get(msg.getQueueName());
        if (ipList == null ||ipList.size() == 0 ){
            ipList = new ArrayList<>();
            ipList.add(ctx.channel().remoteAddress().toString());
            queueHostMap.put(msg.getQueueName(),ipList);
        }else {
            ipList.add(ctx.channel().remoteAddress().toString());
        }

        String address = ctx.channel().remoteAddress().toString();

        String host = address.replace("/","").split(":")[0];

        Queue<MqMessage> mqMessageQueue = messageQueue.get(msg.getQueueName());
        List<MqMessage> messageList = new ArrayList<>();
        if (mqMessageQueue == null) {
            MqMessage message = new MqMessage("没有提供者");
            message.setQueueName(msg.getQueueName());
            messageList.add(message);
        }else {
            for (int i =0;i<100;i++){
                MqMessage mqMessage =  mqMessageQueue.poll();
                if (mqMessage == null){
                    break;
                }
                messageList.add(mqMessage);
            }
        }
        TransferClient client = new TransferClient(host,messageList);
        client.run();

    }
}
