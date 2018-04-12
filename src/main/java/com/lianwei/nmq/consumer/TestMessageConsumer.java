package com.lianwei.nmq.consumer;


/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 17:17
 * @Description
 */
public class TestMessageConsumer extends ConsumerClient {
    public TestMessageConsumer(String host, int port) {
        super(host, port);
    }

    @Override
    String getQueue() {
        return "TestQueue";
    }
}
