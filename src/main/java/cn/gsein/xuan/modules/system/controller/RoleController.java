package cn.gsein.xuan.modules.system.controller;

import cn.gsein.xuan.common.controller.BaseController;
import cn.gsein.xuan.common.entity.JsonResult;
import cn.gsein.xuan.modules.system.entity.Role;
import cn.gsein.xuan.modules.system.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色相关控制器
 *
 * @author G. Seinfeld
 * @since 2020/07/03
 */
@RequestMapping("/sys/role")
@RestController
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;


    /**
     * 获取分页列表数据
     *
     * @param current 当前页码
     * @param size    每页条数
     * @param role    查询条件
     * @return 分页列表数据
     */
    @GetMapping("/list")
    public JsonResult<Page<Role>> list(Integer current, Integer size, Role role) {
        if (current == null || size == null) {
            return JsonResult.error("当前页码和每页条数不能为空！");
        }
        PageRequest pageRequest = PageRequest.of(current - 1, size);
        return JsonResult.ok(roleService.page(role, pageRequest));
    }
}
