package org.alvin.cishan.sys.service.outbound;

import org.alvin.cishan.sys.service.prodrecord.ProdRecord;
import org.alvin.cishan.sys.service.prodrecord.ProdRecordCond;
import org.alvin.cishan.sys.service.prodrecord.ProdRecordDao;
import org.alvin.cishan.sys.service.quote.Quote;
import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	@Autowired
	private ProdRecordDao recordDao;

	/**
	 * @方法说明： 新增[出库记录]记录
	 */
	@Transactional
	public int save(Outbound outbound) {
		Long id = this.dao.saveReturnPK(outbound);
		List<ProdRecord> list = outbound.getProds().stream().filter((prod) -> {
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
	 * @方法说明： 删除出库记录记录(多条)
	 */
	public int delete(Long id) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(new Long[]{id});//物理删除
	}

	/**
	 * @方法说明： 更新出库记录记录
	 */
	@Transactional
	public int update(Outbound outbound) {
		recordDao.deleteByBusTypeAndId(outbound.getId(), 2);
		List<ProdRecord> list = outbound.getProds().stream().filter((prod) -> {
			if (prod.getBus_type() == null || prod.getProd_id() == null || prod.getNum() == null || prod.getPriice() == null) {
				return false;
			}
			return true;
		}).map(prod -> {
			prod.setBus_id(outbound.getId());
			prod.setTotal(prod.getNum() * prod.getPriice());
			return prod;
		}).collect(Collectors.toList());
		recordDao.insertBatch(list);
		return dao.update(outbound);
	}

	/**
	 * @方法说明： 按条件查询分页出库记录列表
	 */
	public Page<Outbound> queryPage(OutboundCond cond) {
//		return dao.queryPage(cond);
		Page<Outbound> page = dao.queryPage(cond);
		//
		List<Long> ids = page.getContent().stream().map(Outbound::getId).collect(Collectors.toList());
		ProdRecordCond prodRecordCond = new ProdRecordCond();
		prodRecordCond.setBus_type((byte) 2);
		prodRecordCond.setBus_ids(ids);
		//
		List<ProdRecord> records = recordDao.queryList(prodRecordCond);
		page.getContent().forEach(item -> {
			item.setProds(records.stream().filter(r -> {
				return r.getBus_id().longValue() == item.getId().longValue();
			}).collect(Collectors.toList()));
		});
		return page;
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