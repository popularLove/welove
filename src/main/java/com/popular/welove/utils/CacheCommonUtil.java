package com.popular.welove.utils;

import com.alibaba.fastjson.JSON;
import com.popular.welove.common.ObjectRestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lidesong
 * @Description 解析缓存工具类
 * @Date 12:53 2020-03-24
 * @Param
 * @return
 **/
@Component
public class CacheCommonUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public ObjectRestResponse getCacheForObjectRestResponse(String key) {
        if (redisTemplate.hasKey(key)) {
            String result = String.valueOf(redisTemplate.opsForValue().get(key));
            if (StringUtils.isNotEmpty(result) && !"null".equals(result)) {
                ObjectRestResponse response = JSON.parseObject(result, ObjectRestResponse.class);
                if (response.getStatus() == 200) {
                    return response;
                } else {
                    List<String> keys = new ArrayList<>();
                    keys.add(key);
                    redisTemplate.delete(keys);
                }
            } else {
                List<String> keys = new ArrayList<>();
                keys.add(key);
                redisTemplate.delete(keys);
            }
        }
        return null;
    }

    public String getCacheForString(String key) {
        if (redisTemplate.hasKey(key)) {
            String result = String.valueOf(redisTemplate.opsForValue().get(key));
            if (StringUtils.isNotEmpty(result) && !"null".equals(result)) {
                return result;
            } else {
                List<String> keys = new ArrayList<>();
                keys.add(key);
                redisTemplate.delete(keys);
            }
        }
        return null;
    }

}
