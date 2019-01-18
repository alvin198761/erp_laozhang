package org.alvin.cishan.sys.service.outbound;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.alvin.cishan.sys.service.prodrecord.ProdRecord;

import java.util.List;

/**
 * @类说明: 出库记录--实体类
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Outbound", description = "出库记录实体")
public class Outbound {

	//数据库中的字段

	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键

	@ApiModelProperty(value = "客户id", dataType = "Long")
	private Long cus_id;//  客户id

	@ApiModelProperty(value = "送货状态", dataType = "String")
	private String status;//  送货状态

	@ApiModelProperty(value = "备注", dataType = "String")
	private String remark;//  备注

	//此处可添加查询显示辅助字段

	private String cus_name;
	private String cus_no;

	private List<ProdRecord> prods;


}