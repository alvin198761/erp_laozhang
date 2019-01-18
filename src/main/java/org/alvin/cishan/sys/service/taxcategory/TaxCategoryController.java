package org.alvin.cishan.sys.service.taxcategory;
import java.util.List;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
/**
* @类说明: 税收分类--数据控制器层
* @author: 唐植超
* @date : 2019-01-18 13:46:52
**/
@RestController
@RequestMapping("/api/taxCategory")
public class TaxCategoryController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private TaxCategoryService service; //注入税收分类数据逻辑层

    /**
    * @方法说明：  新增[税收分类]记录
    */
	@PostMapping("save")
	public int save(@RequestBody TaxCategory taxCategory) {
		return service.save(taxCategory);
	}

    /**
    * @方法说明： 删除税收分类记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

    /**
    * @方法说明： 修改税收分类记录
    */
	@PostMapping("update")
	public int update(@RequestBody TaxCategory taxCategory) {
		return service.update(taxCategory);
	}

    /**
    * @方法说明： 按条件查询分页税收分类列表
    */
	@PostMapping("queryPage")
	public Page<TaxCategory> queryPage(@RequestBody TaxCategoryCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页税收分类列表
    */
	@PostMapping("queryList")
	public List<TaxCategory> queryList(@RequestBody TaxCategoryCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个税收分类记录
    */
	@RequestMapping("findById")
	public TaxCategory findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询税收分类记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody TaxCategoryCond cond ){
		return service.queryCount(cond);
	}
}