package org.alvin.cishan.sys.service.customer;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @类说明: 客户--实体类
 * @author: 唐植超
 * @date : 2018-11-27 17:15:51
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Customer", description = "客户实体")
public class Customer {

	//数据库中的字段

	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "联系人", dataType = "Long")
	private Long concat_id;//  联系人

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



	//此处可添加查询显示辅助字段

	private String type_name ; //客户性质名称

}