package cn.cqlyy.aigou.service.impl;

import cn.cqlyy.aigou.domain.Brand;
import cn.cqlyy.aigou.mapper.BrandMapper;
import cn.cqlyy.aigou.query.BrandQuery;
import cn.cqlyy.aigou.service.IBrandService;
import cn.cqlyy.aigou.util.PageList;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author cqlyy
 * @since 2019-06-23
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public PageList<Brand> queryPage(BrandQuery query) {
        PageList<Brand> pageList = new PageList<>();
        long total = brandMapper.queryPageCount(query);
        if (total>0){
            List<Brand> brands = brandMapper.queryPageList(query);
            pageList.setRows(brands);
            pageList.setTotal(total);
        }
        return pageList;
    }

    @Override
    public void deleteAllById(Integer[] ids) {
        brandMapper.deleteAllById(ids);
    }
}
