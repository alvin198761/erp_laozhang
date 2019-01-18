package org.alvin.cishan.sys.service.prodrecord;


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
 * @类说明: 产品信息记录--查询条件实体类
 * @author: 唐植超
 * @date : 2019-01-18 13:46:52
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProdRecord", description = "产品信息记录查询条件实体")
public class ProdRecordCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(prod_id, " AND t.prod_id = ? ");
		add(total, " AND t.total = ? ");
		add(priice, " AND t.priice = ? ");
		add(num, " AND t.num = ? ");
		add(bus_id, " AND t.bus_id = ? ");
		add(bus_type, " AND t.bus_type = ? ");
		// add(ids, "AND t.id IN ");");
		add(bus_ids, " AND t.bus_id in ");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "产品id", dataType = "Byte")
	private Byte prod_id;//  产品id
	@ApiModelProperty(value = "总价", dataType = "Float")
	private Float total;//  总价
	@ApiModelProperty(value = "单价", dataType = "Float")
	private Float priice;//  单价
	@ApiModelProperty(value = "数量", dataType = "Float")
	private Float num;//  数量
	@ApiModelProperty(value = "业务ID", dataType = "Long")
	private Long bus_id;//  业务ID
	@ApiModelProperty(value = "业务类型", dataType = "Byte")
	private Byte bus_type;//  业务类型

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类

	private List<Long> bus_ids;
}