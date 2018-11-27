package org.alvin.cishan.sys.service.person;

import com.google.common.base.Joiner;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* @类说明: 客户联系人--数据访问层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Repository
public class PersonDao extends BaseDao {

    private StringBuilder insert = new StringBuilder();

    /**
    * @方法说明：  构造方法,用于拼加SQL及初始化工作
    */
    public PersonDao () {
        insert.append("INSERT INTO cus_person (cus_id,address_id) ");
        insert.append(" VALUES (:cus_id,:address_id)");
    }

    /**
    * @方法说明：  新增客户联系人记录
    */
    public int save(Person vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO cus_person (id,cus_id,address_id)");
        sql.append(" VALUES (?,?,?) ");
        Object[] params ={ vo.getId(),vo.getCus_id(),vo.getAddress_id() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
    * @方法说明：新增客户联系人记录并返回自增涨主键值
    */
    public long saveReturnPK(Person vo) {
         return saveKey(vo, insert.toString(), "id");
    }
    
    /**
    * @方法说明：批量插入客户联系人记录
    */
    public int[] insertBatch(List<Person> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
    * @方法说明：物理删除客户联系人记录(多条)
    */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM cus_person WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
    * @方法说明：更新客户联系人记录
    */
    public int update(Person vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE cus_person SET cus_id=?,address_id=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getCus_id(),vo.getAddress_id(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
      }

        /**
        * @方法说明：按条件查询分页客户联系人列表
        */
    public Page<Person> queryPage(PersonCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM cus_person t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
        sb.append(cond.getCondition());
        //sb.append(cond.getOrderSql());//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, Person.class);
    }
    
    /**
    * @方法说明：按条件查询不分页客户联系人列表
    */
    public List<Person> queryList(PersonCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM cus_person t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(cond.getCondition());
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Person.class));
    }
    
    /**
    * @方法说明：按ID查找单个客户联系人实体
    */
    public Person findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM cus_person t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(" AND t.id=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
    }
    
    /**
    * @方法说明：按条件查询客户联系人记录个数
    */
    public long queryCount(PersonCond cond) {
    	String countSql = "SELECT COUNT(1) FROM cus_person t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
    * @方法说明：按条件查询客户联系人记录个数
    */
    public int deleteLogic(Long ids[]) {
    	String sql = "UPDATE cus_person SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }

    /**
    * @方法说明：查询参数定制
    */
    public String getSelectedItems(PersonCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
        return "t.id,t.cus_id,t.address_id"; //默认所有字段
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