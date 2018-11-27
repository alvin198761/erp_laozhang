package org.alvin.cishan.sys.service.bill;
import java.util.List;


import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @类说明: 开票信息--数据逻辑层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Service
public class BillService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private BillDao dao; //注入开票信息数据访问层

	/**
	* @方法说明：  新增[开票信息]记录
	*/
	@Transactional
	public int save(Bill bill) {
			return dao.save(bill);
	}

	/**
	* @方法说明：  删除开票信息记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新开票信息记录
	*/
	@Transactional
	public int update(Bill bill) {
		return dao.update(bill); 
	}

	/**
	* @方法说明： 按条件查询分页开票信息列表
	*/
	public Page<Bill> queryPage(BillCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页开票信息列表(使用范型)
	*/
	public List<Bill> queryList(BillCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个开票信息记录
	*/
	public Bill findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询开票信息记录个数
	*/
	public long queryCount(BillCond cond) {
		return dao.queryCount(cond);
	}
}