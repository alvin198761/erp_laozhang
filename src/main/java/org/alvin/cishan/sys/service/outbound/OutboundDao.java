package org.alvin.cishan.sys.service.outbound;

import com.google.common.base.Joiner;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明: 出库记录--数据访问层
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Repository
public class OutboundDao extends BaseDao {

	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明： 构造方法, 用于拼加SQL及初始化工作
	 */
	public OutboundDao() {
		insert.append("INSERT INTO outbound (cus_id,status,remark,date) ");
		insert.append(" VALUES (:cus_id,:status,:remark,:date)");
	}

	/**
	 * @方法说明： 新增出库记录记录
	 */
	public int save(Outbound vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO outbound (id,cus_id,status,remark,date)");
		sql.append(" VALUES (?,?,?,?,?) ");
		Object[] params = {vo.getId(), vo.getCus_id(),  vo.getStatus(), vo.getRemark()};
		//logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明：新增出库记录记录并返回自增涨主键值
	 */
	public long saveReturnPK(Outbound vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明：批量插入出库记录记录
	 */
	public int[] insertBatch(List<Outbound> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明：物理删除出库记录记录(多条)
	 */
	public int delete(Long ids[]) {
		String sql = "DELETE FROM outbound WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明：更新出库记录记录
	 */
	public int update(Outbound vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE outbound SET cus_id=?,status=?,remark=?,date=? ");
		sql.append(" WHERE id=? ");
		Object[] params = {vo.getCus_id(),  vo.getStatus(), vo.getRemark(),vo.getDate(), vo.getId()};
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明：按条件查询分页出库记录列表
	 */
	public Page<Outbound> queryPage(OutboundCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(cond));
		sb.append(" FROM outbound t ");
		sb.append(getJoinTables());
		sb.append(" WHERE 1=1 ");
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Outbound.class);
	}

	/**
	 * @方法说明：按条件查询不分页出库记录列表
	 */
	public List<Outbound> queryList(OutboundCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(cond));
		sb.append(" FROM outbound t ");
		sb.append(getJoinTables());
		sb.append(" WHERE 1=1 ");
		sb.append(cond.getCondition());
		//sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Outbound.class));
	}

	/**
	 * @方法说明：按ID查找单个出库记录实体
	 */
	public Outbound findById(Long id) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(null));
		sb.append(" FROM outbound t ");
		sb.append(getJoinTables());
		sb.append(" WHERE 1=1 ");
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Outbound.class));
	}

	/**
	 * @方法说明：按条件查询出库记录记录个数
	 */
	public long queryCount(OutboundCond cond) {
		String countSql = "SELECT COUNT(1) FROM outbound t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明：按条件查询出库记录记录个数
	 */
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE outbound SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明：查询参数定制
	 */
	public String getSelectedItems(OutboundCond cond) {
		if (cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()) {
			return "t.id,t.cus_id,t.status,t.remark,t.date,c.cus_name ,c.cus_no "; //默认所有字段
		}
		return Joiner.on(",").join(cond.getSelectedFields());
	}

	/**
	 * @方法说明：表连接代码
	 * @return
	 */
	public String getJoinTables(){
		return " join customer c on c.id = t.cus_id ";
	}
}