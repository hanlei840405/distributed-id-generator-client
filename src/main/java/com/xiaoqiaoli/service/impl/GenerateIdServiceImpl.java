package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.service.client.GenerateIdRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hanlei6 on 2016/7/20.
 */
@Service("generateIdService")
public class GenerateIdServiceImpl implements GenerateIdRemoteService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String get(String application, String module) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String key = application.concat("-").concat(module).concat("-").concat(sdf.format(new Date())).concat("-");
        long value = redisTemplate.opsForValue().increment(key, 1);
        return key.concat(String.format("%08d", value));
    }
}
