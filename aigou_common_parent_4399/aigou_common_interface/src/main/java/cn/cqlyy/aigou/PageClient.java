package cn.cqlyy.aigou;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: cqlyy
 * @Date: 2019/06/25 17:28
 * @Version 1.0
 */
@FeignClient(value = "COMMON-PROVIDER",fallback = RedisClientFallBack.class)
public interface PageClient {
    @RequestMapping(value = "/page/create",method = RequestMethod.POST)
    void createStaticPage(@RequestBody Map<String,Object> map);
}
