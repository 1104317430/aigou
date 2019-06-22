package cn.cqlyy.aigou.util;

/**
 * @Author: cqlyy
 * @Date: 2019/06/19 16:26
 * @Version 1.0
 */
public class JsonResult {
    private Boolean success=true;
    private String msg = "操作成功";
    private Object object;

    public static JsonResult me(){
        return new JsonResult();
    }

    public Object getObject() {
        return object;
    }

    public JsonResult setObject(Object object) {
        this.object = object;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public JsonResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
