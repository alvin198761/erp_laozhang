package org.alvin.cishan.sys.service.bill;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @类说明: 开票信息--实体类
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Bill", description = "开票信息实体")
public class Bill {

	//数据库中的字段

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

	//此处可添加查询显示辅助字段
	private String vendor_name; //供应商名称
	private String vendor_no; //供应商编号

	private String cus_name;
	private String cus_no;
}