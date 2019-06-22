package cn.cqlyy.aigou.web.controller;

import cn.cqlyy.aigou.domain.Employee;
import cn.cqlyy.aigou.util.JsonResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cqlyy
 * @Date: 2019/06/19 17:54
 * @Version 1.0
 */
@RestController
public class LoginController {
    /**
     * 注解：RequestBody  页面body传来的参数
     * @param employee User Info 用户信息
     * @return process result 处理结果
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonResult login(@RequestBody Employee employee){
        if ("cqlyy".equals(employee.getUsername()) && "123456".equals(employee.getPassword())) {
            return JsonResult.me().setObject(employee);
        }
        return JsonResult.me().setSuccess(false).setMsg("登录失败");
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public JsonResult loginGet(){
        return JsonResult.me();
    }


}
