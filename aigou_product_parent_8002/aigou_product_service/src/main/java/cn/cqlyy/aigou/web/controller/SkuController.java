package cn.cqlyy.aigou.web.controller;

import cn.cqlyy.aigou.domain.Specification;
import cn.cqlyy.aigou.service.ISkuService;
import cn.cqlyy.aigou.domain.Sku;
import cn.cqlyy.aigou.query.SkuQuery;
import cn.cqlyy.aigou.util.JsonResult;
import cn.cqlyy.aigou.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sku")
public class SkuController {
    @Autowired
    public ISkuService skuService;

    /**
     * 保存和修改公用的
     *
     * @param sku 传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult save(@RequestBody Sku sku) {
        try {
            if (sku.getId() != null) {
                skuService.updateById(sku);
            } else {
                skuService.insert(sku);
            }
            return JsonResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("保存对象失败！" + e.getMessage());
        }
    }

    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Long id) {
        try {
            skuService.deleteById(id);
            return JsonResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Sku get(@RequestParam(value = "id", required = true) Long id) {
        return skuService.selectById(id);
    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Sku> list() {

        return skuService.selectList(null);
    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<Sku> json(@RequestBody SkuQuery query) {
        Page<Sku> page = new Page<Sku>(query.getPage(), query.getRows());
        page = skuService.selectPage(page);
        return new PageList<Sku>(page.getTotal(), page.getRecords());
    }

    @RequestMapping(value = "/skuProperties", method = RequestMethod.POST)
    public JsonResult skuProperties(@RequestBody Map<String, Object> map) {
        try {
            Long productId = Long.valueOf(map.get("productId") + "");
            List<Map<String, Object>> skuProperties = (List<Map<String, Object>>) map.get("skuProperties");
            List<Specification> specificationsList = new ArrayList<>();
            for (Map<String, Object> skuProperty : skuProperties) {
                Specification specification = new Specification();
                BeanUtils.populate(specification,skuProperty);
                specificationsList.add(specification);
            }
            List<Map<String, Object>> mapList= (List<Map<String, Object>>) map.get("skuDatas");
            skuService.saveSku(productId,specificationsList,mapList);
            return JsonResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setSuccess(false).setMsg("操作失败"+e.getMessage());
        }
    }
}
