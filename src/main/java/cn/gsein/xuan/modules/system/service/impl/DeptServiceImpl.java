package cn.gsein.xuan.modules.system.service.impl;

import cn.gsein.xuan.common.service.impl.BaseServiceImpl;
import cn.gsein.xuan.modules.system.dao.DeptDao;
import cn.gsein.xuan.modules.system.entity.Dept;
import cn.gsein.xuan.modules.system.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * 部门相关业务层实现类
 *
 * @author G. Seinfeld
 * @since 2020/07/08
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept, DeptDao> implements DeptService {
}
