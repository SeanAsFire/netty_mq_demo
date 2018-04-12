package com.lianwei.nmq.provider;


/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 15:36
 * @Description
 */
public class TestMessageSend1 extends ProducerClient {

    public TestMessageSend1(String host, int port) {
        super(host, port);
    }

    @Override
    String getQueue() {
        return "TestQueue-1";
    }

    @Override
    Object getMessage() {
        return "TestMessage-1";
    }
}
