package org.alvin.cishan.sys.service.inbound;
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
* @类说明: 入库记录--数据控制器层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@RestController
@RequestMapping("inbound")
public class InboundController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private InboundService service; //注入入库记录数据逻辑层

    /**
    * @方法说明：  新增[入库记录]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Inbound inbound) {
		return service.save(inbound);
	}

    /**
    * @方法说明： 删除入库记录记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

    /**
    * @方法说明： 修改入库记录记录
    */
	@PostMapping("update")
	public int update(@RequestBody Inbound inbound) {
		return service.update(inbound);
	}

    /**
    * @方法说明： 按条件查询分页入库记录列表
    */
	@PostMapping("queryPage")
	public Page<Inbound> queryPage(@RequestBody InboundCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页入库记录列表
    */
	@PostMapping("queryList")
	public List<Inbound> queryList(@RequestBody InboundCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个入库记录记录
    */
	@RequestMapping("findById")
	public Inbound findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询入库记录记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody InboundCond cond ){
		return service.queryCount(cond);
	}
}