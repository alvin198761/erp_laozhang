package org.alvin.cishan.sys.service.address;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 收寄信息--数据逻辑层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Service
public class AddressService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AddressDao dao; //注入收寄信息数据访问层

	/**
	* @方法说明：  新增[收寄信息]记录
	*/
	@Transactional
	public int save(Address address) {
			return dao.save(address);
	}

	/**
	* @方法说明：  删除收寄信息记录(多条)
	*/
	public int delete(Long id) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(new Long[]{id});//物理删除
	}

	/**
	* @方法说明：  更新收寄信息记录
	*/
	@Transactional
	public int update(Address address) {
		return dao.update(address); 
	}

	/**
	* @方法说明： 按条件查询分页收寄信息列表
	*/
	public Page<Address> queryPage(AddressCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页收寄信息列表(使用范型)
	*/
	public List<Address> queryList(AddressCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个收寄信息记录
	*/
	public Address findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询收寄信息记录个数
	*/
	public long queryCount(AddressCond cond) {
		return dao.queryCount(cond);
	}
}