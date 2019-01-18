package org.alvin.cishan.sys.service.person;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @类说明: 客户联系人--实体类
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Person", description = "客户联系人实体")
public class Person {

	//数据库中的字段

	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键

	@ApiModelProperty(value = "业务id", dataType = "Long")
	private Long bus_id;//  客户id

	@ApiModelProperty(value = "联系人姓名", dataType = "String")
	private String name;//  收寄方ID

	@ApiModelProperty(value = "联系人电话", dataType = "String")
	private String phone_no;//  收寄方ID

	@ApiModelProperty(value = "业务类型", dataType = "Byte")
	private Byte bus_type;//  业务类型

}