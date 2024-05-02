package com.typotest.codecoverage.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestRedisServiceImpl {

    @Mock
    private RedisTemplate redisTemplateMock;

    @InjectMocks
    private RedisServiceImpl redisService;

    @Before
    public void setUp() {
        redisTemplateMock = mock(RedisTemplate.class);
        redisService = new RedisServiceImpl(redisTemplateMock);
    }

    @Test
    public void testAddKey() {
        String key = "testKey";
        String value = "testValue";

        redisTemplateMock = mock(RedisTemplate.class);
        redisService = new RedisServiceImpl(redisTemplateMock);

        ValueOperations<String, String> valueOperationsMock = mock(ValueOperations.class);
        when(redisTemplateMock.opsForValue()).thenReturn(valueOperationsMock);


        redisService.addKey(key, value);

        verify(valueOperationsMock, times(1)).set(key, value);
    }

    @Test
    public void testUpdateKeyTTL() {
        String key = "testKey";
        Long ttl = 10L;
        TimeUnit timeUnit = TimeUnit.MINUTES;

        when(redisTemplateMock.expire(key, ttl, timeUnit)).thenReturn(true);

        Boolean result = redisService.updateKeyTTL(key, ttl, timeUnit);

        assertEquals(true, result);
        verify(redisTemplateMock, times(1)).expire(key, ttl, timeUnit);
    }

    @Test
    public void testGetValueByKey() {
        String key = "testKey";
        String expectedValue = "testValue";


        ValueOperations<String, String> valueOperationsMock = mock(ValueOperations.class);
        when(redisTemplateMock.opsForValue()).thenReturn(valueOperationsMock);


//        when(redisTemplateMock.opsForValue().get(key)).thenReturn(expectedValue);

        String result = redisService.getValueByKey(key);

        verify(valueOperationsMock, times(1)).get(key);
    }
}
