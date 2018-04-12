package com.lianwei.nmq.consumer;


/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 17:27
 * @Description
 */
public class Consumer {

    public static void main(String[] args){
        ConsumerClient client = new ConsumerClient("127.0.0.1", 9999) {
            @Override
            String getQueue() {
                return "TestQueue";
            }
        };
        client.consumer();
    }
}
