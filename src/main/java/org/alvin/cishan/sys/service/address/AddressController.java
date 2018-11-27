package org.alvin.cishan.sys.service.address;
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
* @类说明: 收寄信息--数据控制器层
* @author: 唐植超
* @date : 2018-11-27 14:04:59
**/
@RestController
@RequestMapping("address")
public class AddressController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AddressService service; //注入收寄信息数据逻辑层

    /**
    * @方法说明：  新增[收寄信息]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Address address) {
		return service.save(address);
	}

    /**
    * @方法说明： 删除收寄信息记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

    /**
    * @方法说明： 修改收寄信息记录
    */
	@PostMapping("update")
	public int update(@RequestBody Address address) {
		return service.update(address);
	}

    /**
    * @方法说明： 按条件查询分页收寄信息列表
    */
	@PostMapping("queryPage")
	public Page<Address> queryPage(@RequestBody AddressCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页收寄信息列表
    */
	@PostMapping("queryList")
	public List<Address> queryList(@RequestBody AddressCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个收寄信息记录
    */
	@RequestMapping("findById")
	public Address findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询收寄信息记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody AddressCond cond ){
		return service.queryCount(cond);
	}
}