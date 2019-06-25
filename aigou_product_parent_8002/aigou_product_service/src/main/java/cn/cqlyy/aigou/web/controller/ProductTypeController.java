package cn.cqlyy.aigou.web.controller;

import cn.cqlyy.aigou.service.IProductTypeService;
import cn.cqlyy.aigou.domain.ProductType;
import cn.cqlyy.aigou.query.ProductTypeQuery;
import cn.cqlyy.aigou.util.JsonResult;
import cn.cqlyy.aigou.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productType")
public class ProductTypeController {
    @Autowired
    public IProductTypeService productTypeService;

    /**
    * 保存和修改公用的
    * @param productType  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JsonResult save(@RequestBody ProductType productType){
        try {
            if(productType.getId()!=null){
                productTypeService.updateById(productType);
            }else{
                productTypeService.insert(productType);
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
            productTypeService.deleteById(id);
            return JsonResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return JsonResult.me().setMsg("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ProductType get(@RequestParam(value="id",required=true) Long id)
    {
        return productTypeService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<ProductType> list(){

        return productTypeService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<ProductType> json(@RequestBody ProductTypeQuery query)
    {
        Page<ProductType> page = new Page<ProductType>(query.getPage(),query.getRows());
            page = productTypeService.selectPage(page);
            return new PageList<ProductType>(page.getTotal(),page.getRecords());
    }
    @RequestMapping(value = "/treeData",method = RequestMethod.GET)
    public List<ProductType> treeData(){
        return productTypeService.selectTreeData();
    }
}
