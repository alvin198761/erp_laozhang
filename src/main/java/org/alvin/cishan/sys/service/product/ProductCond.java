package org.alvin.cishan.sys.service.product;

    
    
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.dl.keep.common.util.base.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
* @类说明: 产品--查询条件实体类
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Product", description = "产品查询条件实体")
public class ProductCond extends BaseCondition {


    /**
    * @方法说明: 拼加自定义条件
    **/
    @Override
    public void addCondition() {
                    add(id , " AND t.id = ? " );
                            add(prod_no , " AND t.prod_no LIKE ? " ,3);
                            add(tax_type , " AND t.tax_type LIKE ? " ,3);
                            add(prod_name , " AND t.prod_name LIKE ? " ,3);
                            add(spec_no , " AND t.spec_no LIKE ? " ,3);
                            add(note , " AND t.note LIKE ? " ,3);
                            add(unit , " AND t.unit LIKE ? " ,3);
                            add(price , " AND t.price = ? " );
                            add(sell_price_yes , " AND t.sell_price_yes = ? " );
                            add(sell_price_no , " AND t.sell_price_no = ? " );
                            add(mark_price_yes , " AND t.mark_price_yes = ? " );
                            add(mark_price_no , " AND t.mark_price_no = ? " );
                            add(price_mode , " AND t.price_mode = ? " );
                            add(vendor_id , " AND t.vendor_id = ? " );
                            add(pic1 , " AND t.pic1 LIKE ? " ,3);
                            add(pic2 , " AND t.pic2 LIKE ? " ,3);
                            add(pic3 , " AND t.pic3 LIKE ? " ,3);
                            add(remark , " AND t.remark LIKE ? " ,3);
                // add(ids, "AND t.id IN ");");
    }

    //feilds
        @ApiModelProperty(value = "主键", dataType = "Long")
    private  Long  id ;//  主键
        @ApiModelProperty(value = "货号", dataType = "String")
    private  String  prod_no ;//  货号
        @ApiModelProperty(value = "税收类型", dataType = "String")
    private  String  tax_type ;//  税收类型
        @ApiModelProperty(value = "产品名称", dataType = "String")
    private  String  prod_name ;//  产品名称
        @ApiModelProperty(value = "规格型号", dataType = "String")
    private  String  spec_no ;//  规格型号
        @ApiModelProperty(value = "产品说明", dataType = "String")
    private  String  note ;//  产品说明
        @ApiModelProperty(value = "单位", dataType = "String")
    private  String  unit ;//  单位
        @ApiModelProperty(value = "底价", dataType = "Float")
    private  Float  price ;//  底价
        @ApiModelProperty(value = "售卖价(含税)", dataType = "Float")
    private  Float  sell_price_yes ;//  售卖价(含税)
        @ApiModelProperty(value = "售卖价(不含税)", dataType = "Float")
    private  Float  sell_price_no ;//  售卖价(不含税)
        @ApiModelProperty(value = "市场价(不含税)", dataType = "Float")
    private  Float  mark_price_yes ;//  市场价(不含税)
        @ApiModelProperty(value = "市场价(含税)", dataType = "Float")
    private  Float  mark_price_no ;//  市场价(含税)
        @ApiModelProperty(value = "价格类型", dataType = "Byte")
    private  Byte  price_mode ;//  价格类型
        @ApiModelProperty(value = "货物来源", dataType = "Long")
    private  Long  vendor_id ;//  货物来源
        @ApiModelProperty(value = "产品图片1", dataType = "String")
    private  String  pic1 ;//  产品图片1
        @ApiModelProperty(value = "产品图片2", dataType = "String")
    private  String  pic2 ;//  产品图片2
        @ApiModelProperty(value = "产品图片3", dataType = "String")
    private  String  pic3 ;//  产品图片3
        @ApiModelProperty(value = "备注", dataType = "String")
    private  String  remark ;//  备注
    
    //private List<Long> ids;// 主键列表
    private List<String> selectedFields; //暂时不放入父类
}