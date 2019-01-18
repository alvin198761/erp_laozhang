package org.alvin.cishan.sys.service.taxcategory;

import com.google.common.base.Joiner;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* @类说明: 税收分类--数据访问层
* @author: 唐植超
* @date : 2019-01-18 13:46:52
**/
@Repository
public class TaxCategoryDao extends BaseDao {

    private StringBuilder insert = new StringBuilder();

    /**
    * @方法说明：  构造方法,用于拼加SQL及初始化工作
    */
    public TaxCategoryDao () {
        insert.append("INSERT INTO tax_category (name,tax_code) ");
        insert.append(" VALUES (:name,:tax_code)");
    }

    /**
    * @方法说明：  新增税收分类记录
    */
    public int save(TaxCategory vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO tax_category (id,name,tax_code)");
        sql.append(" VALUES (?,?,?) ");
        Object[] params ={ vo.getId(),vo.getName(),vo.getTax_code() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
    * @方法说明：新增税收分类记录并返回自增涨主键值
    */
    public long saveReturnPK(TaxCategory vo) {
         return saveKey(vo, insert.toString(), "id");
    }
    
    /**
    * @方法说明：批量插入税收分类记录
    */
    public int[] insertBatch(List<TaxCategory> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
    * @方法说明：物理删除税收分类记录(多条)
    */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM tax_category WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
    * @方法说明：更新税收分类记录
    */
    public int update(TaxCategory vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE tax_category SET name=?,tax_code=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getName(),vo.getTax_code(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
      }

        /**
        * @方法说明：按条件查询分页税收分类列表
        */
    public Page<TaxCategory> queryPage(TaxCategoryCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM tax_category t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
        sb.append(cond.getCondition());
        //sb.append(cond.getOrderSql());//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, TaxCategory.class);
    }
    
    /**
    * @方法说明：按条件查询不分页税收分类列表
    */
    public List<TaxCategory> queryList(TaxCategoryCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM tax_category t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(cond.getCondition());
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(TaxCategory.class));
    }
    
    /**
    * @方法说明：按ID查找单个税收分类实体
    */
    public TaxCategory findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM tax_category t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(" AND t.id=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(TaxCategory.class));
    }
    
    /**
    * @方法说明：按条件查询税收分类记录个数
    */
    public long queryCount(TaxCategoryCond cond) {
    	String countSql = "SELECT COUNT(1) FROM tax_category t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
    * @方法说明：按条件查询税收分类记录个数
    */
    public int deleteLogic(Long ids[]) {
    	String sql = "UPDATE tax_category SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }

    /**
    * @方法说明：查询参数定制
    */
    public String getSelectedItems(TaxCategoryCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
        return "t.id,t.name,t.tax_code"; //默认所有字段
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