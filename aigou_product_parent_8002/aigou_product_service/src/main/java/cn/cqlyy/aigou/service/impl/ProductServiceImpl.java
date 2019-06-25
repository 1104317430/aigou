package cn.cqlyy.aigou.service.impl;

import cn.cqlyy.aigou.domain.Product;
import cn.cqlyy.aigou.mapper.ProductMapper;
import cn.cqlyy.aigou.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
