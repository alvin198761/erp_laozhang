package org.alvin.cishan.sys.service.inbound;
import java.util.List;
import com.dl.keep.common.util.Page;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @类说明: 入库记录--数据逻辑层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Service
public class InboundService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private InboundDao dao; //注入入库记录数据访问层

	/**
	* @方法说明：  新增[入库记录]记录
	*/
	@Transactional
	public int save(Inbound inbound) {
			return dao.save(inbound);
	}

	/**
	* @方法说明：  删除入库记录记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新入库记录记录
	*/
	@Transactional
	public int update(Inbound inbound) {
		return dao.update(inbound); 
	}

	/**
	* @方法说明： 按条件查询分页入库记录列表
	*/
	public Page<Inbound> queryPage(InboundCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页入库记录列表(使用范型)
	*/
	public List<Inbound> queryList(InboundCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个入库记录记录
	*/
	public Inbound findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询入库记录记录个数
	*/
	public long queryCount(InboundCond cond) {
		return dao.queryCount(cond);
	}
}