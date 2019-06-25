package cn.cqlyy.aigou.service;

import cn.cqlyy.aigou.domain.Brand;
import cn.cqlyy.aigou.query.BrandQuery;
import cn.cqlyy.aigou.util.PageList;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author cqlyy
 * @since 2019-06-23
 */
public interface IBrandService extends IService<Brand> {

    PageList<Brand> queryPage(BrandQuery query);

    void deleteAllById(Integer[] ids);

}
