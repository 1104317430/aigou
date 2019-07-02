package cn.cqlyy.aigou.web.controller;

import cn.cqlyy.aigou.domain.Specification;
import cn.cqlyy.aigou.service.IProductExtService;
import cn.cqlyy.aigou.domain.ProductExt;
import cn.cqlyy.aigou.query.ProductExtQuery;
import cn.cqlyy.aigou.util.JsonResult;
import cn.cqlyy.aigou.util.PageList;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productExt")
public class ProductExtController {
    @Autowired
    public IProductExtService productExtService;

    /**
    * 保存和修改公用的
    * @param productExt  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JsonResult save(@RequestBody ProductExt productExt){
        try {
            if(productExt.getId()!=null){
                productExtService.updateById(productExt);
            }else{
                productExtService.insert(productExt);
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
            productExtService.deleteById(id);
            return JsonResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return JsonResult.me().setMsg("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ProductExt get(@RequestParam(value="id",required=true) Long id)
    {
        return productExtService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<ProductExt> list(){

        return productExtService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<ProductExt> json(@RequestBody ProductExtQuery query)
    {
        Page<ProductExt> page = new Page<ProductExt>(query.getPage(),query.getRows());
            page = productExtService.selectPage(page);
            return new PageList<ProductExt>(page.getTotal(),page.getRecords());
    }

    @RequestMapping(value = "/updateViewProperties",method = RequestMethod.POST)
    public JsonResult updateViewProperties(@RequestBody Map<String,Object> map)
    {
        try {
            List<Specification> viewPropertiesList = (List<Specification>) map.get("viewProperties");
            String jsonString = JSONArray.toJSONString(viewPropertiesList);
            Object productId = map.get("productId");
            ProductExt entity = new ProductExt();
            entity.setViewProperties(jsonString);

            Wrapper<ProductExt> wrapper = new EntityWrapper<>();
            wrapper.eq("productId",productId);
            productExtService.update(entity,wrapper);
            return JsonResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setSuccess(false).setMsg(e.getMessage());
        }

    }
}
