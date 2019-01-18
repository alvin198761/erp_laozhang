package org.alvin.cishan.sys.service.taxcategory;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.List;

/**
 * @类说明: 税收分类--查询条件实体类
 * @author: 唐植超
 * @date : 2019-01-18 13:46:52
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TaxCategory", description = "税收分类查询条件实体")
public class TaxCategoryCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(name, " AND t.name LIKE ? ", 3);
		add(tax_code, " AND t.tax_code LIKE ? ", 3);
		// add(ids, "AND t.id IN ");");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "名称", dataType = "String")
	private String name;//  名称
	@ApiModelProperty(value = "税收编码", dataType = "String")
	private String tax_code;//  税收编码

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}