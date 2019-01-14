package org.alvin.cishan.sys.service.inbound;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @类说明: 入库记录--实体类
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Inbound", description = "入库记录实体")
public class Inbound {

	//数据库中的字段

	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键

	@ApiModelProperty(value = "产品id", dataType = "String")
	private String prod_id;//  产品id

	//                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "入库日期", dataType = "Date")
	private Date date;//  入库日期

	@ApiModelProperty(value = "发票类型", dataType = "String")
	private String ticket_type;//  发票类型

	@ApiModelProperty(value = "发票状态", dataType = "String")
	private String ticket_status;//  发票状态

	@ApiModelProperty(value = "备注", dataType = "String")
	private String remark;//  备注

	//此处可添加查询显示辅助字段

	private String prod_name;
	private String prod_no;

}