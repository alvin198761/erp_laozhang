package org.alvin.cishan.sys.service.quote;

import com.google.common.base.Joiner;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* @类说明: 报价--数据访问层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Repository
public class QuoteDao extends BaseDao{

    private StringBuilder insert = new StringBuilder();

    /**
    * @方法说明：  构造方法,用于拼加SQL及初始化工作
    */
    public QuoteDao () {
        insert.append("INSERT INTO quote (cus_id,date,prod_id,total,price,remark,status,num) ");
        insert.append(" VALUES (:cus_id,:date,:prod_id,:total,:price,:remark,:status,:num)");
    }

    /**
    * @方法说明：  新增报价记录
    */
    public int save(Quote vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO quote (id,cus_id,date,prod_id,total,price,remark,status,num)");
        sql.append(" VALUES (?,?,?,?,?,?,?,?,?) ");
        Object[] params ={ vo.getId(),vo.getCus_id(),vo.getDate(),vo.getProd_id(),vo.getTotal(),vo.getPrice(),vo.getRemark(),vo.getStatus(),vo.getNum() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
    * @方法说明：新增报价记录并返回自增涨主键值
    */
    public long saveReturnPK(Quote vo) {
         return saveKey(vo, insert.toString(), "id");
    }
    
    /**
    * @方法说明：批量插入报价记录
    */
    public int[] insertBatch(List<Quote> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
    * @方法说明：物理删除报价记录(多条)
    */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM quote WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
    * @方法说明：更新报价记录
    */
    public int update(Quote vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE quote SET cus_id=?,date=?,prod_id=?,total=?,price=?,remark=?,status=?,num=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getCus_id(),vo.getDate(),vo.getProd_id(),vo.getTotal(),vo.getPrice(),vo.getRemark(),vo.getStatus(),vo.getNum(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
      }

        /**
        * @方法说明：按条件查询分页报价列表
        */
    public Page<Quote> queryPage(QuoteCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM quote t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
        sb.append(cond.getCondition());
        //sb.append(cond.getOrderSql());//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, Quote.class);
    }
    
    /**
    * @方法说明：按条件查询不分页报价列表
    */
    public List<Quote> queryList(QuoteCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM quote t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(cond.getCondition());
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Quote.class));
    }
    
    /**
    * @方法说明：按ID查找单个报价实体
    */
    public Quote findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM quote t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(" AND t.id=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Quote.class));
    }
    
    /**
    * @方法说明：按条件查询报价记录个数
    */
    public long queryCount(QuoteCond cond) {
    	String countSql = "SELECT COUNT(1) FROM quote t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
    * @方法说明：按条件查询报价记录个数
    */
    public int deleteLogic(Long ids[]) {
    	String sql = "UPDATE quote SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }

    /**
    * @方法说明：查询参数定制
    */
    public String getSelectedItems(QuoteCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
        return "t.id,t.cus_id,t.date,t.prod_id,t.total,t.price,t.remark,t.status,t.num,c.cus_name ,c.cus_no ,p.prod_name,p.prod_no "; //默认所有字段
        }
        return Joiner.on(",").join(cond.getSelectedFields());
    }

    /**
    * @方法说明：表连接代码
    * @return
    */
    public String getJoinTables(){
        return " join customer c on c.id = t.cus_id join product p on p.id = t.prod_id";
    }

}