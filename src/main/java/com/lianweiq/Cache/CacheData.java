package com.lianweiq.Cache;


import java.io.Serializable;
import java.util.Date;

/**
 * @author lianweiq
 * @Date 2018/3/30,
 * @Time 20:14
 * @Description  缓存数据结构体
 */
public class CacheData implements Serializable{

    /**
     * 持续时间,单位分钟
     */
    private int time;

    /**
     * 打卡时间
     */
    private Date date;


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
