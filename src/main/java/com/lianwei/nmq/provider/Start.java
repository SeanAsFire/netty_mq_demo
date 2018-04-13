package com.lianwei.nmq.provider;


/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 15:48
 * @Description
 */
public class Start {

    public static void main(String[] args) throws InterruptedException {
        ProducerClient client = new TestMessageSend("127.0.0.1",9999);
        for (int i= 0;i<500;i++){
            client.send();
            Thread.sleep(10);
        }
    }
}
