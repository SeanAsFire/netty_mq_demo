package com.lianwei.nmq.provider;

import com.lianweiq.util.ByteUtil;
import com.lianweiq.util.Sha256Hash;

/**
 * @author lianweiq
 * @Date 2018/4/11,
 * @Time 15:45
 * @Description
 */
public class MqMessage {

    private int version = 1;

    // 64个字节
    private String uuid;

    private int queueLen;

    private String queueName;

    private int contentLen;

    private byte[] content;

    // 初始化
    public MqMessage(Object object) {
        byte[] bytes = ByteUtil.objectToBytes(object);
        this.uuid = Sha256Hash.sha256Hash(bytes);
        this.contentLen = bytes.length;
        this.content = bytes;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getContentLen() {
        return contentLen;
    }

    public void setContentLen(int contentLen) {
        this.contentLen = contentLen;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueLen = queueName.getBytes().length;
        this.queueName = queueName;
    }

    public MqMessage() {
    }

    public int getQueueLen() {
        return queueLen;
    }
}
