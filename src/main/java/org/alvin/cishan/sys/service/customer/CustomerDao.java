package org.alvin.cishan.sys.service.customer;


import com.google.common.base.Joiner;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* @类说明: 客户--数据访问层
* @author: 唐植超
* @date : 2018-11-27 17:15:51
**/
@Repository
public class CustomerDao extends BaseDao {

    private StringBuilder insert = new StringBuilder();

    /**
    * @方法说明：  构造方法,用于拼加SQL及初始化工作
    */
    public CustomerDao () {
        insert.append("INSERT INTO customer (cus_no,cus_name,address,cus_type,remark) ");
        insert.append(" VALUES (:cus_no,:cus_name,:address,:cus_type,:remark)");
    }

    /**
    * @方法说明：  新增客户记录
    */
    public int save(Customer vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO customer (id,cus_no,cus_name,address,cus_type,remark)");
        sql.append(" VALUES (?,?,?,?,?,?) ");
        Object[] params ={ vo.getId(),vo.getCus_no(),vo.getCus_name(),vo.getAddress(),vo.getCus_type(),vo.getRemark() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
    * @方法说明：新增客户记录并返回自增涨主键值
    */
    public long saveReturnPK(Customer vo) {
         return saveKey(vo, insert.toString(), "id");
    }
    
    /**
    * @方法说明：批量插入客户记录
    */
    public int[] insertBatch(List<Customer> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
    * @方法说明：物理删除客户记录(多条)
    */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM customer WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
    * @方法说明：更新客户记录
    */
    public int update(Customer vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE customer SET cus_no=?,cus_name=?,address=?,cus_type=?,remark=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getCus_no(),vo.getCus_name(),vo.getAddress(),vo.getCus_type(),vo.getRemark(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
      }

        /**
        * @方法说明：按条件查询分页客户列表
        */
    public Page<Customer> queryPage(CustomerCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM customer t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
        sb.append(cond.getCondition());
        sb.append(" order by id desc ");//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, Customer.class);
    }
    
    /**
    * @方法说明：按条件查询不分页客户列表
    */
    public List<Customer> queryList(CustomerCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM customer t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(cond.getCondition());
        sb.append(" order by id desc");//增加排序子句;
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Customer.class));
    }
    
    /**
    * @方法说明：按ID查找单个客户实体
    */
    public Customer findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM customer t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(" AND t.id=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class));
    }
    
    /**
    * @方法说明：按条件查询客户记录个数
    */
    public long queryCount(CustomerCond cond) {
    	String countSql = "SELECT COUNT(1) FROM customer t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
    * @方法说明：按条件查询客户记录个数
    */
    public int deleteLogic(Long ids[]) {
    	String sql = "UPDATE customer SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }

    /**
    * @方法说明：查询参数定制
    */
    public String getSelectedItems(CustomerCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
        return "t.id,t.cus_no,t.cus_name,t.address,t.cus_type,t.remark,td.name as type_name"; //默认所有字段
        }
        return Joiner.on(",").join(cond.getSelectedFields());
    }

    /**
    * @方法说明：表连接代码
    * @return
    */
    public String getJoinTables(){
        return " join  type_dict td on td.id=t.cus_type and td.type=4 ";
    }
}