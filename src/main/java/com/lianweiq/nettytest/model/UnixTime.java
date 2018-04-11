package com.lianweiq.nettytest.model;

import java.util.Date;

/**
 * @author lianweiq
 * @Date 2018/4/3,
 * @Time 14:32
 * @Description
 */
public class UnixTime {

    private final long value;

    public UnixTime(long value) {
        this.value = value;
    }

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((this.value - 2208988800L) * 1000L).toString();
    }
}
