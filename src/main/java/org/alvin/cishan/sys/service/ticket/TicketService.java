package org.alvin.cishan.sys.service.ticket;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 进销项发票录入--数据逻辑层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Service
public class TicketService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private TicketDao dao; //注入进销项发票录入数据访问层

	/**
	* @方法说明：  新增[进销项发票录入]记录
	*/
	@Transactional
	public int save(Ticket ticket) {
			return dao.save(ticket);
	}

	/**
	* @方法说明：  删除进销项发票录入记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新进销项发票录入记录
	*/
	@Transactional
	public int update(Ticket ticket) {
		return dao.update(ticket); 
	}

	/**
	* @方法说明： 按条件查询分页进销项发票录入列表
	*/
	public Page<Ticket> queryPage(TicketCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页进销项发票录入列表(使用范型)
	*/
	public List<Ticket> queryList(TicketCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个进销项发票录入记录
	*/
	public Ticket findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询进销项发票录入记录个数
	*/
	public long queryCount(TicketCond cond) {
		return dao.queryCount(cond);
	}
}