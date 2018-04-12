package com.lianwei.nmq.provider;


/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 15:48
 * @Description
 */
public class Start {

    public static void main(String[] args){
        ProducerClient client = new TestMessageSend("127.0.0.1",9999);
        ProducerClient client1 = new TestMessageSend1("127.0.0.1",9999);
        client.send();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client1.send();
    }
}
