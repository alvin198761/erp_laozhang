package org.alvin.cishan.sys.service.outbound;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dl.keep.common.util.Page;

/**
* @类说明: 出库记录--数据控制器层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@RestController
@RequestMapping("outbound")
public class OutboundController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private OutboundService service; //注入出库记录数据逻辑层

    /**
    * @方法说明：  新增[出库记录]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Outbound outbound) {
		return service.save(outbound);
	}

    /**
    * @方法说明： 删除出库记录记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

    /**
    * @方法说明： 修改出库记录记录
    */
	@PostMapping("update")
	public int update(@RequestBody Outbound outbound) {
		return service.update(outbound);
	}

    /**
    * @方法说明： 按条件查询分页出库记录列表
    */
	@PostMapping("queryPage")
	public Page<Outbound> queryPage(@RequestBody OutboundCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页出库记录列表
    */
	@PostMapping("queryList")
	public List<Outbound> queryList(@RequestBody OutboundCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个出库记录记录
    */
	@RequestMapping("findById")
	public Outbound findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询出库记录记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody OutboundCond cond ){
		return service.queryCount(cond);
	}
}