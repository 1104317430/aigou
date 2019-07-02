package cn.cqlyy.aigou.utils;

/**
 * @Author: cqlyy
 * @Date: 2019/06/26 17:56
 * @Version 1.0
 */
public class WebPashUtil {

    public static String getSuffixName(String originalFilename) {
        String[] split = originalFilename.split("\\.");
        String res = null;
        if (split.length>0){
            res = split[split.length - 1];
        }
        return res;
    }

    public static void getGroupNameAndFileName(String filePath){
        String res = filePath.substring(filePath.indexOf("/")+1);
        String groupName = res.substring(0, res.indexOf("/"));
        String fileName = res.substring(res.indexOf("/")+1);
        System.out.println(groupName+"============="+fileName);
        FastDfsApiOprUils.delete(groupName,fileName);
    }
}
