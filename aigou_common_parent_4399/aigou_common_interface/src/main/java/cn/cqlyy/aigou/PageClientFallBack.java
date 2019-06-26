package cn.cqlyy.aigou;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: cqlyy
 * @Date: 2019/06/25 17:29
 * @Version 1.0
 */
@Component
public class PageClientFallBack implements PageClient {

    @Override
    public void createStaticPage(Map<String, Object> map) {

    }
}
