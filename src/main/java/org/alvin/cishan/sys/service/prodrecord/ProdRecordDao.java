package org.alvin.cishan.sys.service.prodrecord;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.service.ticket.TicketCond;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明: 产品信息记录--数据访问层
 * @author: 唐植超
 * @date : 2019-01-18 13:46:52
 **/
@Repository
public class ProdRecordDao extends BaseDao {

	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明： 构造方法, 用于拼加SQL及初始化工作
	 */
	public ProdRecordDao() {
		insert.append("INSERT INTO prod_record (prod_id,total,priice,num,bus_id,bus_type) ");
		insert.append(" VALUES (:prod_id,:total,:priice,:num,:bus_id,:bus_type)");
	}

	/**
	 * @方法说明： 新增产品信息记录记录
	 */
	public int save(ProdRecord vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO prod_record (id,prod_id,total,priice,num,bus_id,bus_type)");
		sql.append(" VALUES (?,?,?,?,?,?,?) ");
		Object[] params = {vo.getId(), vo.getProd_id(), vo.getTotal(), vo.getPriice(), vo.getNum(), vo.getBus_id(), vo.getBus_type()};
		//logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明：新增产品信息记录记录并返回自增涨主键值
	 */
	public long saveReturnPK(ProdRecord vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明：批量插入产品信息记录记录
	 */
	public int[] insertBatch(List<ProdRecord> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明：物理删除产品信息记录记录(多条)
	 */
	public int delete(Long ids[]) {
		String sql = "DELETE FROM prod_record WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明：更新产品信息记录记录
	 */
	public int update(ProdRecord vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE prod_record SET prod_id=?,total=?,priice=?,num=?,bus_id=?,bus_type=? ");
		sql.append(" WHERE id=? ");
		Object[] params = {vo.getProd_id(), vo.getTotal(), vo.getPriice(), vo.getNum(), vo.getBus_id(), vo.getBus_type(), vo.getId()};
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明：按条件查询分页产品信息记录列表
	 */
	public Page<ProdRecord> queryPage(ProdRecordCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(cond));
		sb.append(" FROM prod_record t ");
		sb.append(getJoinTables());
		sb.append(" WHERE 1=1 ");
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, ProdRecord.class);
	}

	/**
	 * @方法说明：按条件查询不分页产品信息记录列表
	 */
	public List<ProdRecord> queryList(ProdRecordCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(cond));
		sb.append(" FROM prod_record t ");
		sb.append(getJoinTables());
		sb.append(" WHERE 1=1 ");
		sb.append(cond.getCondition());
		//sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(ProdRecord.class));
	}

	/**
	 * @方法说明：按ID查找单个产品信息记录实体
	 */
	public ProdRecord findById(Long id) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(null));
		sb.append(" FROM prod_record t ");
		sb.append(getJoinTables());
		sb.append(" WHERE 1=1 ");
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(ProdRecord.class));
	}

	/**
	 * @方法说明：按条件查询产品信息记录记录个数
	 */
	public long queryCount(ProdRecordCond cond) {
		String countSql = "SELECT COUNT(1) FROM prod_record t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明：按条件查询产品信息记录记录个数
	 */
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE prod_record SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明：查询参数定制
	 */
	public String getSelectedItems(ProdRecordCond cond) {
		if (cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()) {
			return "t.id,t.prod_id,t.total,t.priice,t.num,t.bus_id,t.bus_type,p.prod_name,p.prod_no"; //默认所有字段
		}
		return Joiner.on(",").join(cond.getSelectedFields());
	}

	/**
	 * @return
	 * @方法说明：表连接代码
	 */
	public String getJoinTables() {
		return " join product p on p.id = t.prod_id ";
	}

	public int deleteByBusTypeAndId(Long bus_id, int bus_type) {
		String sql = "DELETE FROM prod_record WHERE bus_type=? and bus_id =?";
		return jdbcTemplate.update(sql, new Object[]{bus_type, bus_id});

	}

	public List<ProdRecord> queryTicketList(TicketCond cond, List<Long> ticketIds) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append(" t.id, ");
		sb.append(" t.prod_id, ");
		sb.append(" t.total, ");
		sb.append(" t.priice, ");
		sb.append(" t.num, ");
		sb.append(" t.bus_id, ");
		sb.append(" t.bus_type, ");
		sb.append(" p.prod_name, ");
		sb.append(" p.spec_no, ");
		sb.append(" p.prod_no ");
		sb.append(" FROM ");
		sb.append(" prod_record t ");
		sb.append(" JOIN product p ON t.prod_id = p.id ");
		sb.append(" WHERE 1 = 1");
		if (!Strings.isNullOrEmpty(cond.getProd_name())) {
			sb.append(" p.prod_name LIKE '%" + cond.getProd_name() + "%' ");
		}
		if (!Strings.isNullOrEmpty(cond.getSpec_no())) {
			sb.append(" AND p.spec_no LIKE '%" + cond.getSpec_no() + "%' ");
		}
		sb.append(" AND t.bus_type = 3 ");
		if (!ticketIds.isEmpty()) {
			sb.append(" AND t.bus_id IN (" + Joiner.on(",").join(ticketIds) + ") ");
		}
		if (!Strings.isNullOrEmpty(cond.getNum())) {
			sb.append(" AND t.num = " + cond.getNum() + " ");
		}
		if (!Strings.isNullOrEmpty(cond.getPrice())) {
			sb.append(" AND t.priice = " + cond.getPrice() + " ");
		}
		sb.append(" order by t.id desc ");
		System.out.println(sb.toString());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(ProdRecord.class));
	}
}