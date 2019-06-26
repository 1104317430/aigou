package cn.cqlyy.aigou.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import sun.applet.Main;

/**
 * Redis工具类
 * @Author: cqlyy
 * @Date: 2019/06/25 17:57
 * @Version 1.0
 */
public class RedisUtil {
    static JedisPool jedisPool = null;

    static {
        //连接池的设置：
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(20);
        poolConfig.setMaxIdle(5);
        poolConfig.setMaxWaitMillis(3 * 1000);
        poolConfig.setTestOnBorrow(true);
        String host = "127.0.0.1";
        int port = 6379;
        int timeout = 3000;
        String password = "zhuhai321.";
        jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
    }

    public static void setData(String key,String value){
        //获取一个连接
        Jedis jedis = jedisPool.getResource();
        //调用api
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static String getByKey(String key){
        //获取一个连接
        Jedis jedis = jedisPool.getResource();
        //调用api
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
