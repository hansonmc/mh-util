package com.mh.util.lock;

import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JedisLock {

    private Jedis jedis;
    private String key;
    private long waitTime = 60000 * 10L;
    private long expireTime = 60000L;
    private boolean locked;

    public JedisLock(Jedis jedis, String key){
        this(jedis, key, -1L, -1L);
    }

    /**
     *
     * @param jedis
     * @param key
     * @param waitTime 如果被其它线程持有锁了，则进行等待获取锁的时间
     * @param expireTime 锁的失效时间
     */
    public JedisLock(Jedis jedis, String key, long waitTime, long expireTime){
        this.jedis = jedis;
        this.key = key;
        if(waitTime > 0) this.waitTime = waitTime;
        if(expireTime > 0) this.expireTime = expireTime;
    }

    public synchronized boolean acquire() throws InterruptedException {
        String expireTimeValue = null;
        String oldLockValue = null;

        while(waitTime > 0){
            // 1. 给setnx设置值，成功则获取锁
            expireTimeValue = String.valueOf(System.currentTimeMillis() + expireTime);
            if(jedis.setnx(key, expireTimeValue) == 1L){
                locked = true;
                return locked;
            }

            // 2. 取得锁值，校验锁值是否超时
            String expectLockValue = jedis.get(key);
            if(isNumber(expectLockValue) && Long.valueOf(expectLockValue) < System.currentTimeMillis()){
                // getSet是原子操作，肯定保同时只有一个线程能够设入值
                oldLockValue = jedis.getSet(key, expireTimeValue);
                // 能执行到这里肯定只有一个线程的锁值与expectLockValue匹配
                if(oldLockValue != null && oldLockValue.equals(expectLockValue)){
                    jedis.set(key, expireTimeValue);
                    locked = true;
                    return locked;
                }
            }


            waitTime -= 100L;
            Thread.sleep(100L);
        }
        return locked;
    }

    public synchronized void release(){
        if(locked){
            if(jedis != null){
                jedis.del(key);
            }
        }
        locked = false;
    }

    private boolean isNumber(String value){
        return value != null && value.matches("[0-9]+");
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i = 0; i < 99; i++){
            final int j = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = new Jedis("192.168.131.117", 6379);
                    JedisLock mh = new JedisLock(jedis, "MH");
                    try {
                        if(mh.acquire()){
                            System.out.print(Thread.currentThread().getName() + " is processing. j=" + j + "  ");
                            Thread.sleep(1000L);
                            System.out.println(Thread.currentThread().getName() + " process end. j=" + j);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        mh.release();
                    }
                }
            });
        }
    }

}
