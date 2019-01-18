package org.alvin.cishan.sys.service.prodrecord;

    
    
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* @类说明: 产品信息记录--实体类
* @author: 唐植超
* @date : 2019-01-18 13:46:52
**/
@Setter
@Getter
@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProdRecord", description = "产品信息记录实体")
public class ProdRecord {

    //数据库中的字段
    
            @ApiModelProperty(value = "主键", dataType = "Long")
    private Long id ;//  主键
    
            @ApiModelProperty(value = "产品id", dataType = "Byte")
    private Byte prod_id ;//  产品id
    
            @ApiModelProperty(value = "总价", dataType = "Float")
    private Float total ;//  总价
    
            @ApiModelProperty(value = "单价", dataType = "Float")
    private Float priice ;//  单价
    
            @ApiModelProperty(value = "数量", dataType = "Float")
    private Float num ;//  数量
    
            @ApiModelProperty(value = "业务ID", dataType = "Long")
    private Long bus_id ;//  业务ID
    
            @ApiModelProperty(value = "业务类型", dataType = "Byte")
    private Byte bus_type ;//  业务类型
    
    //此处可添加查询显示辅助字段

    private String prod_name;
    private String prod_no;
}