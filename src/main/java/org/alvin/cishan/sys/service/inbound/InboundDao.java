package org.alvin.cishan.sys.service.inbound;

import com.google.common.base.Joiner;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* @类说明: 入库记录--数据访问层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Repository
public class InboundDao extends BaseDao {

    private StringBuilder insert = new StringBuilder();

    /**
    * @方法说明：  构造方法,用于拼加SQL及初始化工作
    */
    public InboundDao () {
        insert.append("INSERT INTO inbound (prod_id,date,ticket_type,ticket_status,remark) ");
        insert.append(" VALUES (:prod_id,:date,:ticket_type,:ticket_status,:remark)");
    }

    /**
    * @方法说明：  新增入库记录记录
    */
    public int save(Inbound vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO inbound (id,prod_id,date,ticket_type,ticket_status,remark)");
        sql.append(" VALUES (?,?,?,?,?,?) ");
        Object[] params ={ vo.getId(),vo.getProd_id(),vo.getDate(),vo.getTicket_type(),vo.getTicket_status(),vo.getRemark() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
    * @方法说明：新增入库记录记录并返回自增涨主键值
    */
    public long saveReturnPK(Inbound vo) {
         return saveKey(vo, insert.toString(), "id");
    }
    
    /**
    * @方法说明：批量插入入库记录记录
    */
    public int[] insertBatch(List<Inbound> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
    * @方法说明：物理删除入库记录记录(多条)
    */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM inbound WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
    * @方法说明：更新入库记录记录
    */
    public int update(Inbound vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE inbound SET prod_id=?,date=?,ticket_type=?,ticket_status=?,remark=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getProd_id(),vo.getDate(),vo.getTicket_type(),vo.getTicket_status(),vo.getRemark(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
      }

        /**
        * @方法说明：按条件查询分页入库记录列表
        */
    public Page<Inbound> queryPage(InboundCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM inbound t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
        sb.append(cond.getCondition());
        //sb.append(cond.getOrderSql());//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, Inbound.class);
    }
    
    /**
    * @方法说明：按条件查询不分页入库记录列表
    */
    public List<Inbound> queryList(InboundCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM inbound t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(cond.getCondition());
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Inbound.class));
    }
    
    /**
    * @方法说明：按ID查找单个入库记录实体
    */
    public Inbound findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM inbound t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(" AND t.id=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Inbound.class));
    }
    
    /**
    * @方法说明：按条件查询入库记录记录个数
    */
    public long queryCount(InboundCond cond) {
    	String countSql = "SELECT COUNT(1) FROM inbound t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
    * @方法说明：按条件查询入库记录记录个数
    */
    public int deleteLogic(Long ids[]) {
    	String sql = "UPDATE inbound SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }

    /**
    * @方法说明：查询参数定制
    */
    public String getSelectedItems(InboundCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
        return "t.id,t.prod_id,t.date,t.ticket_type,t.ticket_status,t.remark"; //默认所有字段
        }
        return Joiner.on(",").join(cond.getSelectedFields());
    }

    /**
    * @方法说明：表连接代码
    * @return
    */
    public String getJoinTables(){
        return "";
    }
}