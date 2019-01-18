package org.alvin.cishan.sys.service.customer;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
* @类说明: 客户--数据控制器层
* @author: 唐植超
* @date : 2018-11-27 17:15:51
**/
@RestController
@RequestMapping("api/customer")
public class CustomerController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CustomerService service; //注入客户数据逻辑层

    /**
    * @方法说明：  新增[客户]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Customer customer) {
		return service.save(customer);
	}

    /**
    * @方法说明： 删除客户记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("id") Long id) {
		return service.delete(id);
	}

    /**
    * @方法说明： 修改客户记录
    */
	@PostMapping("update")
	public int update(@RequestBody Customer customer) {
		return service.update(customer);
	}

    /**
    * @方法说明： 按条件查询分页客户列表
    */
	@PostMapping("queryPage")
	public Page<Customer> queryPage(@RequestBody CustomerCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页客户列表
    */
	@PostMapping("queryList")
	public List<Customer> queryList(@RequestBody CustomerCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个客户记录
    */
	@RequestMapping("findById")
	public Customer findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询客户记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody CustomerCond cond ){
		return service.queryCount(cond);
	}
}