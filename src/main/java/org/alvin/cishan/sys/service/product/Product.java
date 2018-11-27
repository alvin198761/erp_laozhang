package org.alvin.cishan.sys.service.product;

    
    
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* @类说明: 产品--实体类
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Setter
@Getter
@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Product", description = "产品实体")
public class Product {

    //数据库中的字段
    
            @ApiModelProperty(value = "主键", dataType = "Long")
    private Long id ;//  主键
    
            @ApiModelProperty(value = "货号", dataType = "String")
    private String prod_no ;//  货号
    
            @ApiModelProperty(value = "税收类型", dataType = "String")
    private String tax_type ;//  税收类型
    
            @ApiModelProperty(value = "产品名称", dataType = "String")
    private String prod_name ;//  产品名称
    
            @ApiModelProperty(value = "规格型号", dataType = "String")
    private String spec_no ;//  规格型号
    
            @ApiModelProperty(value = "产品说明", dataType = "String")
    private String note ;//  产品说明
    
            @ApiModelProperty(value = "单位", dataType = "String")
    private String unit ;//  单位
    
            @ApiModelProperty(value = "底价", dataType = "Float")
    private Float price ;//  底价
    
            @ApiModelProperty(value = "售卖价(含税)", dataType = "Float")
    private Float sell_price_yes ;//  售卖价(含税)
    
            @ApiModelProperty(value = "售卖价(不含税)", dataType = "Float")
    private Float sell_price_no ;//  售卖价(不含税)
    
            @ApiModelProperty(value = "市场价(不含税)", dataType = "Float")
    private Float mark_price_yes ;//  市场价(不含税)
    
            @ApiModelProperty(value = "市场价(含税)", dataType = "Float")
    private Float mark_price_no ;//  市场价(含税)
    
            @ApiModelProperty(value = "价格类型", dataType = "Byte")
    private Byte price_mode ;//  价格类型
    
            @ApiModelProperty(value = "货物来源", dataType = "Long")
    private Long vendor_id ;//  货物来源
    
            @ApiModelProperty(value = "产品图片1", dataType = "String")
    private String pic1 ;//  产品图片1
    
            @ApiModelProperty(value = "产品图片2", dataType = "String")
    private String pic2 ;//  产品图片2
    
            @ApiModelProperty(value = "产品图片3", dataType = "String")
    private String pic3 ;//  产品图片3
    
            @ApiModelProperty(value = "备注", dataType = "String")
    private String remark ;//  备注
    
    //此处可添加查询显示辅助字段

}