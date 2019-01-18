package org.alvin.cishan.sys.service.prodrecord;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
* @类说明: 产品信息记录--数据控制器层
* @author: 唐植超
* @date : 2019-01-18 13:46:52
**/
@RestController
@RequestMapping("/api/prodRecord")
public class ProdRecordController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ProdRecordService service; //注入产品信息记录数据逻辑层

    /**
    * @方法说明：  新增[产品信息记录]记录
    */
	@PostMapping("save")
	public int save(@RequestBody ProdRecord prodRecord) {
		return service.save(prodRecord);
	}

    /**
    * @方法说明： 删除产品信息记录记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

    /**
    * @方法说明： 修改产品信息记录记录
    */
	@PostMapping("update")
	public int update(@RequestBody ProdRecord prodRecord) {
		return service.update(prodRecord);
	}

    /**
    * @方法说明： 按条件查询分页产品信息记录列表
    */
	@PostMapping("queryPage")
	public Page<ProdRecord> queryPage(@RequestBody ProdRecordCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页产品信息记录列表
    */
	@PostMapping("queryList")
	public List<ProdRecord> queryList(@RequestBody ProdRecordCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个产品信息记录记录
    */
	@RequestMapping("findById")
	public ProdRecord findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询产品信息记录记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody ProdRecordCond cond ){
		return service.queryCount(cond);
	}
}