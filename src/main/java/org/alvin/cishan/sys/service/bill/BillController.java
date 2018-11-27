package org.alvin.cishan.sys.service.bill;
import java.util.List;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @类说明: 开票信息--数据控制器层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@RestController
@RequestMapping("api/bill")
public class BillController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private BillService service; //注入开票信息数据逻辑层

    /**
    * @方法说明：  新增[开票信息]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Bill bill) {
		return service.save(bill);
	}

    /**
    * @方法说明： 删除开票信息记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

    /**
    * @方法说明： 修改开票信息记录
    */
	@PostMapping("update")
	public int update(@RequestBody Bill bill) {
		return service.update(bill);
	}

    /**
    * @方法说明： 按条件查询分页开票信息列表
    */
	@PostMapping("queryPage")
	public Page<Bill> queryPage(@RequestBody BillCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页开票信息列表
    */
	@PostMapping("queryList")
	public List<Bill> queryList(@RequestBody BillCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个开票信息记录
    */
	@RequestMapping("findById")
	public Bill findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询开票信息记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody BillCond cond ){
		return service.queryCount(cond);
	}
}