package com.typotest.codecoverage.service;


import java.util.concurrent.TimeUnit;

public interface RedisService {
    void addKey(String key, String value);

    Boolean updateKeyTTL(String key, Long ttl, TimeUnit timeUnit);

    String getValueByKey(String key);
}