package com.lianwei.nmq.transferbind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import com.lianwei.nmq.provider.MqMessage;

import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 17:23
 * @Description
 */
public abstract class AbstractCoreTransfer<T> extends SimpleChannelInboundHandler<T> {

    protected  Map<String, Queue<MqMessage>> messageQueue = new ConcurrentHashMap<>();

    protected  Map<String, List<String>> queueHostMap = new ConcurrentHashMap<>();

    public static Map<String,List<String>> queueIpMap = new ConcurrentHashMap<>();

}
