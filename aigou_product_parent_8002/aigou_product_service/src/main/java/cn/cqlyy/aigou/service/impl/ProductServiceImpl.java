package cn.cqlyy.aigou.service.impl;

import cn.cqlyy.aigou.domain.Product;
import cn.cqlyy.aigou.domain.ProductExt;
import cn.cqlyy.aigou.mapper.ProductExtMapper;
import cn.cqlyy.aigou.mapper.ProductMapper;
import cn.cqlyy.aigou.service.IProductExtService;
import cn.cqlyy.aigou.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author cqlyy
 * @since 2019-06-24
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductExtMapper productExtMapper;

    @Override
    public boolean insert(Product entity) {
        //设置默认值：
        entity.setCreateTime(new Date().getTime());
        entity.setUpdateTime(new Date().getTime());
        //先保存主表
        boolean insert = super.insert(entity);


        //再保存关联表：ProductExt
        ProductExt productExt = entity.getProductExt();
        //设置关联主键：
        productExt.setProductId(entity.getId());
        productExtMapper.insert(productExt);

        return insert;
    }
}
