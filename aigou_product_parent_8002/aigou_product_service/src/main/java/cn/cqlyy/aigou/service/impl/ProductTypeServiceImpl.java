package cn.cqlyy.aigou.service.impl;

import cn.cqlyy.aigou.domain.ProductType;
import cn.cqlyy.aigou.mapper.ProductTypeMapper;
import cn.cqlyy.aigou.service.IProductTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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

    @Override
    public List<ProductType> selectTreeData() {
        return treeDataLoop();
    }

    private List<ProductType> treeDataLoop() {
        Map<Long, ProductType> map = new HashMap<>();
        List<ProductType> productTypes = typeMapper.selectList(null);
        for (ProductType productType : productTypes) {
            map.put(productType.getId(),productType);
        }
        List<ProductType> result = new ArrayList<>();
        for (ProductType productType : productTypes) {
            Long pid = productType.getPid();
            if (pid==0){
                result.add(productType);
                continue;
            }
            ProductType parent = map.get(pid);
            parent.getChildren().add(productType);
        }

        return result;
    }
}
