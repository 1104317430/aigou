package cn.cqlyy.aigou;

import cn.cqlyy.aigou.RedisClient;
import org.springframework.stereotype.Component;

/**
 * @Author: cqlyy
 * @Date: 2019/06/25 17:29
 * @Version 1.0
 */
@Component
public class RedisClientFallBack implements RedisClient {
    @Override
    public void set(String key, String value) {

    }

    @Override
    public String get(String key) {
        return null;
    }
}
