package org.alvin.cishan.sys.service.quote;

    
import java.util.Date;
    
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* @类说明: 报价--实体类
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Setter
@Getter
@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Quote", description = "报价实体")
public class Quote {

    //数据库中的字段
    
            @ApiModelProperty(value = "主键", dataType = "Long")
    private Long id ;//  主键
    
            @ApiModelProperty(value = "客户id", dataType = "Long")
    private Long cus_id ;//  客户id
    
                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
            @ApiModelProperty(value = "报价日期", dataType = "Date")
    private Date date ;//  报价日期
    
            @ApiModelProperty(value = "产品id", dataType = "Byte")
    private Byte prod_id ;//  产品id
    
            @ApiModelProperty(value = "总价", dataType = "Float")
    private Float total ;//  总价
    
            @ApiModelProperty(value = "单价", dataType = "Float")
    private Float priice ;//  单价
    
            @ApiModelProperty(value = "备注", dataType = "String")
    private String remark ;//  备注
    
            @ApiModelProperty(value = "单据状态", dataType = "Byte")
    private Byte status ;//  单据状态
    
            @ApiModelProperty(value = "数量", dataType = "Float")
    private Float num ;//  数量
    
    //此处可添加查询显示辅助字段

}