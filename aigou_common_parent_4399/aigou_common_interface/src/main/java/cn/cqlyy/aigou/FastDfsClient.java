package cn.cqlyy.aigou;

import cn.cqlyy.aigou.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(value = "COMMON-PROVIDER",fallback = FastDfsClientFallBack.class)
public interface FastDfsClient {

    @RequestMapping(value ="/fastdfs/upload",method = RequestMethod.POST)
    JsonResult upload(@RequestBody MultipartFile file);

    @RequestMapping(value ="/fastdfs/delete",method = RequestMethod.DELETE)
    JsonResult delete(@PathVariable("filePath") String filePath);
}
