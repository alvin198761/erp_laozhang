package org.alvin.cishan.sys.service.bill;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.List;

/**
 * @类说明: 开票信息--查询条件实体类
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Bill", description = "开票信息查询条件实体")
public class BillCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(bus_id, " AND t.bus_id = ? ");
		add(bank, " AND t.bank LIKE ? ", 3);
		add(account, " AND t.account LIKE ? ", 3);
		add(taxpayer_no, " AND t.taxpayer_no LIKE ? ", 3);
		add(remark, " AND t.remark LIKE ? ", 3);
		// add(ids, "AND t.id IN ");");
		add(bus_type, " AND t.bus_type = ? ");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键

	@ApiModelProperty(value = "业务ID", dataType = "Long")
	private Long bus_id;//  供应商

	@ApiModelProperty(value = "开户行", dataType = "String")
	private String bank;//  开户行

	@ApiModelProperty(value = "账号", dataType = "String")
	private String account;//  账号

	@ApiModelProperty(value = "纳税人识别号", dataType = "String")
	private String taxpayer_no;//  纳税人识别号

	@ApiModelProperty(value = "备注", dataType = "String")
	private String remark;//  备注

	@ApiModelProperty(value = "业务类型", dataType = "Byte")
	private Byte bus_type;

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}