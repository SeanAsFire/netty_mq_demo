package com.lianweiq.Tx;


import java.io.Serializable;
import java.util.Date;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 17:24
 * @Description
 */
public class Vin implements Serializable {
        /**
         * 员工公钥
         */
        private String pubKey;
        /**
         * 签名日期
         */
        private Date sigDate;
        /**
         * 数据签名
         */
        private String signature;

        public String getPubKey() {
            return pubKey;
        }

        public void setPubKey(String pubKey) {
            this.pubKey = pubKey;
        }

        public Date getSigDate() {
            return sigDate;
        }

        public void setSigDate(Date sigDate) {
            this.sigDate = sigDate;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
}
