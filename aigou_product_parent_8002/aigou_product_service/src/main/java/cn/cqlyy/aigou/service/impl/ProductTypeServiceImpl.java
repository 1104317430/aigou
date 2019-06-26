package cn.cqlyy.aigou.service.impl;

import cn.cqlyy.aigou.PageClient;
import cn.cqlyy.aigou.RedisClient;
import cn.cqlyy.aigou.domain.ProductType;
import cn.cqlyy.aigou.mapper.ProductTypeMapper;
import cn.cqlyy.aigou.service.IProductTypeService;
import cn.cqlyy.aigou.util.AgConstant;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author cqlyy
 * @since 2019-06-24
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {
    @Autowired
    private ProductTypeMapper typeMapper;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private PageClient pageClient;

    @Override
    public boolean updateById(ProductType entity) {
        boolean b = super.updateById(entity);
        //再修改redis
        List<ProductType> productTypeList = treeDataLoop();
        redisClient.set(AgConstant.REDIS_PRODUCTTYPE_KEY,JSONArray.toJSONString(productTypeList));
        if (b) {
            //1productType
            Map<String,Object> productTypeMap  = new HashMap<>();
            productTypeMap.put(AgConstant.PAGE_MODEL,productTypeList);
            //模板文件
            String templateFilePathAndName ="F:\\aigou_project\\aigou_parent\\aigou_product_parent_8002\\aigou_product_service\\src\\main\\resources\\template\\product.type.vm";
            productTypeMap.put(AgConstant.PAGE_TEMPLATE_FILE_PATH_NAME,templateFilePathAndName);

            //生成的文件：放到前端的项目中
            String targetFilePathAndName = "F:\\aigou_project\\aigou_parent\\aigou_product_parent_8002\\aigou_product_service\\src\\main\\resources\\statichtml\\product.type.html";
            productTypeMap.put(AgConstant.PAGE_TARGET_FILE_PATH_NAME,targetFilePathAndName);
            pageClient.createStaticPage(productTypeMap);

            //2:home
            Map<String,Object> homeMap  = new HashMap<>();

            Map<String,Object> modelMap  = new HashMap<>();
            modelMap.put("staticRoot","F:\\aigou_project\\aigou_parent\\aigou_product_parent_8002\\aigou_product_service\\src\\main\\resources\\");
            homeMap.put(AgConstant.PAGE_MODEL,modelMap);
            //模板文件目录
            templateFilePathAndName ="F:\\aigou_project\\aigou_parent\\aigou_product_parent_8002\\aigou_product_service\\src\\main\\resources\\template\\home.vm";
            homeMap.put(AgConstant.PAGE_TEMPLATE_FILE_PATH_NAME,templateFilePathAndName);

            //生成的文件目录
            targetFilePathAndName = "F:\\aigou_project\\aigou_parent\\aigou_product_parent_8002\\aigou_product_service\\src\\main\\resources\\statichtml\\home.html";
            homeMap.put(AgConstant.PAGE_TARGET_FILE_PATH_NAME,targetFilePathAndName);
            pageClient.createStaticPage(homeMap);
        }
        return b;
    }

    @Override
    public List<ProductType> selectTreeData() {
        String productTypeKey = redisClient.get(AgConstant.REDIS_PRODUCTTYPE_KEY);
        if (StringUtils.isEmpty(productTypeKey)) {
            List<ProductType> productTypes = treeDataLoop();
            System.out.println("db-----------------------------");
            redisClient.set(AgConstant.REDIS_PRODUCTTYPE_KEY, JSONArray.toJSONString(productTypes));
            return productTypes;
        } else {
            System.out.println("redis--------------------------");
            return JSONArray.parseArray(productTypeKey, ProductType.class);
        }
    }

    private List<ProductType> treeDataLoop() {
        Map<Long, ProductType> map = new HashMap<>();
        List<ProductType> productTypes = typeMapper.selectList(null);
        for (ProductType productType : productTypes) {
            map.put(productType.getId(), productType);
        }
        List<ProductType> result = new ArrayList<>();
        for (ProductType productType : productTypes) {
            Long pid = productType.getPid();
            if (pid == 0) {
                result.add(productType);
                continue;
            }
            ProductType parent = map.get(pid);
            parent.getChildren().add(productType);
        }
        return result;
    }
}
