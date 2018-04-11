package com.lianweiq.Tx;


import java.io.Serializable;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 17:24
 * @Description
 */
public class Vout implements Serializable {
        /**
         * 公钥hash
         */
        private String pubHash;
        /**
         * 捐献时间,单位：分钟
         */
        private int donateTime;

        public String getPubHash() {
            return pubHash;
        }

        public void setPubHash(String pubHash) {
            this.pubHash = pubHash;
        }

        public int getDonateTime() {
            return donateTime;
        }

        public void setDonateTime(int donateTime) {
            this.donateTime = donateTime;
        }
}
