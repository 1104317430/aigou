package cn.cqlyy.aigou.mapper;

import cn.cqlyy.aigou.domain.Brand;
import cn.cqlyy.aigou.query.BrandQuery;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author cqlyy
 * @since 2019-06-23
 */
public interface BrandMapper extends BaseMapper<Brand> {

    long queryPageCount(BrandQuery brandQuery);

    List<Brand> queryPageList(BrandQuery brandQuery);

    void deleteAllById(Integer[] ids);
}
