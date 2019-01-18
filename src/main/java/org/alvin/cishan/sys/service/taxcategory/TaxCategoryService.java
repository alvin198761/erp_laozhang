package org.alvin.cishan.sys.service.taxcategory;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 税收分类--数据逻辑层
* @author: 唐植超
* @date : 2019-01-18 13:46:52
**/
@Service
public class TaxCategoryService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private TaxCategoryDao dao; //注入税收分类数据访问层

	/**
	* @方法说明：  新增[税收分类]记录
	*/
	@Transactional
	public int save(TaxCategory taxCategory) {
			return dao.save(taxCategory);
	}

	/**
	* @方法说明：  删除税收分类记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新税收分类记录
	*/
	@Transactional
	public int update(TaxCategory taxCategory) {
		return dao.update(taxCategory); 
	}

	/**
	* @方法说明： 按条件查询分页税收分类列表
	*/
	public Page<TaxCategory> queryPage(TaxCategoryCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页税收分类列表(使用范型)
	*/
	public List<TaxCategory> queryList(TaxCategoryCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个税收分类记录
	*/
	public TaxCategory findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询税收分类记录个数
	*/
	public long queryCount(TaxCategoryCond cond) {
		return dao.queryCount(cond);
	}
}