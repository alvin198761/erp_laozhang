package org.alvin.cishan.sys.service.quote;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
* @类说明: 报价--数据控制器层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@RestController
@RequestMapping("api/quote")
public class QuoteController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private QuoteService service; //注入报价数据逻辑层

    /**
    * @方法说明：  新增[报价]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Quote quote) {
		return service.save(quote);
	}

    /**
    * @方法说明： 删除报价记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

    /**
    * @方法说明： 修改报价记录
    */
	@PostMapping("update")
	public int update(@RequestBody Quote quote) {
		return service.update(quote);
	}

    /**
    * @方法说明： 按条件查询分页报价列表
    */
	@PostMapping("queryPage")
	public Page<Quote> queryPage(@RequestBody QuoteCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页报价列表
    */
	@PostMapping("queryList")
	public List<Quote> queryList(@RequestBody QuoteCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个报价记录
    */
	@RequestMapping("findById")
	public Quote findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询报价记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody QuoteCond cond ){
		return service.queryCount(cond);
	}
}