package cn.cqlyy.aigou.web.controller;

import cn.cqlyy.aigou.FastDfsClient;
import cn.cqlyy.aigou.util.JsonResult;
import cn.cqlyy.aigou.utils.WebPashUtil;
import cn.cqlyy.aigou.utils.FastDfsApiOprUils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: cqlyy
 * @Date: 2019/06/26 17:42
 * @Version 1.0
 */
@RestController
public class FastDfsController implements FastDfsClient {
    /**
     * 上传
     * @param file
     * @return
     */

    @Override
    @RequestMapping(value ="/fastdfs/upload",method = RequestMethod.POST)
    public JsonResult upload(@RequestBody MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffixName = WebPashUtil.getSuffixName(originalFilename);
        if (suffixName!=null) {
            try {
                String uploadFileGroupAndName = FastDfsApiOprUils.upload(file.getBytes(), suffixName);
                return JsonResult.me().setSuccess(true).setMsg("上传成功").setObject(uploadFileGroupAndName);
            } catch (Exception e) {
                e.printStackTrace();
                return JsonResult.me().setSuccess(false).setMsg("上传失败"+e.getMessage());
            }
        }
        return JsonResult.me().setSuccess(false).setMsg("路径不能为空");
    }

    /**
     * 删除
     * @param filePath
     * @return
     */
    @Override
    @RequestMapping(value ="/fastdfs/delete",method = RequestMethod.DELETE)
    public JsonResult delete(@RequestParam("filePath") String filePath) {
        try {
            WebPashUtil.getGroupNameAndFileName(filePath);
            return JsonResult.me().setSuccess(true).setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setSuccess(false).setMsg("删除失败");
        }
    }
}
