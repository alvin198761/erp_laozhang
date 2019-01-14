package org.alvin.cishan.sys.service.vendor;


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
 * @类说明: 供应商--查询条件实体类
 * @author: 唐植超
 * @date : 2018-11-27 17:05:46
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Vendor", description = "供应商查询条件实体")
public class VendorCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(vendor_no, " AND t.vendor_no LIKE ? ", 3);
		add(vendor_name, " AND t.vendor_name LIKE ? ", 3);
		add(address, " AND t.address = ? ");
		add(level, " AND t.level = ? ");
		add(remark, " AND t.remark LIKE ? ", 3);
		// add(ids, "AND t.id IN ");");
	}

	//feilds
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

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}