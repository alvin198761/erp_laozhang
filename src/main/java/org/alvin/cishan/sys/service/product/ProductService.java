package org.alvin.cishan.sys.service.product;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 产品--数据逻辑层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Service
public class ProductService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ProductDao dao; //注入产品数据访问层

	/**
	* @方法说明：  新增[产品]记录
	*/
	@Transactional
	public int save(Product product) {
			return dao.save(product);
	}

	/**
	* @方法说明：  删除产品记录(多条)
	*/
	public int delete(Long id) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(new Long[]{id});//物理删除
	}

	/**
	* @方法说明：  更新产品记录
	*/
	@Transactional
	public int update(Product product) {
		return dao.update(product); 
	}

	/**
	* @方法说明： 按条件查询分页产品列表
	*/
	public Page<Product> queryPage(ProductCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页产品列表(使用范型)
	*/
	public List<Product> queryList(ProductCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个产品记录
	*/
	public Product findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询产品记录个数
	*/
	public long queryCount(ProductCond cond) {
		return dao.queryCount(cond);
	}
}