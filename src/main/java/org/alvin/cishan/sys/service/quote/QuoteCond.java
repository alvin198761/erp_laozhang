package org.alvin.cishan.sys.service.quote;

    
import java.util.Date;
    
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
* @类说明: 报价--查询条件实体类
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Quote", description = "报价查询条件实体")
public class QuoteCond extends BaseCondition {


    /**
    * @方法说明: 拼加自定义条件
    **/
    @Override
    public void addCondition() {
                    add(id , " AND t.id = ? " );
                            add(cus_id , " AND t.cus_id = ? " );
                            add(date , " AND t.date = ? " );
                            add(prod_id , " AND t.prod_id = ? " );
                            add(total , " AND t.total = ? " );
                            add(priice , " AND t.priice = ? " );
                            add(remark , " AND t.remark LIKE ? " ,3);
                            add(status , " AND t.status = ? " );
                            add(num , " AND t.num = ? " );
                // add(ids, "AND t.id IN ");");
    }

    //feilds
        @ApiModelProperty(value = "主键", dataType = "Long")
    private  Long  id ;//  主键
        @ApiModelProperty(value = "客户id", dataType = "Long")
    private  Long  cus_id ;//  客户id
        @ApiModelProperty(value = "报价日期", dataType = "Date")
    private  Date  date ;//  报价日期
        @ApiModelProperty(value = "产品id", dataType = "Byte")
    private  Byte  prod_id ;//  产品id
        @ApiModelProperty(value = "总价", dataType = "Float")
    private  Float  total ;//  总价
        @ApiModelProperty(value = "单价", dataType = "Float")
    private  Float  priice ;//  单价
        @ApiModelProperty(value = "备注", dataType = "String")
    private  String  remark ;//  备注
        @ApiModelProperty(value = "单据状态", dataType = "Byte")
    private  Byte  status ;//  单据状态
        @ApiModelProperty(value = "数量", dataType = "Float")
    private  Float  num ;//  数量
    
    //private List<Long> ids;// 主键列表
    private List<String> selectedFields; //暂时不放入父类
}