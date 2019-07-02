package cn.cqlyy.aigou.web.controller;

import cn.cqlyy.aigou.domain.Specification;
import cn.cqlyy.aigou.service.IProductExtService;
import cn.cqlyy.aigou.service.IProductService;
import cn.cqlyy.aigou.domain.Product;
import cn.cqlyy.aigou.query.ProductQuery;
import cn.cqlyy.aigou.util.JsonResult;
import cn.cqlyy.aigou.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    public IProductService productService;


    /**
    * 保存和修改公用的
    * @param product  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JsonResult save(@RequestBody Product product){
        try {
            if(product.getId()!=null){
                productService.updateById(product);
                product.setUpdateTime(new Date().getTime());
            }else{
                productService.insert(product);
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
            productService.deleteById(id);
            return JsonResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return JsonResult.me().setMsg("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product get(@RequestParam(value="id",required=true) Long id)
    {
        return productService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Product> list(){

        return productService.selectList(null);
    }
    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Product> json(@RequestBody ProductQuery query)
    {
        Page<Product> page = new Page<Product>(query.getPage(),query.getRows());
            page = productService.selectPage(page);
            return new PageList<Product>(page.getTotal(),page.getRecords());
    }

}
