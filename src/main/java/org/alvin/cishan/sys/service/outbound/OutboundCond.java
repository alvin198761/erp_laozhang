package org.alvin.cishan.sys.service.outbound;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.List;

/**
 * @类说明: 出库记录--查询条件实体类
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Outbound", description = "出库记录查询条件实体")
public class OutboundCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(cus_id, " AND t.cus_id = ? ");
		add(prod_id, " AND t.prod_id = ? ");
		add(price, " AND t.price = ? ");
		add(num, " AND t.num = ? ");
		add(total, " AND t.total = ? ");
		add(status, " AND t.status = ? ");
		add(remark, " AND t.remark LIKE ? ", 3);
		// add(ids, "AND t.id IN ");");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "客户id", dataType = "Long")
	private Long cus_id;//  客户id
	@ApiModelProperty(value = "产品id", dataType = "Long")
	private Long prod_id;//  产品id
	@ApiModelProperty(value = "单价", dataType = "Float")
	private Float price;//  单价
	@ApiModelProperty(value = "数量", dataType = "Float")
	private Float num;//  数量
	@ApiModelProperty(value = "总价", dataType = "Float")
	private Float total;//  总价
	@ApiModelProperty(value = "送货状态", dataType = "Byte")
	private Byte status;//  送货状态
	@ApiModelProperty(value = "备注", dataType = "String")
	private String remark;//  备注

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}