package cn.cqlyy.aigou;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: cqlyy
 * @Date: 2019/06/25 17:28
 * @Version 1.0
 */
@FeignClient(value = "COMMON-PROVIDER",fallback = RedisClientFallBack.class)
public interface RedisClient {
    @RequestMapping(value = "/redis/set",method = RequestMethod.POST)
    void set(@RequestParam("key") String key, @RequestParam("value") String value);

    @RequestMapping(value = "/redis/get/{key}",method = RequestMethod.GET)
    String get(@PathVariable("key") String key);
}
