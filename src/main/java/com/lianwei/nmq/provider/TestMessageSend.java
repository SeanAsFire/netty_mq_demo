package com.lianwei.nmq.provider;


/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 15:36
 * @Description
 */
public class TestMessageSend extends ProducerClient {

    public TestMessageSend(String host, int port) {
        super(host, port);
    }

    @Override
    String getQueue() {
        return "TestQueue";
    }

    @Override
    Object getMessage() {
        return "TestMessage";
    }
}
