package cn.cqlyy.aigou.web.controller;

import cn.cqlyy.aigou.query.BrandQuery;
import cn.cqlyy.aigou.service.IBrandService;
import cn.cqlyy.aigou.domain.Brand;
import cn.cqlyy.aigou.util.JsonResult;
import cn.cqlyy.aigou.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    public IBrandService brandService;

    /**
    * 保存和修改公用的
    * @param brand  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JsonResult save(@RequestBody Brand brand){
        try {
            if(brand.getId()!=null){
                /**
                 * 设置创建时间和修改时间
                 */
                brand.setCreateTime(new Date().getTime());
                brand.setUpdateTime(new Date().getTime());
                brandService.updateById(brand);
            }else{
                brand.setUpdateTime(new Date().getTime());
                brandService.insert(brand);
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
            brandService.deleteById(id);
            return JsonResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return JsonResult.me().setMsg("删除对象失败！"+e.getMessage());
        }
    }

    @RequestMapping(value="/deleteAll",method=RequestMethod.POST)
    public JsonResult deleteAll(Integer[] ids){
        try {
            brandService.deleteAllById(ids);
            return JsonResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("删除对象失败！"+e.getMessage());
        }
    }
    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Brand get(@RequestParam(value="id",required=true) Long id)
    {
        return brandService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Brand> list(){

        return brandService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Brand> json(@RequestBody BrandQuery query)
    {
       /* Page<Brand> page = new Page<Brand>(query.getPage(),query.getRows());
            page = brandService.selectPage(page);
            return new PageList<Brand>(page.getTotal(),page.getRecords());*/
       return brandService.queryPage(query);
    }
}
