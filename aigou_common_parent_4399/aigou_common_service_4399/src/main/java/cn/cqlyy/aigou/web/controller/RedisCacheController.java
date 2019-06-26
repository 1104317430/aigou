package cn.cqlyy.aigou.web.controller;

import cn.cqlyy.aigou.RedisClient;
import cn.cqlyy.aigou.utils.RedisUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: cqlyy
 * @Date: 2019/06/25 17:44
 * @Version 1.0
 */
@RestController
public class RedisCacheController implements RedisClient {

    @RequestMapping(value = "/redis/set",method = RequestMethod.POST)
    public void set(@RequestParam("key") String key, @RequestParam("value") String value){
        RedisUtil.setData(key,value);
    }

    @RequestMapping(value = "/redis/get/{key}",method = RequestMethod.GET)
    public String get(@PathVariable("key") String key){
        return RedisUtil.getByKey(key);
    }
}
