package org.alvin.cishan.sys.service.ticket;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明: 进销项发票录入--数据访问层
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Repository
public class TicketDao extends BaseDao {

	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明： 构造方法, 用于拼加SQL及初始化工作
	 */
	public TicketDao() {
		insert.append("INSERT INTO ticket (vendor_id,tax_type,target_name,target_id,remark,");
		insert.append("ticket_type,rate,type,cus_id) ");
		insert.append(" VALUES (:vendor_id,:tax_type,:target_name,:target_id,:remark,");
		insert.append(":ticket_type,:rate,:type,:cus_id)");
	}

	/**
	 * @方法说明： 新增进销项发票录入记录
	 */
	public int save(Ticket vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO ticket (id,vendor_id,tax_type,target_name,target_id,remark,");
		sql.append("ticket_type,rate,type,cus_id)");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?) ");
		Object[] params = {vo.getId(), vo.getVendor_id(), vo.getTax_type(), vo.getTarget_name(), vo.getTarget_id(), vo.getRemark(), vo.getTicket_type(), vo.getRate(), vo.getType(), vo.getCus_id()};
		//logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明：新增进销项发票录入记录并返回自增涨主键值
	 */
	public long saveReturnPK(Ticket vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明：批量插入进销项发票录入记录
	 */
	public int[] insertBatch(List<Ticket> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明：物理删除进销项发票录入记录(多条)
	 */
	public int delete(Long ids[]) {
		String sql = "DELETE FROM ticket WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明：更新进销项发票录入记录
	 */
	public int update(Ticket vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ticket SET vendor_id=?,tax_type=?,target_name=?,target_id=?,remark=?,");
		sql.append("ticket_type=?,rate=?,type=?,cus_id=? ");
		sql.append(" WHERE id=? ");
		Object[] params = {vo.getVendor_id(), vo.getTax_type(), vo.getTarget_name(), vo.getTarget_id(), vo.getRemark(), vo.getTicket_type(), vo.getRate(), vo.getType(), vo.getCus_id(), vo.getId()};
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明：按条件查询分页进销项发票录入列表
	 */
	public Page<Ticket> queryInPage(TicketCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(" t.id,t.vendor_id,t.tax_type,t.target_name,t.target_id,t.remark,t.ticket_type,t.rate,t.type,t.cus_id,v.vendor_name,v.vendor_no,a.target_phone as address_phone_no,a.target_addr as address,a.target_name as address_name ");
		sb.append(" FROM ticket t ");
		sb.append(" join vendor v on v.id = t.vendor_id join address a on a.id = t.target_id ");
		sb.append(" WHERE 1=1 ");
		sb.append(cond.getCondition());
		sb.append(" order by id desc ");//增加排序子句;
		logger.info(SqlUtil.showSql(sb.toString(), cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Ticket.class);
	}


	/**
	 * @方法说明：按条件查询分页进销项发票录入列表
	 */
	public Page<Ticket> queryOutPage(TicketCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(" t.id,t.vendor_id,t.tax_type,t.target_name,t.target_id,t.remark,t.ticket_type,t.rate,t.type,t.cus_id,c.cus_no,c.cus_name,a.target_phone as address_phone_no,a.target_addr as address,a.target_name as address_name ");
		sb.append(" FROM ticket t ");
		sb.append(" join customer c on c.id = t.cus_id join address a on a.id = t.target_id ");
		sb.append(" WHERE 1=1 ");
		sb.append(cond.getCondition());
		sb.append(" order by id desc ");//增加排序子句;
		logger.info(SqlUtil.showSql(sb.toString(), cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Ticket.class);
	}

	/**
	 * @方法说明：按条件查询不分页进销项发票录入列表
	 */
	public List<Ticket> queryList(TicketCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(cond));
		sb.append(" FROM ticket t ");
		sb.append(getJoinTables());
		sb.append(" WHERE 1=1 ");
		sb.append(cond.getCondition());
		//sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Ticket.class));
	}

	/**
	 * @方法说明：按ID查找单个进销项发票录入实体
	 */
	public Ticket findById(Long id) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(null));
		sb.append(" FROM ticket t ");
		sb.append(getJoinTables());
		sb.append(" WHERE 1=1 ");
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Ticket.class));
	}

	/**
	 * @方法说明：按条件查询进销项发票录入记录个数
	 */
	public long queryCount(TicketCond cond) {
		String countSql = "SELECT COUNT(1) FROM ticket t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明：按条件查询进销项发票录入记录个数
	 */
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE ticket SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明：查询参数定制
	 */
	public String getSelectedItems(TicketCond cond) {
		if (cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()) {
			return "t.id,t.vendor_id,t.tax_type,t.target_name,t.target_id,t.remark,t.ticket_type,t.rate,t.type,t.cus_id"; //默认所有字段
		}
		return Joiner.on(",").join(cond.getSelectedFields());
	}

	/**
	 * @return
	 * @方法说明：表连接代码
	 */
	public String getJoinTables() {
		return "";
	}

	/**
	 * 进项发票数据列表
	 *
	 * @param cond
	 * @return
	 */
	public List<Ticket> queryInList(TicketCond cond) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append(" t.id, ");
		sb.append(" t.vendor_id, ");
		sb.append(" t.tax_type, ");
		sb.append(" t.target_name, ");
		sb.append(" t.target_id, ");
		sb.append(" t.remark, ");
		sb.append(" t.ticket_type, ");
		sb.append(" t.rate, ");
		sb.append(" t.type, ");
		sb.append(" t.cus_id, ");
		sb.append(" v.vendor_name, ");
		sb.append(" v.vendor_no, ");
		sb.append(" a.target_phone AS address_phone_no, ");
		sb.append(" a.target_addr AS address, ");
		sb.append(" a.target_name AS address_name ");
		sb.append(" FROM ");
		sb.append(" ticket t ");
		sb.append(" JOIN prod_record pr ON pr.bus_type = 3 ");
		sb.append(" AND pr.bus_id = t.id ");
		sb.append(" AND vendor_id IS NOT NULL ");
		sb.append(" JOIN vendor v ON v.id = t.vendor_id ");
		sb.append(" JOIN address a ON a.id = t.target_id ");
		sb.append(" WHERE 1 = 1 ");
		if (!Strings.isNullOrEmpty(cond.getTax_type())) {
			sb.append(" AND t.tax_type LIKE '%" + cond.getTax_type() + "%' ");
		}
		sb.append(" AND t.type = 1 ");
		if (!Strings.isNullOrEmpty(cond.getPrice())) {
			sb.append(" AND pr.priice = " + cond.getPrice() + " ");
		}
		if (!Strings.isNullOrEmpty(cond.getNum())) {
			sb.append(" AND pr.num = " + cond.getNum() + " ");
		}
		sb.append(" AND pr.prod_id IN ( ");
		sb.append(" SELECT ");
		sb.append(" p.id ");
		sb.append(" FROM ");
		sb.append(" product p ");
		sb.append(" WHERE 1= 1 ");
		if (!Strings.isNullOrEmpty(cond.getProd_name())) {
			sb.append(" AND	p.prod_name LIKE '%" + cond.getProd_name() + "%' ");
		}
		if (!Strings.isNullOrEmpty(cond.getSpec_no())) {
			sb.append(" AND p.spec_no LIKE '%" + cond.getSpec_no() + "%' ");
		}
		sb.append(" ) order by id desc ");
//		System.out.println(sb.toString());
		return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(Ticket.class));
	}

	public List<Ticket> queryOutList(TicketCond cond) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append(" t.id, ");
		sb.append(" t.vendor_id, ");
		sb.append(" t.tax_type, ");
		sb.append(" t.target_name, ");
		sb.append(" t.target_id, ");
		sb.append(" t.remark, ");
		sb.append(" t.ticket_type, ");
		sb.append(" t.rate, ");
		sb.append(" t.type, ");
		sb.append(" t.cus_id, ");
		sb.append(" c.cus_name, ");
		sb.append(" c.cus_no, ");
		sb.append(" a.target_phone AS address_phone_no, ");
		sb.append(" a.target_addr AS address, ");
		sb.append(" a.target_name AS address_name ");
		sb.append(" FROM ");
		sb.append(" ticket t ");
		sb.append(" JOIN prod_record pr ON pr.bus_type = 3 ");
		sb.append(" AND pr.bus_id = t.id ");
		sb.append(" AND vendor_id IS NOT NULL ");
		sb.append(" JOIN customer c ON c.id = t.cus_id ");
		sb.append(" JOIN address a ON a.id = t.target_id ");
		sb.append(" WHERE 1= 1  ");
		if (!Strings.isNullOrEmpty(cond.getTax_type())) {
			sb.append(" AND t.tax_type LIKE '%" + cond.getTax_type() + "%' ");
		}
		sb.append(" AND t.type = 2 ");
		if (!Strings.isNullOrEmpty(cond.getPrice())) {
			sb.append(" AND pr.priice = " + cond.getPrice() + " ");
		}
		if(!Strings.isNullOrEmpty(cond.getNum())) {
			sb.append(" AND pr.num = " + cond.getNum() + " ");
		}
		sb.append(" AND pr.prod_id IN ( ");
		sb.append(" SELECT ");
		sb.append(" p.id ");
		sb.append(" FROM ");
		sb.append(" product p ");
		sb.append(" WHERE 1= 1 ");
		if (!Strings.isNullOrEmpty(cond.getProd_name())) {
			sb.append(" AND	p.prod_name LIKE '%" + cond.getProd_name() + "%' ");
		}
		if (!Strings.isNullOrEmpty(cond.getSpec_no())) {
			sb.append(" AND p.spec_no LIKE '%" + cond.getSpec_no() + "%' ");
		}
		sb.append(" ) order by id desc ");

		System.out.println(sb.toString());
		return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(Ticket.class));
	}

	//		for (String line : sql.split("\n")) {
//			System.out.println("sb.append(\" " + line.trim() + " \");");
//		}
}