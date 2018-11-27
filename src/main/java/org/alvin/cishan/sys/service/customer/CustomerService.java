package org.alvin.cishan.sys.service.customer;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 客户--数据逻辑层
* @author: 唐植超
* @date : 2018-11-27 17:15:51
**/
@Service
public class CustomerService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CustomerDao dao; //注入客户数据访问层

	/**
	* @方法说明：  新增[客户]记录
	*/
	@Transactional
	public int save(Customer customer) {
			return dao.save(customer);
	}

	/**
	* @方法说明：  删除客户记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新客户记录
	*/
	@Transactional
	public int update(Customer customer) {
		return dao.update(customer); 
	}

	/**
	* @方法说明： 按条件查询分页客户列表
	*/
	public Page<Customer> queryPage(CustomerCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页客户列表(使用范型)
	*/
	public List<Customer> queryList(CustomerCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个客户记录
	*/
	public Customer findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询客户记录个数
	*/
	public long queryCount(CustomerCond cond) {
		return dao.queryCount(cond);
	}
}