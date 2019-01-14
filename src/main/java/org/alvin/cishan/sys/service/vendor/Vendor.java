package org.alvin.cishan.sys.service.vendor;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @类说明: 供应商--实体类
 * @author: 唐植超
 * @date : 2018-11-27 17:05:46
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Vendor", description = "供应商实体")
public class Vendor {

	//数据库中的字段

	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键

	@ApiModelProperty(value = "供应商编号", dataType = "String")
	private String vendor_no;//  供应商编号

	@ApiModelProperty(value = "供应商名称", dataType = "String")
	private String vendor_name;//  供应商名称

	@ApiModelProperty(value = "供应商地址", dataType = "String")
	private String address;//  供应商地址

	@ApiModelProperty(value = "供应商级别", dataType = "Byte")
	private Byte level;//  供应商级别

	@ApiModelProperty(value = "备注", dataType = "String")
	private String remark;//  备注

	//此处可添加查询显示辅助字段

	private String level_name;//级别名称

}