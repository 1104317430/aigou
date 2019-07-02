package cn.cqlyy.aigou.web.controller;

import cn.cqlyy.aigou.domain.ProductExt;
import cn.cqlyy.aigou.service.IProductExtService;
import cn.cqlyy.aigou.service.ISpecificationService;
import cn.cqlyy.aigou.domain.Specification;
import cn.cqlyy.aigou.query.SpecificationQuery;
import cn.cqlyy.aigou.util.JsonResult;
import cn.cqlyy.aigou.util.PageList;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
    @Autowired
    public ISpecificationService specificationService;
    @Autowired
    public IProductExtService productExtService;

    /**
    * 保存和修改公用的
    * @param specification  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JsonResult save(@RequestBody Specification specification){
        try {
            if(specification.getId()!=null){
                specificationService.updateById(specification);
            }else{
                specificationService.insert(specification);
            }
            return JsonResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Long id){
        try {
            specificationService.deleteById(id);
            return JsonResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return JsonResult.me().setMsg("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Specification get(@RequestParam(value="id",required=true) Long id)
    {
        return specificationService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Specification> list(){

        return specificationService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Specification> json(@RequestBody SpecificationQuery query)
    {
        Page<Specification> page = new Page<Specification>(query.getPage(),query.getRows());
            page = specificationService.selectPage(page);
            return new PageList<Specification>(page.getTotal(),page.getRecords());
    }


    @RequestMapping(value="/viewProperties/{productTypeId}/{productId}",method=RequestMethod.GET)
    public List<Specification> viewProperties(@PathVariable("productTypeId") Long productTypeId,@PathVariable("productId") Long productId){
        Wrapper<ProductExt> extWrapper = new EntityWrapper<>();
        extWrapper.eq("productId",productId);
        ProductExt productExt = productExtService.selectOne(extWrapper);
        if (null!=productExt && !StringUtils.isEmpty(productExt.getViewProperties())){
            String viewProperties = productExt.getViewProperties();
            return JSONArray.parseArray(viewProperties,Specification.class);
        }
        Wrapper<Specification> wrapper= new EntityWrapper<>();
        wrapper.eq("productTypeId",productTypeId);
        wrapper.eq("type",1);
        return specificationService.selectList(wrapper);
    }
    @RequestMapping(value="/skuProperties/{productTypeId}",method=RequestMethod.GET)
    public List<Specification> skuProperties(@PathVariable("productTypeId") Long productTypeId){

        Wrapper<Specification>  wapper = new EntityWrapper<>();
        // SELECT * FROM `t_specification` s where s.productTypeId=1 and s.type=1 ;
        wapper.eq("productTypeId",productTypeId);
        wapper.eq("type",2);
        return   specificationService.selectList(wapper);
    }
}
