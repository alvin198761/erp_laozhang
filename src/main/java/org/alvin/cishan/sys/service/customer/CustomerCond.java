package org.alvin.cishan.sys.service.customer;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.List;

/**
 * @类说明: 客户--查询条件实体类
 * @author: 唐植超
 * @date : 2018-11-27 17:15:51
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Customer", description = "客户查询条件实体")
public class CustomerCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(cus_no, " AND t.cus_no LIKE ? ", 3);
		add(cus_name, " AND t.cus_name LIKE ? ", 3);
		add(address, " AND t.address LIKE ? ", 3);
		add(cus_type, " AND t.cus_type = ? ");
		add(remark, " AND t.remark LIKE ? ", 3);
		// add(ids, "AND t.id IN ");");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "客户编号", dataType = "String")
	private String cus_no;//  客户编号
	@ApiModelProperty(value = "单位名称", dataType = "String")
	private String cus_name;//  单位名称
	@ApiModelProperty(value = "客户地址", dataType = "String")
	private String address;//  客户地址
	@ApiModelProperty(value = "客户性质", dataType = "Byte")
	private Byte cus_type;//  客户性质
	@ApiModelProperty(value = "备注", dataType = "String")
	private String remark;//  备注

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}