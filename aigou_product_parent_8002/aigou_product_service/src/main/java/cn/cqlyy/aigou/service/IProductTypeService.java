package cn.cqlyy.aigou.service;

import cn.cqlyy.aigou.domain.ProductType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author cqlyy
 * @since 2019-06-24
 */
public interface IProductTypeService extends IService<ProductType> {

    List<ProductType> selectTreeData();

}
