package org.alvin.cishan.sys.service.inbound;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.Date;
import java.util.List;

/**
 * @类说明: 入库记录--查询条件实体类
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Inbound", description = "入库记录查询条件实体")
public class InboundCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(prod_id, " AND t.prod_id LIKE ? ", 3);
		add(date, " AND t.date = ? ");
		add(ticket_type, " AND t.ticket_type = ? ");
		add(ticket_status, " AND t.ticket_status = ? ");
		add(remark, " AND t.remark LIKE ? ", 3);
		// add(ids, "AND t.id IN ");");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "产品id", dataType = "String")
	private String prod_id;//  产品id
	@ApiModelProperty(value = "入库日期", dataType = "Date")
	private Date date;//  入库日期
	@ApiModelProperty(value = "发票类型", dataType = "String")
	private String ticket_type;//  发票类型
	@ApiModelProperty(value = "发票状态", dataType = "String")
	private String ticket_status;//  发票状态
	@ApiModelProperty(value = "备注", dataType = "String")
	private String remark;//  备注

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}