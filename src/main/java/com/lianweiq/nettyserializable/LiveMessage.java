package com.lianweiq.nettyserializable;

/**
 * @author lianweiq
 * @Date 2018/4/4,
 * @Time 14:58
 * @Description
 */
public class LiveMessage {

    private byte type;

    private int length;

    private String content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LiveMessage{" + "type=" + type + ", length=" + length + ", content='" + content + '\'' + '}';
    }
}
