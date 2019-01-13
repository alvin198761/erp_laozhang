package org.alvin.cishan.sys.service.vendor;
import java.util.List;

import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


import com.google.common.base.Joiner;
/**
* @类说明: 供应商--数据访问层
* @author: 唐植超
* @date : 2018-11-27 17:05:46
**/
@Repository
public class VendorDao extends BaseDao {

    private StringBuilder insert = new StringBuilder();

    /**
    * @方法说明：  构造方法,用于拼加SQL及初始化工作
    */
    public VendorDao () {
        insert.append("INSERT INTO vendor (vendor_no,vendor_name,address,level,remark,concat_id) ");
        insert.append(" VALUES (:vendor_no,:vendor_name,:address,:level,:remark,:concat_id)");
    }

    /**
    * @方法说明：  新增供应商记录
    */
    public int save(Vendor vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO vendor (id,vendor_no,vendor_name,address,level,remark,concat_id)");
        sql.append(" VALUES (?,?,?,?,?,?,?) ");
        Object[] params ={ vo.getId(),vo.getVendor_no(),vo.getVendor_name(),vo.getAddress(),vo.getLevel(),vo.getRemark(),vo.getConcat_id() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
    * @方法说明：新增供应商记录并返回自增涨主键值
    */
    public long saveReturnPK(Vendor vo) {
         return saveKey(vo, insert.toString(), "id");
    }
    
    /**
    * @方法说明：批量插入供应商记录
    */
    public int[] insertBatch(List<Vendor> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
    * @方法说明：物理删除供应商记录(多条)
    */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM vendor WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
    * @方法说明：更新供应商记录
    */
    public int update(Vendor vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE vendor SET vendor_no=?,vendor_name=?,address=?,level=?,remark=?,concat_id=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getVendor_no(),vo.getVendor_name(),vo.getAddress(),vo.getLevel(),vo.getRemark(),vo.getConcat_id(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
      }

        /**
        * @方法说明：按条件查询分页供应商列表
        */
    public Page<Vendor> queryPage(VendorCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM vendor t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
        sb.append(cond.getCondition());
        //sb.append(cond.getOrderSql());//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, Vendor.class);
    }
    
    /**
    * @方法说明：按条件查询不分页供应商列表
    */
    public List<Vendor> queryList(VendorCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM vendor t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(cond.getCondition());
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Vendor.class));
    }
    
    /**
    * @方法说明：按ID查找单个供应商实体
    */
    public Vendor findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM vendor t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(" AND t.id=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Vendor.class));
    }
    
    /**
    * @方法说明：按条件查询供应商记录个数
    */
    public long queryCount(VendorCond cond) {
    	String countSql = "SELECT COUNT(1) FROM vendor t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
    * @方法说明：按条件查询供应商记录个数
    */
    public int deleteLogic(Long ids[]) {
    	String sql = "UPDATE vendor SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }

    /**
    * @方法说明：查询参数定制
    */
    public String getSelectedItems(VendorCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
        return "t.id,t.vendor_no,t.vendor_name,t.address,t.level,t.remark,t.concat_id,td.name as level_name "; //默认所有字段
        }
        return Joiner.on(",").join(cond.getSelectedFields());
    }

    /**
    * @方法说明：表连接代码
    * @return
    */
    public String getJoinTables(){
        return " join  type_dict td on td.id=t.level and td.level=5 ";
    }
}