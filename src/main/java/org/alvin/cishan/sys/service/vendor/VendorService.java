package org.alvin.cishan.sys.service.vendor;
import java.util.List;


import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @类说明: 供应商--数据逻辑层
* @author: 唐植超
* @date : 2018-11-27 17:05:46
**/
@Service
public class VendorService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private VendorDao dao; //注入供应商数据访问层

	/**
	* @方法说明：  新增[供应商]记录
	*/
	@Transactional
	public int save(Vendor vendor) {
			return dao.save(vendor);
	}

	/**
	* @方法说明：  删除供应商记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新供应商记录
	*/
	@Transactional
	public int update(Vendor vendor) {
		return dao.update(vendor); 
	}

	/**
	* @方法说明： 按条件查询分页供应商列表
	*/
	public Page<Vendor> queryPage(VendorCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页供应商列表(使用范型)
	*/
	public List<Vendor> queryList(VendorCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个供应商记录
	*/
	public Vendor findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询供应商记录个数
	*/
	public long queryCount(VendorCond cond) {
		return dao.queryCount(cond);
	}
}