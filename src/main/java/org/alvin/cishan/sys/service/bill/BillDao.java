package org.alvin.cishan.sys.service.bill;

import com.google.common.base.Joiner;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* @类说明: 开票信息--数据访问层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Repository
public class BillDao extends BaseDao {

    private StringBuilder insert = new StringBuilder();

    /**
    * @方法说明：  构造方法,用于拼加SQL及初始化工作
    */
    public BillDao () {
        insert.append("INSERT INTO bill (vendor_id,bank,account,taxpayer_no,remark) ");
        insert.append(" VALUES (:vendor_id,:bank,:account,:taxpayer_no,:remark)");
    }

    /**
    * @方法说明：  新增开票信息记录
    */
    public int save(Bill vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO bill (id,vendor_id,bank,account,taxpayer_no,remark)");
        sql.append(" VALUES (?,?,?,?,?,?) ");
        Object[] params ={ vo.getId(),vo.getVendor_id(),vo.getBank(),vo.getAccount(),vo.getTaxpayer_no(),vo.getRemark() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
    * @方法说明：新增开票信息记录并返回自增涨主键值
    */
    public long saveReturnPK(Bill vo) {
         return saveKey(vo, insert.toString(), "id");
    }
    
    /**
    * @方法说明：批量插入开票信息记录
    */
    public int[] insertBatch(List<Bill> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
    * @方法说明：物理删除开票信息记录(多条)
    */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM bill WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
    * @方法说明：更新开票信息记录
    */
    public int update(Bill vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE bill SET vendor_id=?,bank=?,account=?,taxpayer_no=?,remark=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getVendor_id(),vo.getBank(),vo.getAccount(),vo.getTaxpayer_no(),vo.getRemark(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
      }

        /**
        * @方法说明：按条件查询分页开票信息列表
        */
    public Page<Bill> queryPage(BillCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM bill t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
        sb.append(cond.getCondition());
        //sb.append(cond.getOrderSql());//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, Bill.class);
    }
    
    /**
    * @方法说明：按条件查询不分页开票信息列表
    */
    public List<Bill> queryList(BillCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM bill t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(cond.getCondition());
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Bill.class));
    }
    
    /**
    * @方法说明：按ID查找单个开票信息实体
    */
    public Bill findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM bill t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(" AND t.id=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Bill.class));
    }
    
    /**
    * @方法说明：按条件查询开票信息记录个数
    */
    public long queryCount(BillCond cond) {
    	String countSql = "SELECT COUNT(1) FROM bill t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
    * @方法说明：按条件查询开票信息记录个数
    */
    public int deleteLogic(Long ids[]) {
    	String sql = "UPDATE bill SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }

    /**
    * @方法说明：查询参数定制
    */
    public String getSelectedItems(BillCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
        return "t.id,t.vendor_id,t.bank,t.account,t.taxpayer_no,t.remark"; //默认所有字段
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