package org.alvin.cishan.sys.service.ticket;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @类说明: 进销项发票录入--数据控制器层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@RestController
@RequestMapping("api/ticket")
public class TicketController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private TicketService service; //注入进销项发票录入数据逻辑层

    /**
    * @方法说明：  新增[进销项发票录入]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Ticket ticket) {
		return service.save(ticket);
	}

    /**
    * @方法说明： 删除进销项发票录入记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("id") Long id) {
		return service.delete(id);
	}

    /**
    * @方法说明： 修改进销项发票录入记录
    */
	@PostMapping("update")
	public int update(@RequestBody Ticket ticket) {
		return service.update(ticket);
	}

    /**
    * @方法说明： 按条件查询分页进销项发票录入列表
    */
	@PostMapping("queryPage")
	public Page<Ticket> queryPage(@RequestBody TicketCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页进销项发票录入列表
    */
	@PostMapping("queryList")
	public List<Ticket> queryList(@RequestBody TicketCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个进销项发票录入记录
    */
	@RequestMapping("findById")
	public Ticket findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询进销项发票录入记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody TicketCond cond ){
		return service.queryCount(cond);
	}
}