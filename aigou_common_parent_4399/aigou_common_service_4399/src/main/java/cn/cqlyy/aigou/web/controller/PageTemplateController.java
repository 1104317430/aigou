package cn.cqlyy.aigou.web.controller;

import cn.cqlyy.aigou.PageClient;
import cn.cqlyy.aigou.util.AgConstant;
import cn.cqlyy.aigou.utils.VelocityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: cqlyy
 * @Date: 2019/06/25 19:40
 * @Version 1.0
 */
@RestController
public class PageTemplateController implements PageClient {
    @RequestMapping(value = "/page/create",method = RequestMethod.POST)
    @Override
    public void createStaticPage(@RequestBody Map<String, Object> map) {

        Object model = map.get(AgConstant.PAGE_MODEL);
        String template = (String) map.get(AgConstant.PAGE_TEMPLATE_FILE_PATH_NAME);
        String target = (String) map.get(AgConstant.PAGE_TARGET_FILE_PATH_NAME);
        VelocityUtils.staticByTemplate(model,template,target);
    }
}
