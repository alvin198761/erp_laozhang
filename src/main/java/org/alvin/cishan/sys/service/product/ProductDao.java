package org.alvin.cishan.sys.service.product;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.dl.keep.common.util.SqlUtil;
import com.dl.webdata.common.jdbc.BaseDao;
import com.dl.keep.common.util.Page;
import com.google.common.base.Joiner;
/**
* @类说明: 产品--数据访问层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Repository
public class ProductDao extends BaseDao{

    private StringBuilder insert = new StringBuilder();

    /**
    * @方法说明：  构造方法,用于拼加SQL及初始化工作
    */
    public ProductDao () {
        insert.append("INSERT INTO product (prod_no,tax_type,prod_name,spec_no,note,unit,price,sell_price_yes,sell_price_no,"); 
		insert.append("mark_price_yes,mark_price_no,price_mode,vendor_id,pic1,pic2,pic3,remark) ");
        insert.append(" VALUES (:prod_no,:tax_type,:prod_name,:spec_no,:note,:unit,:price,:sell_price_yes,:sell_price_no,"); 
		insert.append(":mark_price_yes,:mark_price_no,:price_mode,:vendor_id,:pic1,:pic2,:pic3,:remark)");
    }

    /**
    * @方法说明：  新增产品记录
    */
    public int save(Product vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO product (id,prod_no,tax_type,prod_name,spec_no,note,unit,price,sell_price_yes,sell_price_no,"); 
		sql.append("mark_price_yes,mark_price_no,price_mode,vendor_id,pic1,pic2,pic3,remark)");
        sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
        Object[] params ={ vo.getId(),vo.getProd_no(),vo.getTax_type(),vo.getProd_name(),vo.getSpec_no(),vo.getNote(),vo.getUnit(),vo.getPrice(),vo.getSell_price_yes(),vo.getSell_price_no(),vo.getMark_price_yes(),vo.getMark_price_no(),vo.getPrice_mode(),vo.getVendor_id(),vo.getPic1(),vo.getPic2(),vo.getPic3(),vo.getRemark() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
    * @方法说明：新增产品记录并返回自增涨主键值
    */
    public long saveReturnPK(Product vo) {
         return saveKey(vo, insert.toString(), "id");
    }
    
    /**
    * @方法说明：批量插入产品记录
    */
    public int[] insertBatch(List<Product> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
    * @方法说明：物理删除产品记录(多条)
    */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM product WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
    * @方法说明：更新产品记录
    */
    public int update(Product vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE product SET prod_no=?,tax_type=?,prod_name=?,spec_no=?,note=?,unit=?,price=?,sell_price_yes=?,sell_price_no=?,"); 
		sql.append("mark_price_yes=?,mark_price_no=?,price_mode=?,vendor_id=?,pic1=?,pic2=?,pic3=?,remark=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getProd_no(),vo.getTax_type(),vo.getProd_name(),vo.getSpec_no(),vo.getNote(),vo.getUnit(),vo.getPrice(),vo.getSell_price_yes(),vo.getSell_price_no(),vo.getMark_price_yes(),vo.getMark_price_no(),vo.getPrice_mode(),vo.getVendor_id(),vo.getPic1(),vo.getPic2(),vo.getPic3(),vo.getRemark(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
      }

        /**
        * @方法说明：按条件查询分页产品列表
        */
    public Page<Product> queryPage(ProductCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM product t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
        sb.append(cond.getCondition());
        //sb.append(cond.getOrderSql());//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, Product.class);
    }
    
    /**
    * @方法说明：按条件查询不分页产品列表
    */
    public List<Product> queryList(ProductCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM product t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(cond.getCondition());
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Product.class));
    }
    
    /**
    * @方法说明：按ID查找单个产品实体
    */
    public Product findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM product t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(" AND t.id=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Product.class));
    }
    
    /**
    * @方法说明：按条件查询产品记录个数
    */
    public long queryCount(ProductCond cond) {
    	String countSql = "SELECT COUNT(1) FROM product t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
    * @方法说明：按条件查询产品记录个数
    */
    public int deleteLogic(Long ids[]) {
    	String sql = "UPDATE product SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }

    /**
    * @方法说明：查询参数定制
    */
    public String getSelectedItems(ProductCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
        return "t.id,t.prod_no,t.tax_type,t.prod_name,t.spec_no,t.note,t.unit,t.price,t.sell_price_yes,t.sell_price_no,t.mark_price_yes,t.mark_price_no,t.price_mode,t.vendor_id,t.pic1,t.pic2,t.pic3,t.remark"; //默认所有字段
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