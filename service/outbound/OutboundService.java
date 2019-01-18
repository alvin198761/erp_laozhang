package org.alvin.cishan.sys.service.outbound;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 出库记录--数据逻辑层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Service
public class OutboundService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private OutboundDao dao; //注入出库记录数据访问层

	/**
	* @方法说明：  新增[出库记录]记录
	*/
	@Transactional
	public int save(Outbound outbound) {
			return dao.save(outbound);
	}

	/**
	* @方法说明：  删除出库记录记录(多条)
	*/
	public int delete(Long id) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(new Long[]{id});//物理删除
	}

	/**
	* @方法说明：  更新出库记录记录
	*/
	@Transactional
	public int update(Outbound outbound) {
		return dao.update(outbound); 
	}

	/**
	* @方法说明： 按条件查询分页出库记录列表
	*/
	public Page<Outbound> queryPage(OutboundCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页出库记录列表(使用范型)
	*/
	public List<Outbound> queryList(OutboundCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个出库记录记录
	*/
	public Outbound findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询出库记录记录个数
	*/
	public long queryCount(OutboundCond cond) {
		return dao.queryCount(cond);
	}
}