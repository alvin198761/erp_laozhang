package org.alvin.cishan.sys.service.person;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
* @类说明: 客户联系人--数据控制器层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@RestController
@RequestMapping("api/person")
public class PersonController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private PersonService service; //注入客户联系人数据逻辑层

    /**
    * @方法说明：  新增[客户联系人]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Person person) {
		return service.save(person);
	}

    /**
    * @方法说明： 删除客户联系人记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("id") Long id) {
		return service.delete(id);
	}

    /**
    * @方法说明： 修改客户联系人记录
    */
	@PostMapping("update")
	public int update(@RequestBody Person person) {
		return service.update(person);
	}

    /**
    * @方法说明： 按条件查询分页客户联系人列表
    */
	@PostMapping("queryPage")
	public Page<Person> queryPage(@RequestBody PersonCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页客户联系人列表
    */
	@PostMapping("queryList")
	public List<Person> queryList(@RequestBody PersonCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个客户联系人记录
    */
	@RequestMapping("findById")
	public Person findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询客户联系人记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody PersonCond cond ){
		return service.queryCount(cond);
	}
}