package org.alvin.cishan.sys.service.address;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.List;

/**
 * @类说明: 收寄信息--查询条件实体类
 * @author: 唐植超
 * @date : 2018-11-27 14:04:59
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Address", description = "收寄信息查询条件实体")
public class AddressCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(type, " AND t.type = ? ");
		add(target_phone, " AND t.target_phone LIKE ? ", 3);
		add(target_addr, " AND t.target_addr LIKE ? ", 3);
		add(target_name, " AND t.target_name LIKE ? ", 3);
		add(target_id, " AND t.target_id = ? ");
		add(remark, " AND t.remark LIKE ? ", 3);
		// add(ids, "AND t.id IN ");");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "类型", dataType = "Byte")
	private Byte type;//  类型
	@ApiModelProperty(value = "电话", dataType = "String")
	private String target_phone;//  电话
	@ApiModelProperty(value = "地址", dataType = "String")
	private String target_addr;//  地址
	@ApiModelProperty(value = "姓名", dataType = "String")
	private String target_name;//  姓名
	@ApiModelProperty(value = "收寄方id", dataType = "Long")
	private Long target_id;//  收寄方id
	@ApiModelProperty(value = "备注", dataType = "String")
	private String remark;//  备注

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}