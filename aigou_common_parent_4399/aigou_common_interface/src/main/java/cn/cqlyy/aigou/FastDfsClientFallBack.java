package cn.cqlyy.aigou;

import cn.cqlyy.aigou.util.JsonResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: cqlyy
 * @Date: 2019/06/26 15:10
 * @Version 1.0
 */
public class FastDfsClientFallBack implements FastDfsClient {
    @Override
    public JsonResult upload(MultipartFile file) {
        return null;
    }

    @Override
    public JsonResult delete(String filePath) {
        return null;
    }
}
