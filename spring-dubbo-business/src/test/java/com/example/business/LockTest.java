package com.example.business;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Package: com.example.business
 * User: twk
 * Date: 2024/6/3
 * Time: 16:13
 * Description:
 */
public class LockTest {

    private Config config;

    private void init(){
        config = new Config();
        config.useSingleServer().setPassword("12345").setAddress("redis://127.0.0.1:6379");
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        lockTest.init();
        RedissonClient redissonClient = Redisson.create(lockTest.config);
        lockTest.lock(redissonClient);
    }

    public void rateLimiter(RedissonClient redissonClient){
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("rateLimiter");
        rateLimiter.trySetRate(RateType.PER_CLIENT,10,2, RateIntervalUnit.SECONDS);
        int i = 10;
        while (i>0){
            rateLimiter.acquire(3);
            System.out.println("current num is: "+i);
            i--;
        }
    }


    public void lock(RedissonClient redissonClient){
        RLock lock = redissonClient.getLock("lock");
        lock.lock(100, TimeUnit.SECONDS);
        try {
            System.out.println("lock success");
        }finally {
            lock.unlock();
        }
    }
}
