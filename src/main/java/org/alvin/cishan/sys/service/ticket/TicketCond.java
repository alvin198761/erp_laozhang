package org.alvin.cishan.sys.service.ticket;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.List;

/**
 * @类说明: 进销项发票录入--查询条件实体类
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Ticket", description = "进销项发票录入查询条件实体")
public class TicketCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(vendor_id, " AND t.vendor_id = ? ");
		add(tax_type, " AND t.tax_type LIKE ? ", 3);
		add(target_name, " AND t.target_name LIKE ? ", 3);
		add(target_id, " AND t.target_id = ? ");
		add(remark, " AND t.remark LIKE ? ", 3);
		add(ticket_type, " AND t.ticket_type = ? ");
		add(rate, " AND t.rate = ? ");
		add(type, " AND t.type = ? ");
		add(cus_id, " AND t.cus_id = ? ");
		// add(ids, "AND t.id IN ");");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "供应商id", dataType = "Long")
	private Long vendor_id;//  供应商id
	@ApiModelProperty(value = "税收类型", dataType = "String")
	private String tax_type;//  税收类型
	@ApiModelProperty(value = "姓名", dataType = "String")
	private String target_name;//  姓名
	@ApiModelProperty(value = "收寄方id", dataType = "Long")
	private Long target_id;//  收寄方id
	@ApiModelProperty(value = "备注", dataType = "String")
	private String remark;//  备注
	@ApiModelProperty(value = "发票类型", dataType = "String")
	private String ticket_type;//  发票类型
	@ApiModelProperty(value = "税率", dataType = "Double")
	private Double rate;//  税率
	@ApiModelProperty(value = "进出类型", dataType = "Byte")
	private Byte type;//  进出类型
	@ApiModelProperty(value = "客户Id", dataType = "Long")
	private Long cus_id;//  客户Id

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}