package com.lianwei.nmq.transferbind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.lianwei.nmq.provider.MqMessage;

import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 17:23
 * @Description
 */
public abstract class AbstractCoreTransfer<T> extends SimpleChannelInboundHandler<T> {

    protected static Map<String, Queue<MqMessage>> messageQueue = new HashMap<>();

    protected static Map<String, List<String>> queueHostMap = new HashMap<>();

}
