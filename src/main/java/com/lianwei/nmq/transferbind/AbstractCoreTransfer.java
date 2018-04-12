package com.lianwei.nmq.transferbind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import com.lianwei.nmq.FileUtil;
import com.lianwei.nmq.provider.MqMessage;

import com.lianweiq.util.ByteUtil;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 17:23
 * @Description
 */
public abstract class AbstractCoreTransfer<T> extends SimpleChannelInboundHandler<T> {

    protected  Map<String, Queue<MqMessage>> messageQueue = null;

    protected  Map<String, List<String>> queueHostMap = null;

    public static Map<String,List<String>> queueIpMap = null;

    {
            if (FileUtil.read("messageQueue.txt") == null){
                messageQueue = new ConcurrentHashMap<>();
            }else {
                messageQueue = (Map<String, Queue<MqMessage>>) ByteUtil.bytesToObject(FileUtil.read("messageQueue.txt"));
            }

            if (FileUtil.read("queueHostMap.txt") == null){
                queueHostMap = new ConcurrentHashMap<>();
            }else {
                queueHostMap = (Map<String, List<String>>) ByteUtil.bytesToObject(FileUtil.read("queueHostMap.txt"));
            }

            if (FileUtil.read("queueIpMap.txt") == null){
                queueIpMap = new ConcurrentHashMap<>();
            }else {
                queueIpMap = (Map<String, List<String>>) ByteUtil.bytesToObject(FileUtil.read("queueHostMap.txt"));
            }

        }

}
