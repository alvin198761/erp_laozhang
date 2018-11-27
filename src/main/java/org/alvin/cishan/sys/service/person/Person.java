package org.alvin.cishan.sys.service.person;

    
    
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* @类说明: 客户联系人--实体类
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Setter
@Getter
@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Person", description = "客户联系人实体")
public class Person {

    //数据库中的字段
    
            @ApiModelProperty(value = "主键", dataType = "Long")
    private Long id ;//  主键
    
            @ApiModelProperty(value = "客户id", dataType = "Long")
    private Long cus_id ;//  客户id
    
            @ApiModelProperty(value = "收寄方ID", dataType = "Long")
    private Long address_id ;//  收寄方ID
    
    //此处可添加查询显示辅助字段

}