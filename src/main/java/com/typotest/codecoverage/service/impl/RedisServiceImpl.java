package com.typotest.codecoverage.service.impl;

import com.typotest.codecoverage.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void addKey(String key, String value) {
        log.info("addKey: {} {}", key, value);
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Boolean updateKeyTTL(String key, Long ttl, TimeUnit timeUnit) {
        log.info("updateKeyTTL: {} {} {}", key, ttl, timeUnit);
        Boolean set = redisTemplate.expire(key, ttl, timeUnit);
        log.info("login: set userTokenValue ttl: {}", set);
        return set;
    }

    @Override
    public String getValueByKey(String key) {
        log.info("getValueByKey: {}", key);
        String value = (String) redisTemplate.opsForValue().get(key);
        log.info("getValueByKey: value {}", value);
        return value;
    }
}
