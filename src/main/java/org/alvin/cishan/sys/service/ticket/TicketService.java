package org.alvin.cishan.sys.service.ticket;

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
	@Autowired
	private ProdRecordDao recordDao;


	/**
	 * @方法说明： 新增[进销项发票录入]记录
	 */
	@Transactional
	public int save(Ticket ticket) {
//		return dao.save(ticket);
		Long id = this.dao.saveReturnPK(ticket);
		List<ProdRecord> list = ticket.getProds().stream().filter((prod) -> {
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
	 * @方法说明： 删除进销项发票录入记录(多条)
	 */
	public int delete(Long id) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(new Long[]{id});//物理删除
	}

	/**
	 * @方法说明： 更新进销项发票录入记录
	 */
	@Transactional
	public int update(Ticket ticket) {
		recordDao.deleteByBusTypeAndId(ticket.getId(), 3);
		List<ProdRecord> list = ticket.getProds().stream().filter((prod) -> {
			if (prod.getBus_type() == null || prod.getProd_id() == null || prod.getNum() == null || prod.getPriice() == null) {
				return false;
			}
			return true;
		}).map(prod -> {
			prod.setBus_id(ticket.getId());
			prod.setTotal(prod.getNum() * prod.getPriice());
			return prod;
		}).collect(Collectors.toList());
		recordDao.insertBatch(list);
		return dao.update(ticket);
	}

	/**
	 * @方法说明： 按条件查询分页进销项发票录入列表
	 */
	public Page<Ticket> queryPage(TicketCond cond) {
		Page<Ticket> page = null;
		if (cond.getType() == 1) {
			page = this.dao.queryInPage(cond);
		} else {
			page = this.dao.queryOutPage(cond);
		}
		List<Long> ids = page.getContent().stream().map(Ticket::getId).collect(Collectors.toList());
		ProdRecordCond prodRecordCond = new ProdRecordCond();
		prodRecordCond.setBus_type((byte) 3);
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

	/**
	 * 统计的查法
	 *
	 * @param cond
	 * @return
	 */
	public List<Ticket> queryInList(TicketCond cond) {
		List<Ticket> list = this.dao.queryInList(cond);
		List<Long> ids = list.stream().map(Ticket::getId).distinct().collect(Collectors.toList());
		List<ProdRecord> prodRecords = this.recordDao.queryTicketList(cond, ids);
		list.stream().forEach(item -> {
			item.setProds(prodRecords.stream().filter(r -> {
				return r.getBus_id().longValue() == item.getId().longValue();
			}).collect(Collectors.toList()));
		});
		return list;
	}

	public List<Ticket> queryOutList(TicketCond cond) {
		List<Ticket> list = this.dao.queryOutList(cond);
		List<Long> ids = list.stream().map(Ticket::getId).distinct().collect(Collectors.toList());
		List<ProdRecord> prodRecords = this.recordDao.queryTicketList(cond, ids);
		list.stream().forEach(item -> {
			item.setProds(prodRecords.stream().filter(r -> {
				return r.getBus_id().longValue() == item.getId().longValue();
			}).collect(Collectors.toList()));
		});
		return list;
	}
}