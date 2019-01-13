package org.alvin.cishan.sys.service.person;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.List;

/**
 * @类说明: 客户联系人--查询条件实体类
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Person", description = "客户联系人查询条件实体")
public class PersonCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(cus_id, " AND t.bus_id = ? ");
		add(cus_id, " AND t.bus_type = ? ");
		add(address_id, " AND t.address_id = ? ");
		// add(ids, "AND t.id IN ");");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "客户id", dataType = "Long")
	private Long cus_id;//  客户id
	@ApiModelProperty(value = "收寄方ID", dataType = "Long")
	private Long address_id;//  收寄方ID
	@ApiModelProperty(value = "业务类型", dataType = "Byte")
	private Byte bus_type;//  业务类型
	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}