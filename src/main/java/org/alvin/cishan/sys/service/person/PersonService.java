package org.alvin.cishan.sys.service.person;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 客户联系人--数据逻辑层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Service
public class PersonService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private PersonDao dao; //注入客户联系人数据访问层

	/**
	* @方法说明：  新增[客户联系人]记录
	*/
	@Transactional
	public int save(Person person) {
			return dao.save(person);
	}

	/**
	* @方法说明：  删除客户联系人记录(多条)
	*/
	public int delete(Long id) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(new Long[]{id});//物理删除
	}

	/**
	* @方法说明：  更新客户联系人记录
	*/
	@Transactional
	public int update(Person person) {
		return dao.update(person); 
	}

	/**
	* @方法说明： 按条件查询分页客户联系人列表
	*/
	public Page<Person> queryPage(PersonCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页客户联系人列表(使用范型)
	*/
	public List<Person> queryList(PersonCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个客户联系人记录
	*/
	public Person findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询客户联系人记录个数
	*/
	public long queryCount(PersonCond cond) {
		return dao.queryCount(cond);
	}
}