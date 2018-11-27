package org.alvin.cishan.sys.service.quote;
import java.util.List;
import com.dl.keep.common.util.Page;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @类说明: 报价--数据逻辑层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Service
public class QuoteService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private QuoteDao dao; //注入报价数据访问层

	/**
	* @方法说明：  新增[报价]记录
	*/
	@Transactional
	public int save(Quote quote) {
			return dao.save(quote);
	}

	/**
	* @方法说明：  删除报价记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新报价记录
	*/
	@Transactional
	public int update(Quote quote) {
		return dao.update(quote); 
	}

	/**
	* @方法说明： 按条件查询分页报价列表
	*/
	public Page<Quote> queryPage(QuoteCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页报价列表(使用范型)
	*/
	public List<Quote> queryList(QuoteCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个报价记录
	*/
	public Quote findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询报价记录个数
	*/
	public long queryCount(QuoteCond cond) {
		return dao.queryCount(cond);
	}
}