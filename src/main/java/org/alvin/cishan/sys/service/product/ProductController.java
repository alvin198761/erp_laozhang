package org.alvin.cishan.sys.service.product;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @类说明: 产品--数据控制器层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@RestController
@RequestMapping("api/product")
public class ProductController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ProductService service; //注入产品数据逻辑层

    /**
    * @方法说明：  新增[产品]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Product product) {
		return service.save(product);
	}

    /**
    * @方法说明： 删除产品记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

    /**
    * @方法说明： 修改产品记录
    */
	@PostMapping("update")
	public int update(@RequestBody Product product) {
		return service.update(product);
	}

    /**
    * @方法说明： 按条件查询分页产品列表
    */
	@PostMapping("queryPage")
	public Page<Product> queryPage(@RequestBody ProductCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页产品列表
    */
	@PostMapping("queryList")
	public List<Product> queryList(@RequestBody ProductCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个产品记录
    */
	@RequestMapping("findById")
	public Product findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询产品记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody ProductCond cond ){
		return service.queryCount(cond);
	}
}