package org.alvin.cishan.sys.service.taxcategory;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @类说明: 税收分类--实体类
 * @author: 唐植超
 * @date : 2019-01-18 13:46:52
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TaxCategory", description = "税收分类实体")
public class TaxCategory {

	//数据库中的字段

	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键

	@ApiModelProperty(value = "名称", dataType = "String")
	private String name;//  名称

	@ApiModelProperty(value = "税收编码", dataType = "String")
	private String tax_code;//  税收编码

	//此处可添加查询显示辅助字段

}