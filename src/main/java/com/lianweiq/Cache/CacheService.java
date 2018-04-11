package com.lianweiq.Cache;


import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lianweiq.BlockChain.BlockChainService;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * @author lianweiq
 * @Date 2018/3/30,
 * @Time 20:17
 * @Description 本地缓存服务类
 */
public class CacheService {


    private LoadingCache<String,Optional<CacheData>> workTimeCache;

    private BlockChainService blockChainService = new BlockChainService();

    public CacheService() {
        this.workTimeCache = buildTimeCache();
    }


    /**
     * 使用本地缓存保存临时打卡数据
     * @return 打卡时间缓存
     */
    private  LoadingCache<String,Optional<CacheData>> buildTimeCache(){
        LoadingCache<String,Optional<CacheData>> timeCache = CacheBuilder.newBuilder()
                .maximumSize(2000)
                .build(new CacheLoader<String, Optional<CacheData>>() {
                    @Override
                    public Optional<CacheData> load(String key) throws Exception {
                        if (key == null || key.length() <= 0){
                            return Optional.absent();
                        }

                        return Optional.of(blockChainService.getTimeData(key));
                    }
                });

        return timeCache;
    }


    public boolean  updateTimeData(String pubKey){

            CacheData data = getTimeData(pubKey);
            if (data == null){
                //查无此人，不给开门
                return false;
            }
            //更新工作时间
            Date now = new Date();
            Date beforeDate = data.getDate();
            data.setDate(now);
            data.setTime((int) (data.getTime() + (now.getTime() - beforeDate.getTime())/(1000*60)));
            workTimeCache.put(pubKey,Optional.of(data));
            return true;
    }


    public CacheData  getTimeData(String pubKey){
        try {
            Optional<CacheData> timeCache = workTimeCache.get(pubKey);
            if (timeCache.isPresent()){
                //查无此人
                return null;
            }
            CacheData data = timeCache.orNull();
            if (data == null){
                return null;
            }
            return data;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
