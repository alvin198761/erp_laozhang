package org.alvin.cishan.sys.service.vendor;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
* @类说明: 供应商--数据控制器层
* @author: 唐植超
* @date : 2018-11-27 17:05:46
**/
@RestController
@RequestMapping("api/vendor")
public class VendorController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private VendorService service; //注入供应商数据逻辑层

    /**
    * @方法说明：  新增[供应商]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Vendor vendor) {
		return service.save(vendor);
	}

    /**
    * @方法说明： 删除供应商记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("id") Long id) {
		return service.delete(id);
	}

    /**
    * @方法说明： 修改供应商记录
    */
	@PostMapping("update")
	public int update(@RequestBody Vendor vendor) {
		return service.update(vendor);
	}

    /**
    * @方法说明： 按条件查询分页供应商列表
    */
	@PostMapping("queryPage")
	public Page<Vendor> queryPage(@RequestBody VendorCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页供应商列表
    */
	@PostMapping("queryList")
	public List<Vendor> queryList(@RequestBody VendorCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个供应商记录
    */
	@RequestMapping("findById")
	public Vendor findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询供应商记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody VendorCond cond ){
		return service.queryCount(cond);
	}
}