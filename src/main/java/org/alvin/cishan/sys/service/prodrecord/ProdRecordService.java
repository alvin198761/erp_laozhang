package org.alvin.cishan.sys.service.prodrecord;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 产品信息记录--数据逻辑层
* @author: 唐植超
* @date : 2019-01-18 13:46:52
**/
@Service
public class ProdRecordService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ProdRecordDao dao; //注入产品信息记录数据访问层

	/**
	* @方法说明：  新增[产品信息记录]记录
	*/
	@Transactional
	public int save(ProdRecord prodRecord) {
			return dao.save(prodRecord);
	}

	/**
	* @方法说明：  删除产品信息记录记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新产品信息记录记录
	*/
	@Transactional
	public int update(ProdRecord prodRecord) {
		return dao.update(prodRecord); 
	}

	/**
	* @方法说明： 按条件查询分页产品信息记录列表
	*/
	public Page<ProdRecord> queryPage(ProdRecordCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页产品信息记录列表(使用范型)
	*/
	public List<ProdRecord> queryList(ProdRecordCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个产品信息记录记录
	*/
	public ProdRecord findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询产品信息记录记录个数
	*/
	public long queryCount(ProdRecordCond cond) {
		return dao.queryCount(cond);
	}
}