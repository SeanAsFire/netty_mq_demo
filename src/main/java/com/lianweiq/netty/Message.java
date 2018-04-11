package com.lianweiq.netty;


/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 14:56
 * @Description
 */
public class Message {

    private byte type;

    private int length;

    private String msgBody;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }


    public Message(byte type, int length, String msgBody) {
        this.type = type;
        this.length = length;
        this.msgBody = msgBody;
    }
}
