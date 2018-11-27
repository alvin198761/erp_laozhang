package org.alvin.cishan.sys.service.ticket;

    
    
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* @类说明: 进销项发票录入--实体类
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Setter
@Getter
@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Ticket", description = "进销项发票录入实体")
public class Ticket {

    //数据库中的字段
    
            @ApiModelProperty(value = "主键", dataType = "Long")
    private Long id ;//  主键
    
            @ApiModelProperty(value = "供应商id", dataType = "Long")
    private Long vendor_id ;//  供应商id
    
            @ApiModelProperty(value = "税收类型", dataType = "String")
    private String tax_type ;//  税收类型
    
            @ApiModelProperty(value = "产品id", dataType = "Long")
    private Long prod_id ;//  产品id
    
            @ApiModelProperty(value = "姓名", dataType = "String")
    private String target_name ;//  姓名
    
            @ApiModelProperty(value = "收寄方id", dataType = "Long")
    private Long target_id ;//  收寄方id
    
            @ApiModelProperty(value = "备注", dataType = "String")
    private String remark ;//  备注
    
            @ApiModelProperty(value = "数量", dataType = "Float")
    private Float num ;//  数量
    
            @ApiModelProperty(value = "单价", dataType = "Float")
    private Float price ;//  单价
    
            @ApiModelProperty(value = "总价", dataType = "Float")
    private Float total ;//  总价
    
            @ApiModelProperty(value = "发票类型", dataType = "Byte")
    private Byte ticket_type ;//  发票类型
    
            @ApiModelProperty(value = "税率", dataType = "Double")
    private Double rate ;//  税率
    
            @ApiModelProperty(value = "进出类型", dataType = "String")
    private String type ;//  进出类型
    
            @ApiModelProperty(value = "客户Id", dataType = "Long")
    private Long cus_id ;//  客户Id
    
    //此处可添加查询显示辅助字段

}