package org.alvin.cishan.sys.service.quote;

import org.alvin.cishan.sys.service.prodrecord.ProdRecord;
import org.alvin.cishan.sys.service.prodrecord.ProdRecordCond;
import org.alvin.cishan.sys.service.prodrecord.ProdRecordDao;
import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

	@Autowired
	private ProdRecordDao recordDao;

	/**
	 * @方法说明： 新增[报价]记录
	 */
	@Transactional
	public int save(Quote quote) {
		Long id = dao.saveReturnPK(quote);
		List<ProdRecord> list = quote.getProds().stream().filter((prod) -> {
			if (prod.getBus_type() == null || prod.getProd_id() == null || prod.getNum() == null || prod.getPriice() == null) {
				return false;
			}
			return true;
		}).map(prod -> {
			prod.setBus_id(id);
			prod.setTotal(prod.getNum() * prod.getPriice());
			return prod;
		}).collect(Collectors.toList());
		recordDao.insertBatch(list);
		return 1;
	}

	/**
	 * @方法说明： 删除报价记录(多条)
	 */
	public int delete(Long id) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(new Long[]{id});//物理删除
	}

	/**
	 * @方法说明： 更新报价记录
	 */
	@Transactional
	public int update(Quote quote) {
		recordDao.deleteByBusTypeAndId(quote.getId() ,1);
		List<ProdRecord> list = quote.getProds().stream().filter((prod) -> {
			if (prod.getBus_type() == null || prod.getProd_id() == null || prod.getNum() == null || prod.getPriice() == null) {
				return false;
			}
			return true;
		}).map(prod -> {
			prod.setBus_id(quote.getId());
			prod.setTotal(prod.getNum() * prod.getPriice());
			return prod;
		}).collect(Collectors.toList());
		recordDao.insertBatch(list);
		return dao.update(quote);
	}

	/**
	 * @方法说明： 按条件查询分页报价列表
	 */
	public Page<Quote> queryPage(QuoteCond cond) {
		Page<Quote> page = dao.queryPage(cond);
		//
		List<Long> ids = page.getContent().stream().map(Quote::getId).collect(Collectors.toList());
		ProdRecordCond prodRecordCond = new ProdRecordCond();
		prodRecordCond.setBus_type((byte)1);
		prodRecordCond.setBus_ids(ids);
		//
		List<ProdRecord> records = recordDao.queryList(prodRecordCond);
		page.getContent().forEach(item ->{
			item.setProds(records.stream().filter(r ->{
				return r.getBus_id().longValue() == item.getId().longValue();
			}).collect(Collectors.toList()));
		});
		return page;
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